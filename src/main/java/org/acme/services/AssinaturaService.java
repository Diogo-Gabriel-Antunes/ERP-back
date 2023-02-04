package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;
import org.acme.models.cobranca.Assinatura.Assinatura;
import org.acme.models.DTO.AssinaturaDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class AssinaturaService {
    private Gson gson = new GsonUtil().parser;
    private FieldUtil fieldUtil = new FieldUtil();
    @Inject
    EntityManager em;



    public Response create(String json) {
        try{
            AssinaturaDTO assinaturaDTO = gson.fromJson(json, AssinaturaDTO.class);
            Assinatura assinatura = new Assinatura();

            fieldUtil.updateFieldsDtoToModel(assinatura,assinaturaDTO);
            em.persist(assinatura);
            return Response.status(Response.Status.CREATED).entity(assinatura).build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


    public Assinatura update(String uuid, String json) {
        try{
            Optional<Assinatura> assinatura = Assinatura.findByIdOptional(uuid);
            if(!assinatura.isPresent()){
                throw new RuntimeException("Não foi encontrado nenhuma assinatura com esse uuid");
            }
            em.merge(assinatura.get());
            AssinaturaDTO assinaturaDTO = gson.fromJson(json, AssinaturaDTO.class);
            updateAtributos(assinatura.get(),assinaturaDTO);
            fieldUtil.updateFieldsDtoToModel(assinatura.get(),assinaturaDTO);
            em.persist(assinatura.get());
            return assinatura.get();
        }catch (Throwable t){
            t.printStackTrace();
            System.out.println("Erro na atualização da assinatura");
            return null;
        }
    }

    private void updateAtributos(Assinatura assinatura, AssinaturaDTO assinaturaDTO) {
        fieldUtil.updateFieldsDtoToModel(assinatura.getInterest(),assinaturaDTO.getInterest());
        em.persist(assinatura.getInterest());

        fieldUtil.updateFieldsDtoToModel(assinatura.getDiscount(),assinaturaDTO.getDiscount());
        em.persist(assinatura.getDiscount());

        fieldUtil.updateFieldsDtoToModel(assinatura.getFine(),assinaturaDTO.getFine());
        em.persist(assinatura.getFine());

        fieldUtil.updateFieldsDtoToModel(assinatura.getSplit(),assinaturaDTO.getSplit());
        em.persist(assinatura.getSplit());
    }
}
