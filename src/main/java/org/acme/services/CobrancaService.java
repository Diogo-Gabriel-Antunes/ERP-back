package org.acme.services;

import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.asaas.CobrancaParcelada;
import org.acme.models.asaas.CobrancaParceladaDTO;
import org.acme.models.asaas.CobrancaParceladaRetorno;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class CobrancaService extends Service {



    public List<CobrancaParcelada> getAll() {
        return em.createQuery("SELECT c FROM CobrancaParcelada c", CobrancaParcelada.class).getResultList();
    }
    public Response listOne(String uuid) {
        try{
            CobrancaParcelada cobrancaParcelada = listOneEntity(uuid);
            if(cobrancaParcelada == null){
                return ResponseBuilder.responseEntityNotFound();
            }
            return ResponseBuilder.responseOk(cobrancaParcelada);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }
    public Response create(String json) {
        try {
            CobrancaParceladaDTO cobrancaParceladaDTO = gson.fromJson(json, CobrancaParceladaDTO.class);
            validaCobrancaParcelada(cobrancaParceladaDTO);
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(cobrancaParceladaDTO)))
                    .header("access_token", "$aact_YTU5YTE0M2M2N2I4MTliNzk0YTI5N2U5MzdjNWZmNDQ6OjAwMDAwMDAwMDAwMDAwNDU1NTg6OiRhYWNoXzA3ODg1NTliLWU4ZTgtNDI1Ni05OTUyLTZkYmUxZTA1ZWVmMQ==")
                    .header("Content-Type", "application/json")
                    .uri(new URI("https://sandbox.asaas.com/api/v3/payments"))
                    .build();
            HttpResponse<String> resposta = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            CobrancaParceladaRetorno cobrancaParceladaRetorno = gson.fromJson(resposta.body(), CobrancaParceladaRetorno.class);


            CobrancaParceladaRetorno cobrancaParceladaRetornoBD = em.merge(cobrancaParceladaRetorno);
            cobrancaParceladaDTO.setCobrancaParceladaRetorno(cobrancaParceladaRetornoBD);
            CobrancaParcelada cobrancaParceladaMerged = em.merge(cobrancaParceladaDTO.convertToModel());

            return ResponseBuilder.responseOk(cobrancaParceladaMerged);
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            return ResponseBuilder.returnResponse();
        }
    }


    public Response update(String json, String uuid) {
        try {
            CobrancaParcelada cobrancaParcelada = listOneEntity(uuid);
            if(cobrancaParcelada == null){
                return ResponseBuilder.responseEntityNotFound();
            }
            em.merge(cobrancaParcelada);
            CobrancaParceladaDTO cobrancaParceladaDTO = gson.fromJson(json, CobrancaParceladaDTO.class);
            validaCobrancaParcelada(cobrancaParceladaDTO);
//            HttpClient httpClient = HttpClient.newBuilder().build();
//            HttpRequest httpRequest = HttpRequest.newBuilder()
//                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(cobrancaParceladaDTO)))
//                    .header("access_token", "$aact_YTU5YTE0M2M2N2I4MTliNzk0YTI5N2U5MzdjNWZmNDQ6OjAwMDAwMDAwMDAwMDAwNDU1NTg6OiRhYWNoXzA3ODg1NTliLWU4ZTgtNDI1Ni05OTUyLTZkYmUxZTA1ZWVmMQ==")
//                    .header("Content-Type", "application/json")
//                    .uri(new URI("https://sandbox.asaas.com/api/v3/payments"))
//                    .build();
//            HttpResponse<String> resposta = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
//            CobrancaParceladaRetorno cobrancaParceladaRetorno = gson.fromJson(resposta.body(), CobrancaParceladaRetorno.class);

            fieldUtil.updateFieldsDtoToModel(cobrancaParcelada,cobrancaParceladaDTO);
            cobrancaParceladaDTO.setCobrancaParceladaRetorno(cobrancaParceladaDTO.getCobrancaParceladaRetorno());
            em.persist(cobrancaParcelada);
            return ResponseBuilder.responseOk(cobrancaParcelada);
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            return ResponseBuilder.returnResponse();
        }
    }

    public CobrancaParcelada listOneEntity(String uuid) {
        try {
            Optional<CobrancaParcelada> cobranca = CobrancaParcelada.findByIdOptional(uuid);
            if (cobranca.isPresent()) {
                return cobranca.get();
            }
            return null;
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }
    }

    private void validaCobrancaParcelada(CobrancaParceladaDTO cobrancaParceladaDTO) {
        ValidacaoException validacao = new ValidacaoException();

        fineService.validaFine(validacao, cobrancaParceladaDTO.getFine());
        discountService.validaDiscount(validacao, cobrancaParceladaDTO.getDiscount());
        interestService.validaInterest(validacao, cobrancaParceladaDTO.getInterest());

        if (cobrancaParceladaDTO.getBillingType() == null) {
            validacao.add("Campo tipo de pagamento esta invalido");
        }
        if (cobrancaParceladaDTO.getValue() < 5.0) {
            validacao.add("Campo valor esta invalido");
            validacao.add("Campo valor deve ser maior ou igual a 5");
        }
        if (!StringUtil.stringValida(cobrancaParceladaDTO.getDueDate())) {
            validacao.add("Data de vencimento invalida");
        }

        validacao.lancaErro();
    }


}
