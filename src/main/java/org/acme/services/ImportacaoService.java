package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;
import org.acme.models.DTO.NFE.ImportacaoDadosDTO;
import org.acme.models.Nota_fiscal_eletronica.ImportacaoDados;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class ImportacaoService {
    @Inject
    EntityManager em;
    private Gson gson = new GsonUtil().parser;
    private FieldUtil fieldUtil = new FieldUtil();

    @Transactional
    public Response listAll() {
        try {
            List<ImportacaoDados> importacaoDados = em.createQuery("select im from ImportacaoDados im", ImportacaoDados.class)
                    .getResultList();
            if (importacaoDados.size() == 0) {
                return Response.status(Response.Status.NO_CONTENT).build();
            }
            return Response.ok(importacaoDados).build();
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    public Response listOne(String uuid) {
        try {
            ImportacaoDados importacaoDados = em.createQuery("select im from ImportacaoDados im where im.uuid = :uuid", ImportacaoDados.class)
                    .setParameter("uuid", uuid)
                    .getSingleResult();
            return Response.ok(importacaoDados).build();
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    public Response create(String json) {
        try {
            ImportacaoDados importacaoDados = new ImportacaoDados();
            ImportacaoDadosDTO importacaoDadosDTO = gson.fromJson(json, ImportacaoDadosDTO.class);
            fieldUtil.updateFieldsDtoToModel(importacaoDados, importacaoDadosDTO);
            ImportacaoDados importacaoMerged = em.merge(importacaoDados);
            return Response.ok(importacaoMerged).build();
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    public Response update(String uuid, String json) {
        try {
            ImportacaoDados importacaoDados = (ImportacaoDados) listOne(uuid).getEntity();
            em.merge(importacaoDados);
            ImportacaoDadosDTO importacaoDadosDTO = gson.fromJson(json, ImportacaoDadosDTO.class);
            fieldUtil.updateFieldsDtoToModel(importacaoDados, importacaoDadosDTO);
            em.persist(importacaoDados.getDesembaraco());
            em.persist(importacaoDados.getAdquirente());
            em.persist(importacaoDados);
            return Response.ok(importacaoDados).build();
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @Transactional
    public Response delete(String uuid) {
        try {
            ImportacaoDados importacaoDados = (ImportacaoDados) listOne(uuid).getEntity();
            em.remove(importacaoDados);
            return Response.ok(importacaoDados).build();
        } catch (Throwable t) {
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
