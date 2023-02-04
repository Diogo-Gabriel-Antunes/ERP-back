package org.acme.services;

import org.acme.models.cobranca.CobrancaParceladaRetorno;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class CobrancaRetornoService extends Service {



    public List<CobrancaParceladaRetorno> getAll(){
        return em.createQuery("SELECT cpr FROM CobrancaParceladaRetorno cpr",CobrancaParceladaRetorno.class).getResultList();
    }
}
