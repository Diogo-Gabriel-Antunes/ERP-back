package org.acme.services;

import org.acme.models.asaas.Assinatura.Assinatura;
import org.acme.models.DTO.Financas.AssinaturaDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Optional;

@ApplicationScoped
public class AssinaturaService extends Service{

    @Transactional
    public Response create(String json) {
        try{
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .header("access_token", "$aact_YTU5YTE0M2M2N2I4MTliNzk0YTI5N2U5MzdjNWZmNDQ6OjAwMDAwMDAwMDAwMDAwNDU1NTg6OiRhYWNoXzU5ZTZmNjlkLWFkZmItNGU4YS1iN2FkLTEyOTQxNjEyYzcyYg==")
                    .header("Content-Type", "application/json")
                    .uri(new URI("https://private-anon-22990467ef-asaasv3.apiary-mock.com/api/v3/subscriptions"))
                    .build();
            HttpResponse<String> resposta = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            if(resposta.statusCode() == 200){

                AssinaturaDTO assinaturaDTO = gson.fromJson(json, AssinaturaDTO.class);
                Assinatura assinatura = new Assinatura();
                convertDTOS(assinatura,assinaturaDTO);
                fieldUtil.updateFieldsDtoToModel(assinatura,assinaturaDTO);
                em.persist(assinatura);
                return Response.status(Response.Status.CREATED).entity(assinatura).build();
            }
            throw new RuntimeException("Erro na inserção da assinatura");
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }



    private void convertDTOS(Assinatura assinatura, AssinaturaDTO assinaturaDTO) {

        if(assinaturaDTO.getSplit() != null){

            fieldUtil.updateFieldsDtoToModel(assinatura.getSplit(),assinaturaDTO.getSplit());
        }
        fieldUtil.updateFieldsDtoToModel(assinatura.getFine(),assinaturaDTO.getFine());
        fieldUtil.updateFieldsDtoToModel(assinatura.getDiscount(),assinaturaDTO.getDiscount());
        fieldUtil.updateFieldsDtoToModel(assinatura.getInterest(),assinaturaDTO.getInterest());
        assinaturaDTO.setFine(null);
        assinaturaDTO.setSplit(null);
        assinaturaDTO.setDiscount(null);
        assinaturaDTO.setInterest(null);
        em.persist(assinatura.getSplit());
        em.persist(assinatura.getFine());
        em.persist(assinatura.getDiscount());
        em.persist(assinatura.getInterest());
    }

    @Transactional
    public Assinatura update(String uuid, String json) {
        try{
            Optional<Assinatura> assinatura = Assinatura.findByIdOptional(uuid);
            if(!assinatura.isPresent()){
                throw new RuntimeException("Não foi encontrado nenhuma assinatura com esse uuid");
            }
            em.merge(assinatura.get());
            AssinaturaDTO assinaturaDTO = gson.fromJson(json, AssinaturaDTO.class);
            convertDTOS(assinatura.get(),assinaturaDTO);
            fieldUtil.updateFieldsDtoToModel(assinatura.get(),assinaturaDTO);
            em.persist(assinatura.get());
            return assinatura.get();
        }catch (Throwable t){
            t.printStackTrace();
            System.out.println("Erro na atualização da assinatura");
            return null;
        }
    }


}
