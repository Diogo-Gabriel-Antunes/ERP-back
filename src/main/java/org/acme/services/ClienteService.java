package org.acme.services;

import org.acme.Util.JsonUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.Cliente;
import org.acme.models.DTO.ClienteDTO;
import org.acme.models.DTO.EnderecoNFEDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class ClienteService extends Service {


    public List<Cliente> findAll() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public Response create(String json) {
        try {

            ClienteDTO clienteDTO = createDTO(json);
            validaCliente(clienteDTO,null,true);
            Cliente cliente = new Cliente();
            fieldUtil.updateFieldsDtoToModel(cliente.getEndereco(), clienteDTO.getEndereco());
            clienteDTO.setEndereco(null);
            fieldUtil.updateFieldsDtoToModel(cliente, clienteDTO);
            em.persist(cliente.getEndereco());
            em.persist(cliente);
            return Response.ok(cliente).build();
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }

    }

    private ClienteDTO createDTO(String json) {
        HashMap<String,String> hashMap = gson.fromJson(json, HashMap.class);
        String cliente = gson.toJson(hashMap.get("cliente"));
        String endereco = gson.toJson(hashMap.get("endereco"));
        ClienteDTO clienteDTO = gson.fromJson( cliente, ClienteDTO.class);
        EnderecoNFEDTO enderecoDTO = gson.fromJson(endereco, EnderecoNFEDTO.class);
        clienteDTO.setEndereco(enderecoDTO);
        return clienteDTO;
    }


    public Response findOne(String uuid) {
       try{
           Cliente cliente = findOneEntity(uuid);
           if (cliente != null) {
               return Response.ok(cliente).build();
           }
           return Response.status(Response.Status.BAD_REQUEST).build();
       }catch (Throwable t){
           t.printStackTrace();
           return ResponseBuilder.returnResponse();
       }
    }

    private static Cliente findOneEntity(String uuid) {
        try{
            Optional<Cliente> cliente = Cliente.findByIdOptional(uuid);
            return cliente.orElse(null);
        }catch (Throwable t){
            t.printStackTrace();
            return null;
        }
    }

    public Response update(String uuid, String json) {
        try {
            ClienteDTO clienteDTO = gson.fromJson(json, ClienteDTO.class);
            validaCliente(clienteDTO,null,true);
            Cliente cliente = findOneEntity(uuid);
            em.merge(cliente);
            fieldUtil.updateFieldsDtoToModel(cliente, clienteDTO);

            em.persist(cliente);
            return Response.ok(cliente).build();
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            return ResponseBuilder.returnResponse();
        }
    }

    public Response delete(String uuid) {
       try {
           Cliente cliente =findOneEntity(uuid);
           if(cliente != null){
               em.remove(cliente);
               return Response.ok(cliente).build();
           }
           throw new RuntimeException();
       }catch (Throwable t){
           t.printStackTrace();
           return ResponseBuilder.returnResponse();
       }
    }
    public void validaCliente(ClienteDTO clienteDTO,ValidacaoException validacaoException,boolean comEndereco) {
        boolean validacaoExiste = validacaoException == null;
        if(validacaoException == null){

            validacaoException = new ValidacaoException();
        }

        if(!StringUtil.stringValida(clienteDTO.getXNome())){
            validacaoException.add("Campo nome invalido");
        }
        if(!StringUtil.stringValida(clienteDTO.getCpfCnpj())){
            validacaoException.add("Campo CPF/CNPJ invalido");
        }

        enderecoService.validaEndereco(validacaoException,clienteDTO.getEndereco(),comEndereco);
        if(validacaoExiste){
            validacaoException.lancaErro();
        }
    }
}
