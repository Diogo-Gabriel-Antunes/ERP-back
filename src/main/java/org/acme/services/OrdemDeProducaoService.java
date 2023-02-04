package org.acme.services;

import org.acme.Util.FieldUtil;
import org.acme.models.*;
import org.acme.models.DTO.OrdemDeProducaoDTO;
import org.acme.models.enums.StatusDaProducao;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class OrdemDeProducaoService extends Service {

    @Inject
    StorageService storageService;
    public List<OrdemDeProducao> findAll(){
        return em.createQuery("SELECT o FROM OrdemDeProducao o", OrdemDeProducao.class).getResultList();
    }

    public void create(OrdemDeProducaoDTO ordemDeProducaoDTO) {
        OrdemDeProducao ordemDeProducao = new OrdemDeProducao();

        fieldUtil.updateFieldsDtoToModel(ordemDeProducao,ordemDeProducaoDTO);

        TimesOrdemDeProducao timesOrdemDeProducao = new TimesOrdemDeProducao();
        timesOrdemDeProducao.setTime(LocalDateTime.now());
        timesOrdemDeProducao.setStatusDaProducao(ordemDeProducaoDTO.getStatus());
        ordemDeProducao.setInicioDaProducao(LocalDate.now());

        OrdemDeProducao ordemDeProducaoDB = em.merge(ordemDeProducao);
        timesOrdemDeProducao.setOrdemDeProducao(ordemDeProducaoDB);
        em.merge(timesOrdemDeProducao);

    }

    public OrdemDeProducao findOne(String uuid) {
        return em.createQuery("SELECT o FROM OrdemDeProducao o WHERE uuid = :uuid",OrdemDeProducao.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public void update(String uuid, OrdemDeProducaoDTO ordemDeProducaoDTO) {
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
    }

    public void delete(String uuid) {
        OrdemDeProducao ordemDeProducao = findOne(uuid);
        em.remove(ordemDeProducao);

    }

    public void updateFinish(String uuid ) {
        OrdemDeProducao ordemDeProducao = findOne(uuid);

        em.merge(ordemDeProducao);
        Storage storage = storageService.findByProduct(ordemDeProducao.getProduct());

        em.merge(storage);
        storage.setQuantidade(storage.getQuantidade() + ordemDeProducao.getQuantidade());
        storage.setUltimaAtualizacao(LocalDate.now());

        ordemDeProducao.setFinalizadoEm(LocalDate.now());
        ordemDeProducao.setStatus(StatusDaProducao.FINALIZADO);
        TimesOrdemDeProducao timesOrdemDeProducao = new TimesOrdemDeProducao();
        timesOrdemDeProducao.setStatusDaProducao(StatusDaProducao.FINALIZADO);
        timesOrdemDeProducao.setTime(LocalDateTime.now());
        timesOrdemDeProducao.setOrdemDeProducao(ordemDeProducao);
        em.merge(timesOrdemDeProducao);

        em.persist(storage);
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
