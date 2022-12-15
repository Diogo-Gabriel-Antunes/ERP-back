package org.acme.controller;

import com.google.gson.Gson;
import org.acme.Util.GsonUtil;
import org.acme.models.Cliente;
import org.acme.models.DTO.ClienteDTO;
import org.acme.models.Endereco;
import org.acme.services.ClienteService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/clientes")
@ApplicationScoped
public class ClienteController {

    @Inject
    private ClienteService clienteService;
    private Gson gson = new GsonUtil().parser;
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> listAll(){
        return clienteService.findAll();
//        Cliente cliente = new Cliente();
//        cliente.setXNome("teste");
//        cliente.setIe("teste");
//        cliente.setIm("teste");
//        cliente.setEmail("teste");
//        cliente.setCpf("teste");
//        cliente.setCnpj("teste");
//        cliente.setIsuf("teste");
//        cliente.setIdEstrangeiro("teste");
//        cliente.setIndIEDest("teste");
//        Endereco endereco = new Endereco();
//        endereco.setXEnder("Adilia");
//        endereco.setXLgr("teste");
//        endereco.setNro("teste");
//        endereco.setXCpl("teste");
//        endereco.setXBairro("teste");
//        endereco.setCMun("teste");
//        endereco.setXMun("Joinville");
//        endereco.setUf("SC");
//        endereco.setCEP("89229129");
//        endereco.setCPais("Brasil");
//        endereco.setXPais("Brasil");
//        cliente.setEndereco(endereco);
//        return List.of(cliente);
    }

    @GET
    @Path("{uuid}")
    public ClienteDTO listOne(@PathParam("uuid")String uuid){
        Cliente cliente = clienteService.findOne(uuid);
        return ClienteDTO.convert(cliente);
    }
    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public ClienteDTO create(String json){
        ClienteDTO clienteDTO = gson.fromJson(json, ClienteDTO.class);
        clienteService.create(clienteDTO);
        return clienteDTO;
    }

    @PUT
    @Path("{uuid}")
    @Transactional
    public ClienteDTO update(@PathParam("uuid")String uuid,String json){
        ClienteDTO clienteDTO = gson.fromJson(json, ClienteDTO.class);
        clienteService.update(uuid,clienteDTO);
        return clienteDTO;
    }
    @DELETE
    @Path("{uuid}")
    @Transactional
    public Response delete(@PathParam("uuid")String uuid){

        return clienteService.delete(uuid);
    }
}
