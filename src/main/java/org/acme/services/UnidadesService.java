package org.acme.services;

import io.smallrye.mutiny.Uni;
import org.acme.Util.FieldUtil;
import org.acme.models.Cliente;
import org.acme.models.DTO.ClienteDTO;
import org.acme.models.DTO.UnidadeDTO;
import org.acme.models.Endereco;
import org.acme.models.Pessoa;
import org.acme.models.Unidade;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.time.LocalDate;
import java.util.List;

@ApplicationScoped
public class UnidadesService {
    @Inject
    EntityManager em;
    private FieldUtil fieldUtil = new FieldUtil();

    public List<Unidade> findAll(){
        return em.createQuery("SELECT u FROM Unidade u",Unidade.class).getResultList();
    }

    public void create(UnidadeDTO unidadeDTO) {
        Unidade unidade = new Unidade();
        fieldUtil.updateFieldsDtoToModel(unidade,unidadeDTO);
        unidade.setCriadoEm(LocalDate.now());
        unidade.setAtualizadoEm(LocalDate.now());
        Pessoa pessoaMerged = em.merge(unidade.getPessoa());
        unidade.setPessoa(pessoaMerged);
        em.merge(unidade);
    }

    public Unidade findOne(String uuid) {
        return em.createQuery("SELECT u FROM Unidade u WHERE uuid = :uuid", Unidade.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }

    public void update(String uuid, UnidadeDTO unidadeDTO) {
        Unidade unidade= findOne(uuid);
        em.merge(unidade.getPessoa());
        em.merge(unidade);
        fieldUtil.updateFieldsDtoToModel(unidade,unidadeDTO);

        unidade.setAtualizadoEm(LocalDate.now());
        unidadeDTO.setAtualizadoEm(LocalDate.now());
        em.persist(unidade.getPessoa());
        em.persist(unidade);
        em.flush();
    }

    public Response delete(String uuid) {
        Unidade unidade = findOne(uuid);
        em.remove(unidade);
        return Response.ok().build();
    }
}