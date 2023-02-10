package org.acme.services;

import org.acme.models.asaas.ContasApagar.ContasAPagar;
import org.acme.models.DTO.Financas.ContasAPagarDTO;

import javax.enterprise.context.ApplicationScoped;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@ApplicationScoped
public class ContasAPagarService extends Service {

    public List<ContasAPagar> listAll() {
        return em.createQuery("SELECT c from ContasAPagar c", ContasAPagar.class).getResultList();
    }

    public ContasAPagar create(ContasAPagarDTO contasAPagarDTO) {
        try{
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(contasAPagarDTO)))
                    .header("access_token", "$aact_YTU5YTE0M2M2N2I4MTliNzk0YTI5N2U5MzdjNWZmNDQ6OjAwMDAwMDAwMDAwMDAwNDU1NTg6OiRhYWNoXzU5ZTZmNjlkLWFkZmItNGU4YS1iN2FkLTEyOTQxNjEyYzcyYg==")
                    .header("Content-Type", "application/json")
                    .uri(new URI("https://sandbox.asaas.com/api/v3/bill"))
                    .build();
            HttpResponse<String> resposta = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if(resposta.statusCode() == 400){
                ContasAPagar contasAPagar = new ContasAPagar();
                fieldUtil.updateFieldsDtoToModel(contasAPagar,contasAPagarDTO);
                em.merge(contasAPagar);
                return contasAPagar;
            }
            throw new RuntimeException();
        }catch (Throwable t){
            t.printStackTrace();
            return null;
        }

    }

    public ContasAPagar getOne(String uuid) {
        return em.createQuery("SELECT c from ContasAPagar c WHERE uuid = :uuid", ContasAPagar.class)
                .setParameter("uuid", uuid)
                .getSingleResult();
    }

    public ContasAPagar update(String id, ContasAPagarDTO contasAPagarDTO) {
        ContasAPagar contasAPagar = getOne(id);

        em.merge(contasAPagar);

        fieldUtil.updateFieldsDtoToModel(contasAPagar,contasAPagarDTO);

        em.persist(contasAPagar);
        return contasAPagar;
    }


    public void delete(String uuid) {
        em.createQuery("delete from ContasAPagar WHERE uuid = :uuid")
                .setParameter("uuid",uuid).executeUpdate();

    }
}
