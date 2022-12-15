package org.acme.services;

import org.acme.Util.FieldUtil;
import org.acme.models.Cliente;
import org.acme.models.DTO.ClienteDTO;
import org.acme.models.Endereco;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class ClienteService {
    @Inject
    EntityManager em;
    private FieldUtil fieldUtil = new FieldUtil();

    public List<Cliente> findAll(){
        return em.createQuery("SELECT c FROM Cliente c",Cliente.class).getResultList();
    }

    public void create(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        Endereco enderecoSalvo = em.merge(clienteDTO.getEndereco());
        fieldUtil.updateFieldsDtoToModel(cliente,clienteDTO);
        cliente.setEndereco(enderecoSalvo);
        cliente.setDataCriacao(LocalDate.now());
        cliente.setUltimaAtualização(LocalDate.now());
        em.merge(cliente);
    }

    public Cliente findOne(String uuid) {
        return em.createQuery("SELECT c FROM Cliente c WHERE uuid = :uuid",Cliente.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public void update(String uuid, ClienteDTO clienteDTO) {
        Cliente cliente= findOne(uuid);

        em.merge(cliente);
        fieldUtil.updateFieldsDtoToModel(cliente,clienteDTO);
        em.persist(cliente.getEndereco());
        cliente.setUltimaAtualização(LocalDate.now());
        clienteDTO.setUltimaAtualização(LocalDate.now());
        em.persist(cliente);
        em.flush();
    }

    public Response delete(String uuid) {
        Cliente cliente = findOne(uuid);
        em.remove(cliente);
        return Response.ok().build();
    }
}
