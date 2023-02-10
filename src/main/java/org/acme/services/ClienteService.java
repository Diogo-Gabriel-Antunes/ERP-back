package org.acme.services;

import org.acme.Util.StringUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.Cliente;
import org.acme.models.DTO.ClienteDTO;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class ClienteService extends Service {

    @Inject
    private EnderecoService enderecoService;
    public List<Cliente> findAll() {
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public Response create(String json) {
        try {
            ClienteDTO clienteDTO = gson.fromJson(json, ClienteDTO.class);
            validaCliente(clienteDTO);
            Cliente cliente = new Cliente();
            cliente.setEndereco(new EnderecoNFE());
            fieldUtil.updateFieldsDtoToModel(cliente.getEndereco(), clienteDTO.getEndereco());
            clienteDTO.setEndereco(null);
            fieldUtil.updateFieldsDtoToModel(cliente, clienteDTO);

            em.persist(cliente);
            return Response.ok(cliente).build();
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }

    }



    public Cliente findOne(String uuid) {
        return em.createQuery("SELECT c FROM Cliente c WHERE uuid = :uuid", Cliente.class)
                .setParameter("uuid", uuid)
                .getSingleResult();
    }

    public Response update(String uuid, String json) {
        try {
            Cliente cliente = findOne(uuid);
            ClienteDTO clienteDTO = gson.fromJson(json, ClienteDTO.class);

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
        Cliente cliente = findOne(uuid);
        em.remove(cliente);
        return Response.ok().build();
    }
    private void validaCliente(ClienteDTO clienteDTO) {
        ValidacaoException validacaoException = new ValidacaoException();

        if(!StringUtil.stringValida(clienteDTO.getXNome())){
            validacaoException.add("Campo nome invalido");
        }
        if(!StringUtil.stringValida(clienteDTO.getCpfCnpj())){
            validacaoException.add("Campo CPF/CNPJ invalido");
        }
        enderecoService.validaEndereco(validacaoException,clienteDTO.getEndereco(),true);

        validacaoException.lancaErro();
    }
}
