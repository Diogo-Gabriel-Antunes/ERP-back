package org.acme.services;

import org.acme.models.DTO.AtividadeDTO;
import org.acme.models.DTO.FuncionarioDTO;
import org.acme.models.DTO.ProjetoDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;

@ApplicationScoped
public class ProjetoService extends Service{


    public Response create(String json) {
            ProjetoDTO projetoDTO = gson.fromJson(json, ProjetoDTO.class);

            Projeto projeto = new Projeto();
            em.merge(projeto);
            if (projetoDTO.getEquipe() != null) {
                for (FuncionarioDTO funcionarioDTO: projetoDTO.getEquipe()) {
                        Funcionario funcionario = new Funcionario();
                        fieldUtil.updateFieldsDtoToModel(funcionario, funcionarioDTO);
                    Funcionario funcionarioBD = em.merge(funcionario);
                    projeto.getEquipe().add(funcionarioBD);
                    };

            }
            if (projetoDTO.getAtividades() != null) {
                for (AtividadeDTO atividadeDTO: projetoDTO.getAtividades()) {
                    Atividade atividade= new Atividade();
                    fieldUtil.updateFieldsDtoToModel(atividade, atividadeDTO);
                    Atividade atividadeBD = em.merge(atividade);
                    projeto.getAtividades().add(atividadeBD);
                };
            }

            projetoDTO.setAtividades(null);
            projetoDTO.setEquipe(null);
            fieldUtil.updateFieldsDtoToModel(projeto, projetoDTO);

            em.persist(projeto);
            em.flush();
            return Response.status(Response.Status.CREATED).entity(projeto).build();
    }

    public Response update(String uuid, String json) {
        Projeto projeto = Projeto.findById(uuid);
        ProjetoDTO projetoDTO = gson.fromJson(json,ProjetoDTO.class);
        Projeto projetoBD = Projeto.getEntityManager().merge(projeto);
        fieldUtil.updateFieldsDtoToModel(projetoBD,projetoDTO);
        projetoBD.setUltimaAtualizacao(LocalDateTime.now().toString());
        Projeto.flush();
        return Response.ok(projetoBD).build();
    }
}
