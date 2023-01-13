package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;
import org.acme.models.Atividade;
import org.acme.models.DTO.AtividadeDTO;
import org.acme.repository.AtividadeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class AtividadeService {

    @Inject
    AtividadeRepository atividadeRepository;
    private Gson gson = new GsonUtil().parser;
    public Response update(String uuid, String json){
        Atividade atividade  = Atividade.findById(uuid);
        AtividadeDTO atividadeDTO = gson.fromJson(json, AtividadeDTO.class);

        atividadeRepository.create(atividade,atividadeDTO);

        return Response.ok(atividade).build();
    }
}
