package org.acme.services;

import org.acme.models.Nota_fiscal_eletronica.Veiculo;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class VeiculoService extends Service{


    public Response listAll(){
        try{
            List<Veiculo> veiculos = em.createQuery("select v from Veiculo v", Veiculo.class)
                    .getResultList();
            return Response.ok(veiculos).build();
        }catch (Throwable t){
            return Response.status(Response.Status.BAD_REQUEST).build();
        }


    }
}
