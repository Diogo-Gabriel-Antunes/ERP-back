package org.acme.services;

import org.acme.Util.DateUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.asaas.Boleto.BoletoAsaas;
import org.acme.models.asaas.Boleto.BoletoAsaasDTO;
import org.acme.models.asaas.Boleto.RetornoAsaas;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class BoletoService extends Service {


    public List<BoletoAsaas> listAll() {
        return em.createQuery("SELECT b FROM BoletoAsaas b", BoletoAsaas.class).getResultList();
    }

    public BoletoAsaas listOne(String uuid) {

        return em.createQuery("SELECT b from BoletoAsaas b WHERE uuid = :uuid", BoletoAsaas.class).setParameter("uuid", uuid).getSingleResult();
    }

    public Response create(String boletoAsaasJson) {
        try {
            BoletoAsaasDTO boletoAsaasDTO = gson.fromJson(boletoAsaasJson, BoletoAsaasDTO.class);

            validaBoleto(boletoAsaasDTO);
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(boletoAsaasJson))
                    .header("access_token", "$aact_YTU5YTE0M2M2N2I4MTliNzk0YTI5N2U5MzdjNWZmNDQ6OjAwMDAwMDAwMDAwMDAwNDU1NTg6OiRhYWNoXzU5ZTZmNjlkLWFkZmItNGU4YS1iN2FkLTEyOTQxNjEyYzcyYg==")
                    .header("Content-Type", "application/json")
                    .uri(new URI("https://sandbox.asaas.com/api/v3/paymentLinks"))
                    .build();
            HttpResponse<String> resposta = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if (resposta.statusCode() == 200) {
                BoletoAsaas boletoAsaas = new BoletoAsaas();
                fieldUtil.updateFieldsDtoToModel(boletoAsaas,boletoAsaasDTO);
                RetornoAsaas retornoAsaas = gson.fromJson(resposta.body(), RetornoAsaas.class);
                retornoAsaas.setAtualizadoEm(LocalDate.now().toString());
                RetornoAsaas retornoAsaasBD = em.merge(retornoAsaas);
                boletoAsaas.setRetornoAsaas(retornoAsaasBD);
                em.merge(boletoAsaas);
                em.flush();

                return Response.ok(boletoAsaas).build();
            }
            throw new RuntimeException();
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }




    public Response update(String uuid, String json) {
            BoletoAsaas boletoAsaas = listOne(uuid);
            BoletoAsaasDTO boletoAsaasDTO = gson.fromJson(json,BoletoAsaasDTO.class);
            validaBoleto(boletoAsaasDTO);
            em.merge(boletoAsaas);
            fieldUtil.updateFieldsDtoToModel(boletoAsaas, boletoAsaasDTO);
            em.persist(boletoAsaas);

            return Response.ok(boletoAsaas).build();
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

    private void validaBoleto(BoletoAsaasDTO boletoAsaas) {
        ValidacaoException validacoes = new ValidacaoException();

        if (!StringUtil.stringValida(boletoAsaas.getName())) {
            validacoes.add("Campo nome esta invalido ou vazio");
        }
        if (boletoAsaas.getBillingType() == null) {
            validacoes.add("Tipo de pagamento esta vazio");
        }
        if (boletoAsaas.getChargeType() == null) {
            validacoes.add("Forma de cobrança esta vazio");
        }
        validacoes.lancaErro();
    }
}
