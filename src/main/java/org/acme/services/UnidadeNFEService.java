package org.acme.services;

import org.acme.models.DTO.NFE.UnidadeNFEDto;
import org.acme.models.Nota_fiscal_eletronica.UnidadeNFE;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class UnidadeNFEService extends Service{

    public Response listAll() {
            List<UnidadeNFE> unidadeNFEList = em.createQuery("select u from UnidadeNFE u", UnidadeNFE.class).getResultList();
            if(unidadeNFEList.size() == 0){
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.ok(unidadeNFEList).build();
    }

    public Response listOne(String uuid) {
        try{
            UnidadeNFE unidade = em.createQuery("select u from UnidadeNFE u where u.uuid = :uuid", UnidadeNFE.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
            return Response.ok(unidade).build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    public Response create(String json) {
            UnidadeNFEDto unidadeNFEDto = gson.fromJson(json, UnidadeNFEDto.class);
            UnidadeNFE unidadeNFE = new UnidadeNFE();
            fieldUtil.updateFieldsDtoToModel(unidadeNFE,unidadeNFEDto);
            UnidadeNFE unidadeMerged = em.merge(unidadeNFE);
            return Response.ok(unidadeMerged).build();


    }
    @Transactional
    public Response update(String uuid, String json) {
        Response response = listOne(uuid);
        UnidadeNFE unidadeNFE = (UnidadeNFE) response.getEntity();
        UnidadeNFEDto unidadeNFEDto = gson.fromJson(json,UnidadeNFEDto.class);
        fieldUtil.updateFieldsDtoToModel(unidadeNFE,unidadeNFEDto);
        UnidadeNFE unidadeNFEMerged = em.merge(unidadeNFE);
        return Response.ok(unidadeNFEMerged).build();
    }
    @Transactional
    public Response delete(String uuid) {
            UnidadeNFE unidade =(UnidadeNFE) listOne(uuid).getEntity();
            em.remove(unidade);
            return Response.ok(unidade).build();

    }
}
