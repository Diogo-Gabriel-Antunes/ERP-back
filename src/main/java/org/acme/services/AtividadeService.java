package org.acme.services;

import org.acme.Util.DataUtil;
import org.acme.Util.IntegerUtil;
import org.acme.Util.PrimitiveUtil.ArrayUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.AtividadeDTO;
import org.acme.models.enums.Prioridade;
import org.acme.repository.AtividadeRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class AtividadeService extends Service {

    @Inject
    AtividadeRepository atividadeRepository;

    public Response update(String uuid, String json) {
            AtividadeDTO atividadeDTO = gson.fromJson(json, AtividadeDTO.class);
            validaAtividade(null, atividadeDTO);
            Atividade atividade = Atividade.findById(uuid);
            List<Funcionario> newList = new ArrayList<>();
            atividadeDTO.getFuncionario().forEach(funcionarioDTO -> {
                Funcionario funcionario = new Funcionario();
                fieldUtil.updateFieldsDtoToModel(funcionario, funcionarioDTO);
                Funcionario funcionarioBD = em.merge(funcionario);
                newList.add(funcionarioBD);
            });
            atividade.getFuncionario().addAll(newList);
            em.flush();
            em.persist(atividade);

            return ResponseBuilder.responseOk(atividade);

    }

    @Transactional
    public Response create(String json) {
            AtividadeDTO atividadeDTO = gson.fromJson(json, AtividadeDTO.class);
            validaAtividade(null, atividadeDTO);
            Atividade atividade = new Atividade();
            List<Funcionario> funcionarioList = new ArrayList<>();
            atividadeDTO.getFuncionario().forEach(funcionarioDTO -> {
                Funcionario funcionario = new Funcionario();
                fieldUtil.updateFieldsDtoToModel(funcionario, funcionarioDTO);
                Funcionario funcionarioBD = em.merge(funcionario);
                funcionarioList.add(funcionarioBD);
            });
            atividade.setFuncionario(funcionarioList);
            fieldUtil.updateFieldsDtoToModel(atividade,atividadeDTO);
            em.persist(atividade);
            return ResponseBuilder.responseOk(atividade);
    }

    private void validaAtividade(ValidacaoException validacao, AtividadeDTO atividadeDTO) {
        boolean lancarErro = validacao == null;
        if (validacao == null) {
            validacao = new ValidacaoException();
        }

        if (!StringUtil.stringValida(atividadeDTO.getDescricao())) {
            validacao.add("Campo descrição invalido");
        }
        if (!StringUtil.stringValida(atividadeDTO.getCategoria())) {
            validacao.add("Campo categoria invalido");
        }
        if (!IntegerUtil.integerValidoMaiorQueZero(atividadeDTO.getPrazo())) {
            validacao.add("Campo prazo invalido");
        }
        if (atividadeDTO.getDataDeInicio() == null) {
            validacao.add("Campo data de inicio invalido");
        }
        if (!StringUtil.stringValida(atividadeDTO.getTitulo())) {
            validacao.add("Campo titulo invalido");
        }
        if (!DataUtil.validaLocalDateDentroDesseAno(atividadeDTO.getDataDeInicio())) {
            validacao.add("Data de inicio invalida");
        }
        if (!validaPropriedade(atividadeDTO.getPrioridade())) {
            validacao.add("Prioridade é obrigatorio");
        }
        if (!StringUtil.stringValida(atividadeDTO.getVersao())) {
            validacao.add("Versão invalido");
        }
        if (ArrayUtil.validaArray(atividadeDTO.getFuncionario())) {
            validacao.add("É obrigatorio a inserção de um funcionario");
        }

        if (lancarErro) {
            validacao.lancaErro();
        }

    }

    private boolean validaPropriedade(Prioridade prioridade) {
        if (prioridade != null) {
            return Prioridade.BAIXA.equals(prioridade) || Prioridade.ALTA.equals(prioridade)
                    || Prioridade.MEDIA.equals(prioridade) || Prioridade.URGENTE.equals(prioridade);
        } else {
            return false;
        }
    }
}
