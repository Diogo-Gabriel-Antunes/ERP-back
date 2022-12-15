package org.acme.services;

import org.acme.Util.FieldUtil;
import org.acme.models.DTO.TransportadoraDTO;
import org.acme.models.Endereco;
import org.acme.models.Transportadora;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class TransportadoraService {

    private FieldUtil fieldUtil = new FieldUtil();
    @Inject
    EntityManager em;
    public List<Transportadora> findAll() {
        return em.createQuery("SELECT t FROM Transportadora t",Transportadora.class).getResultList();
    }
    public Transportadora findOne(String uuid){
        return em.createQuery("SELECT t FROM Transportadora t WHERE uuid = :uuid",Transportadora.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public void create(TransportadoraDTO transportadoraDTO) {
        Transportadora transportadora = new Transportadora();
        fieldUtil.updateFieldsDtoToModel(transportadora,transportadoraDTO);
        Endereco enderoNoBanco = em.merge(transportadora.getEndereco());
        transportadora.setEndereco(enderoNoBanco);
        em.merge(transportadora);
    }

    public void update(String uuid, TransportadoraDTO transportadoraDTO) {
        Transportadora transportadora = findOne(uuid);
        em.merge(transportadora);
        em.merge(transportadora.getEndereco());
        fieldUtil.updateFieldsDtoToModel(transportadora,transportadoraDTO);
        em.persist(transportadora.getEndereco());
        em.persist(transportadora);
    }

    public Response delete(String uuid) {
        try{
            Transportadora transportadora = findOne(uuid);
            em.remove(transportadora);
            return Response.ok().build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
