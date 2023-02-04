package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.cobranca.*;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@ApplicationScoped
public class CobrancaService extends Service {



    public List<CobrancaParcelada> getAll(){
        return em.createQuery("SELECT c FROM CobrancaParcelada c",CobrancaParcelada.class).getResultList();
    }

    public CobrancaParcelada create(CobrancaParceladaDTO cobrancaParceladaDTO) {
        try {

            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(cobrancaParceladaDTO)))
                    .header("access_token", "$aact_YTU5YTE0M2M2N2I4MTliNzk0YTI5N2U5MzdjNWZmNDQ6OjAwMDAwMDAwMDAwMDAwNDU1NTg6OiRhYWNoXzA3ODg1NTliLWU4ZTgtNDI1Ni05OTUyLTZkYmUxZTA1ZWVmMQ==")
                    .header("Content-Type", "application/json")
                    .uri(new URI("https://sandbox.asaas.com/api/v3/payments"))
                    .build();
            HttpResponse<String> resposta = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CobrancaParceladaRetorno cobrancaParceladaRetorno = gson.fromJson(resposta.body(), CobrancaParceladaRetorno.class);




            if(cobrancaParceladaDTO.getFine() != null){


                Fine fineMerge = em.merge(cobrancaParceladaDTO.getFine());
                cobrancaParceladaDTO.setFine(fineMerge);

            }
            if(cobrancaParceladaDTO.getDiscount() != null){

                Discount discountMerge = em.merge(cobrancaParceladaDTO.getDiscount());
                cobrancaParceladaDTO.setDiscount(discountMerge);
            }
            if(cobrancaParceladaDTO.getInterest() != null) {

                Interest interestMerged = em.merge(cobrancaParceladaDTO.getInterest());
                cobrancaParceladaDTO.setInterest(interestMerged);
            }
            if(cobrancaParceladaDTO.getSplits() != null) {

                cobrancaParceladaDTO.getSplits().forEach(split->{
                     em.merge(split);
                     cobrancaParceladaDTO.getSplits().add(split);
                });
            }

            CobrancaParceladaRetorno cobrancaParceladaRetornoBD = em.merge(cobrancaParceladaRetorno);
            cobrancaParceladaDTO.setCobrancaParceladaRetorno(cobrancaParceladaRetornoBD);


            return  em.merge(cobrancaParceladaDTO.convertToModel());
        } catch (Throwable t) {
            t.printStackTrace();
            throw new RuntimeException("Erro na integração da cobrança");
        }



    }
}
