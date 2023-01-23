package org.acme.services;

import org.acme.Util.FieldUtil;
import org.acme.models.DTO.TransportadorDTO;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;
import org.acme.models.Nota_fiscal_eletronica.Transportador;

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
    public List<Transportador> findAll() {
        return em.createQuery("SELECT t FROM Transportador t", Transportador.class).getResultList();
    }
    public Transportador findOne(String uuid){
        return em.createQuery("SELECT t FROM Transportador t WHERE uuid = :uuid",Transportador.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public void create(TransportadorDTO transportadorDTO) {
        Transportador transportador = new Transportador();
        fieldUtil.updateFieldsDtoToModel(transportador,transportadorDTO);
        EnderecoNFE enderoNoBanco = em.merge(transportador.getEndereco());
        transportador.setEndereco(enderoNoBanco);
        em.merge(transportador);
    }

    public void update(String uuid, TransportadorDTO transportadorDTO) {
        Transportador transportador = findOne(uuid);
        em.merge(transportador);
        em.merge(transportador.getEndereco());
        fieldUtil.updateFieldsDtoToModel(transportador,transportadorDTO);
        em.persist(transportador.getEndereco());
        em.persist(transportador);
    }

    public Response delete(String uuid) {
        try{
            Transportador transportador = findOne(uuid);
            em.remove(transportador);
            return Response.ok().build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
