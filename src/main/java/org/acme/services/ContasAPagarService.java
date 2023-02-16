package org.acme.services;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.StringUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.asaas.ContasApagar.ContasAPagar;
import org.acme.models.DTO.Financas.ContasAPagarDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ContasAPagarService extends Service {

    public List<ContasAPagar> listAll() {
        return em.createQuery("SELECT c from ContasAPagar c", ContasAPagar.class).getResultList();
    }

    public Response create(String json) {
        try{
            ContasAPagarDTO contasAPagarDTO = gson.fromJson(json, ContasAPagarDTO.class);
            validaConta(contasAPagarDTO);
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
                fieldUtil.updateFieldsDtoToModel(contasAPagar.getCliente(),contasAPagarDTO.getCliente());
                fieldUtil.updateFieldsDtoToModel(contasAPagar,contasAPagarDTO);
                em.persist(contasAPagar);
                return ResponseBuilder.responseOk(contasAPagar);
            }
            throw new RuntimeException();
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }

    }

    private void validaConta(ContasAPagarDTO contasAPagarDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if(!StringUtil.stringValida(contasAPagarDTO.getIdentificationField())){
            validacao.add("Campo codigo de barras invalido");
        }
        if(!StringUtil.stringValida(contasAPagarDTO.getDescription())){
            validacao.add("Campo descrição invalido");
        }
        if(!StringUtil.stringValida(contasAPagarDTO.getDueDate())){
            validacao.add("Data de vencimento invalida");
        }
        if(contasAPagarDTO.getValue() < 5){
            validacao.add("Valor invalido");
            validacao.add("Valor deve ser maior que 5");
        }
        clienteService.validaCliente(contasAPagarDTO.getCliente(),validacao,false);

        validacao.lancaErro();
    }

    public Response getOne(String uuid) {
        try{
            ContasAPagar contasAPagar = getEntity(uuid);
            if(contasAPagar != null){
                return ResponseBuilder.responseOk(contasAPagar);
            }
            return ResponseBuilder.responseEntityNotFound();
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    private ContasAPagar getEntity(String uuid) {
        try {
            Optional<ContasAPagar> contasAPagar = ContasAPagar.findByIdOptional(uuid);
            return contasAPagar.orElse(null);
        }catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }

    public Response update(String id, String json) {
        try{
            ContasAPagarDTO contasAPagarDTO = gson.fromJson(json, ContasAPagarDTO.class);
            ContasAPagar contasAPagar = getEntity(id);
            em.merge(contasAPagar);

            fieldUtil.updateFieldsDtoToModel(contasAPagar,contasAPagarDTO);

            em.persist(contasAPagar);
            return ResponseBuilder.responseOk(contasAPagar);
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }


    public Response delete(String uuid) {
        ContasAPagar contasAPagar = getEntity(uuid);
        if(contasAPagar != null){
            em.remove(contasAPagar);
            return ResponseBuilder.responseOk(contasAPagar);
        }
        return ResponseBuilder.responseEntityNotFound();
    }
}
