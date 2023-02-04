package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;
import org.acme.models.DTO.NFE.NFEDto;
import org.acme.models.Nota_fiscal_eletronica.NFe;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class NfeService extends Service{



    public Response listAll() {
        List<NFe> nfeList = em.createQuery("Select nfe from NFe nfe", NFe.class).getResultList();
        if (nfeList.size() == 0) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        return Response.ok(nfeList).build();
    }

    public Response listOne(String uuid) {
        try {
            NFe nfe = em.createQuery("Select nfe from NFe nfe where nfe.uuid = :uuid", NFe.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
            if (nfe == null) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.ok(nfe).build();
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }

    }

    @Transactional
    public Response create(String json) {
        try {
            NFe nFe = new NFe();
            NFEDto nfeDto = gson.fromJson(json, NFEDto.class);
            fieldUtil.updateFieldsDtoToModel(nFe, nfeDto);
            NFe nfeMerged = em.merge(nFe);
            return Response.ok(nfeMerged).build();
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    public Response update(String uuid, String json) {
        Response response = listOne(uuid);
        NFe nfe = (NFe) response.getEntity();
        em.merge(nfe);
        NFEDto nfeDto = gson.fromJson(json, NFEDto.class);
        fieldUtil.updateFieldsDtoToModel(nfe, nfeDto);
        em.persist(nfe);
        return Response.ok(nfe).build();
    }

    @Transactional
    public Response delete(String uuid) {
        try {
            Response response = listOne(uuid);
            NFe nFe = (NFe) response.getEntity();
            em.remove(nFe);
            return Response.ok(response.getEntity()).build();
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
