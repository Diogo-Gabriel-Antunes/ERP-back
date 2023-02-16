package org.acme.services;

import org.acme.Util.StringUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.*;
import org.acme.models.DTO.OrdemDeProducaoDTO;
import org.acme.models.enums.StatusDaProducao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class OrdemDeProducaoService extends Service {


    public List<OrdemDeProducao> findAll(){
        return em.createQuery("SELECT o FROM OrdemDeProducao o", OrdemDeProducao.class).getResultList();
    }

    public Response create(String json) {
        try {
           OrdemDeProducaoDTO ordemDeProducaoDTO = gson.fromJson(json, OrdemDeProducaoDTO.class);
            validaOrdemDeProducao(ordemDeProducaoDTO);
            OrdemDeProducao ordemDeProducao = new OrdemDeProducao();

            fieldUtil.updateFieldsDtoToModel(ordemDeProducao, ordemDeProducaoDTO);

            TimesOrdemDeProducao timesOrdemDeProducao = new TimesOrdemDeProducao();
            timesOrdemDeProducao.setTime(LocalDateTime.now());
            timesOrdemDeProducao.setStatusDaProducao(ordemDeProducaoDTO.getStatus());
            ordemDeProducao.setInicioDaProducao(LocalDate.now());

            OrdemDeProducao ordemDeProducaoDB = em.merge(ordemDeProducao);
            timesOrdemDeProducao.setOrdemDeProducao(ordemDeProducaoDB);
            em.merge(timesOrdemDeProducao);
            return ResponseBuilder.responseOk(ordemDeProducaoDB);
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }

    }

    private void validaOrdemDeProducao(OrdemDeProducaoDTO ordemDeProducaoDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if(ordemDeProducaoDTO.getStatus() == null){
            validacao.add("Campo status invalido");
        }
        if(ordemDeProducaoDTO.getQuantidade() != null){
            if(ordemDeProducaoDTO.getQuantidade() == 0 ){
                validacao.add("Campo quantidade invalido");
            }
        }else{
            validacao.add("Campo quantidade invalido");
        }
        if(!StringUtil.stringValida(ordemDeProducaoDTO.getDescricao())){
            validacao.add("Campo descrição invalido");
        }
        if(ordemDeProducaoDTO.getProduct() == null){
            validacao.add("É necessario adicionar um produto");
        }


        validacao.lancaErro();
    }

    public OrdemDeProducao findOne(String uuid) {
        return em.createQuery("SELECT o FROM OrdemDeProducao o WHERE uuid = :uuid",OrdemDeProducao.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public Response update(String uuid, String json) {
        OrdemDeProducaoDTO ordemDeProducaoDTO = gson.fromJson(json, OrdemDeProducaoDTO.class);
        OrdemDeProducao ordemDeProducao= findOne(uuid);
        TimesOrdemDeProducao timesOrdemDeProducao = new TimesOrdemDeProducao();
        timesOrdemDeProducao.setStatusDaProducao(ordemDeProducaoDTO.getStatus());
        timesOrdemDeProducao.setTime(LocalDateTime.now());
        timesOrdemDeProducao.setOrdemDeProducao(ordemDeProducao);
        em.merge(timesOrdemDeProducao);
        em.merge(ordemDeProducao);
        ordemDeProducao.setAtualizadoEm(null);
        fieldUtil.updateFieldsDtoToModel(ordemDeProducao,ordemDeProducaoDTO);
        ordemDeProducao.setInicioDaProducao(LocalDate.now());
        em.persist(ordemDeProducao);
        em.flush();
        return ResponseBuilder.responseOk(ordemDeProducao);
    }

    public void delete(String uuid) {
        OrdemDeProducao ordemDeProducao = findOne(uuid);
        em.remove(ordemDeProducao);

    }

    public void updateFinish(String uuid ) {
        OrdemDeProducao ordemDeProducao = findOne(uuid);

        em.merge(ordemDeProducao);
        Estoque estoque = estoqueService.findByProduct(ordemDeProducao.getProduto());

        em.merge(estoque);
        estoque.setQuantidade(estoque.getQuantidade() + ordemDeProducao.getQuantidade());

        ordemDeProducao.setFinalizadoEm(LocalDate.now());
        ordemDeProducao.setStatus(StatusDaProducao.FINALIZADO);
        TimesOrdemDeProducao timesOrdemDeProducao = new TimesOrdemDeProducao();
        timesOrdemDeProducao.setStatusDaProducao(StatusDaProducao.FINALIZADO);
        timesOrdemDeProducao.setTime(LocalDateTime.now());
        timesOrdemDeProducao.setOrdemDeProducao(ordemDeProducao);
        em.merge(timesOrdemDeProducao);
        em.persist(estoque);
        em.persist(ordemDeProducao);
    }

    public List<OrdemDeProducao> findByMonth() {
        LocalDate hoje = LocalDate.now();
        LocalDate umMesAtras = LocalDate.of(hoje.getYear(),hoje.getMonth().getValue()-1,hoje.getDayOfMonth());
        return em.createQuery("SELECT o FROM OrdemDeProducao o WHERE o.finalizadoEm <= :hoje AND o.finalizadoEm >= :umMesAtras ",OrdemDeProducao.class)
                .setParameter("hoje",hoje)
                .setParameter("umMesAtras",umMesAtras)
                .getResultList();
    }
}
