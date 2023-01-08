package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;
import org.acme.models.Atividade;
import org.acme.models.DTO.AtividadeDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class AtividadeService {

    private FieldUtil fieldUtil = new FieldUtil();
    private Gson gson = new GsonUtil().parser;
    public Response update(String uuid, String json){
        Atividade atividade  = Atividade.findById(uuid);
        AtividadeDTO atividadeDTO = gson.fromJson(json, AtividadeDTO.class);

        Atividade.getEntityManager().merge(atividade);
        fieldUtil.updateFieldsDtoToModel(atividade,atividadeDTO);
        Atividade.persist(atividade);
        return Response.ok(atividade).build();
    }
}
