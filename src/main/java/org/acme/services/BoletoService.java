package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;
import org.acme.models.OrdemDeProducao;
import org.acme.models.boleto.asaas.BoletoAsaas;
import org.acme.models.boleto.asaas.BoletoAsaasDTO;
import org.acme.models.boleto.asaas.RetornoAsaas;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BoletoService {
    @Inject
    private EntityManager em;
    private Gson gson = new GsonUtil().parser;
    private FieldUtil fieldUtil = new FieldUtil();

    public List<BoletoAsaas> listAll() {
        return em.createQuery("SELECT b FROM BoletoAsaas b", BoletoAsaas.class).getResultList();
    }

    public BoletoAsaas listOne(String uuid) {

        return em.createQuery("SELECT b from BoletoAsaas b WHERE uuid = :uuid", BoletoAsaas.class).setParameter("uuid", uuid).getSingleResult();
    }

    public BoletoAsaas create(BoletoAsaas boletoAsaas) {
        try {
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(boletoAsaas)))
                    .header("access_token", "$aact_YTU5YTE0M2M2N2I4MTliNzk0YTI5N2U5MzdjNWZmNDQ6OjAwMDAwMDAwMDAwMDAwNDU1NTg6OiRhYWNoXzU5ZTZmNjlkLWFkZmItNGU4YS1iN2FkLTEyOTQxNjEyYzcyYg==")
                    .header("Content-Type", "application/json")
                    .uri(new URI("https://sandbox.asaas.com/api/v3/paymentLinks"))
                    .build();
            HttpResponse<String> resposta = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (resposta.statusCode() == 200) {
                RetornoAsaas retornoAsaas = gson.fromJson(resposta.body(), RetornoAsaas.class);
                retornoAsaas.setAtualizadoEm(LocalDate.now().toString());
                RetornoAsaas retornoAsaasBD = em.merge(retornoAsaas);
                boletoAsaas.setRetornoAsaas(retornoAsaasBD);
                em.merge(boletoAsaas);
                em.flush();
                return boletoAsaas;
            }
            throw new RuntimeException();
        } catch (Throwable t) {
            t.printStackTrace();
            return null;
        }

    }

    public BoletoAsaas update(String uuid, BoletoAsaasDTO boletoAsaasDTO) {
        BoletoAsaas boletoAsaas = listOne(uuid);

        em.merge(boletoAsaas);
        fieldUtil.updateFieldsDtoToModel(boletoAsaas, boletoAsaasDTO);
        em.persist(boletoAsaas);
        return boletoAsaas;
    }

    public List<BoletoAsaas> listByMonth() {
        LocalDate hoje = LocalDate.now();
        LocalDate umMesAtras = LocalDate.of(hoje.getYear(),hoje.getMonth().getValue()-1,hoje.getDayOfMonth());
        return em.createQuery("SELECT b FROM BoletoAsaas b WHERE b.atualizadoEm <= :hoje AND b.atualizadoEm >= :umMesAtras ", BoletoAsaas.class)
                .setParameter("hoje",hoje.toString())
                .setParameter("umMesAtras",umMesAtras.toString())
                .getResultList();
    }
}
