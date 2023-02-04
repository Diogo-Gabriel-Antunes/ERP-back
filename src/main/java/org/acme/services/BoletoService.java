package org.acme.services;

import org.acme.Util.DateUtil;
import org.acme.models.cobranca.Assinatura.Boleto.BoletoAsaas;
import org.acme.models.cobranca.Assinatura.Boleto.BoletoAsaasDTO;
import org.acme.models.cobranca.Assinatura.Boleto.RetornoAsaas;

import javax.enterprise.context.ApplicationScoped;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BoletoService extends Service{


    public List<BoletoAsaas> listAll() {
        return em.createQuery("SELECT b FROM BoletoAsaas b", BoletoAsaas.class).getResultList();
    }

    public BoletoAsaas listOne(String uuid) {

        return em.createQuery("SELECT b from BoletoAsaas b WHERE uuid = :uuid", BoletoAsaas.class).setParameter("uuid", uuid).getSingleResult();
    }

    public BoletoAsaas create(String boletoAsaasJson) {
        try {
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(boletoAsaasJson))
                    .header("access_token", "$aact_YTU5YTE0M2M2N2I4MTliNzk0YTI5N2U5MzdjNWZmNDQ6OjAwMDAwMDAwMDAwMDAwNDU1NTg6OiRhYWNoXzU5ZTZmNjlkLWFkZmItNGU4YS1iN2FkLTEyOTQxNjEyYzcyYg==")
                    .header("Content-Type", "application/json")
                    .uri(new URI("https://sandbox.asaas.com/api/v3/paymentLinks"))
                    .build();
            HttpResponse<String> resposta = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (resposta.statusCode() == 200) {
                BoletoAsaas boletoAsaas = gson.fromJson(boletoAsaasJson,BoletoAsaas.class);
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

        try {
            LocalDate umMesAtras = LocalDate.of(hoje.getYear(), hoje.getMonth().getValue() - 1, hoje.getDayOfMonth() - 1);

            if (new DateUtil().validaData(hoje)) {
                return em.createQuery("SELECT b FROM BoletoAsaas b WHERE b.atualizadoEm <= :hoje AND b.atualizadoEm >= :umMesAtras ", BoletoAsaas.class)
                        .setParameter("hoje", hoje.toString())
                        .setParameter("umMesAtras", umMesAtras.toString())
                        .getResultList();
            } else {
                return em.createQuery("SELECT b FROM BoletoAsaas b WHERE b.atualizadoEm <= :hoje AND b.atualizadoEm >= :umMesAtras ", BoletoAsaas.class)
                        .setParameter("hoje", hoje.toString())
                        .setParameter("umMesAtras", umMesAtras.toString())
                        .getResultList();
            }
        } catch (DateTimeException e) {
            LocalDate umMesAtras = LocalDate.of(hoje.getYear() - 1, 12, hoje.getDayOfMonth() - 1);
            if (new DateUtil().validaData(hoje)) {
                return em.createQuery("SELECT b FROM BoletoAsaas b WHERE b.atualizadoEm <= :hoje AND b.atualizadoEm >= :umMesAtras ", BoletoAsaas.class)
                        .setParameter("hoje", hoje.toString())
                        .setParameter("umMesAtras", umMesAtras.toString())
                        .getResultList();
            } else {
                return em.createQuery("SELECT b FROM BoletoAsaas b WHERE b.atualizadoEm <= :hoje AND b.atualizadoEm >= :umMesAtras ", BoletoAsaas.class)
                        .setParameter("hoje", hoje.toString())
                        .setParameter("umMesAtras", umMesAtras.toString())
                        .getResultList();
            }
        }
    }
}
