package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.FieldUtil;
import org.acme.Util.GsonUtil;
import org.acme.models.cobranca.Assinatura.Assinatura;
import org.acme.models.DTO.AssinaturaDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;

@ApplicationScoped
public class AssinaturaService extends Service{

    @Transactional
    public Response create(String json) {
        try{
            AssinaturaDTO assinaturaDTO = gson.fromJson(json, AssinaturaDTO.class);
            Assinatura assinatura = new Assinatura();
            convertDTOS(assinatura,assinaturaDTO);
            fieldUtil.updateFieldsDtoToModel(assinatura,assinaturaDTO);
            em.persist(assinatura);
            return Response.status(Response.Status.CREATED).entity(assinatura).build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    private void convertDTOS(Assinatura assinatura, AssinaturaDTO assinaturaDTO) {

        fieldUtil.updateFieldsDtoToModel(assinatura.getSplit(),assinaturaDTO.getSplit());
        fieldUtil.updateFieldsDtoToModel(assinatura.getFine(),assinaturaDTO.getFine());
        fieldUtil.updateFieldsDtoToModel(assinatura.getDiscount(),assinaturaDTO.getDiscount());
        fieldUtil.updateFieldsDtoToModel(assinatura.getInterest(),assinaturaDTO.getInterest());
        assinaturaDTO.setFine(null);
        assinaturaDTO.setSplit(null);
        assinaturaDTO.setDiscount(null);
        assinaturaDTO.setInterest(null);
        em.persist(assinatura.getSplit());
        em.persist(assinatura.getFine());
        em.persist(assinatura.getDiscount());
        em.persist(assinatura.getInterest());
    }

    @Transactional
    public Assinatura update(String uuid, String json) {
        try{
            Optional<Assinatura> assinatura = Assinatura.findByIdOptional(uuid);
            if(!assinatura.isPresent()){
                throw new RuntimeException("Não foi encontrado nenhuma assinatura com esse uuid");
            }
            em.merge(assinatura.get());
            AssinaturaDTO assinaturaDTO = gson.fromJson(json, AssinaturaDTO.class);
            convertDTOS(assinatura.get(),assinaturaDTO);
            fieldUtil.updateFieldsDtoToModel(assinatura.get(),assinaturaDTO);
            em.persist(assinatura.get());
            return assinatura.get();
        }catch (Throwable t){
            t.printStackTrace();
            System.out.println("Erro na atualização da assinatura");
            return null;
        }
    }


}
