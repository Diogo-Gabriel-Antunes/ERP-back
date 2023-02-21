package org.acme.services;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.StringUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.Validacao;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.MateriaPrimaDTO;
import org.acme.models.MateriaPrima;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;


@ApplicationScoped
public class MateriaPrimaService extends Service{

    @Transactional
    public Response create(String json) {
        try{
            MateriaPrimaDTO dto = gson.fromJson(json, MateriaPrimaDTO.class);
              validacaoMetariaPrima(dto);
            MateriaPrima materiaPrima = new MateriaPrima();
            fieldUtil.updateFieldsDtoToModel(materiaPrima,dto);
            em.persist(materiaPrima);
            return ResponseBuilder.responseOk(materiaPrima);
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    private void validacaoMetariaPrima(MateriaPrimaDTO dto) {
        ValidacaoException validacao = new ValidacaoException();

        if(!StringUtil.stringValida(dto.getNome())){
            validacao.add("Campo nome invalido");
        }
        if(!StringUtil.stringValida(dto.getDescricao())){
            validacao.add("Campo nome invalido");
        }
        if(dto.getQuantidade() <= 0 ){
            validacao.add("Campo quantidade invalido");
            validacao.add("Campo quantidade deve ser maior ou igual a 0");
        }
        if(dto.getPrecoUnitario() < 0){
            validacao.add("Preco unitario deve ser maior que 0");
        }

        validacao.lancaErro();

    }

    public Response update(String uuid, String json) {
        try{
            Optional<MateriaPrima> materiaPrima = MateriaPrima.findByIdOptional(uuid);
            if(materiaPrima.isPresent()){
                MateriaPrima materiaBD = em.merge(materiaPrima.get());
                MateriaPrimaDTO materiaPrimaDTO = gson.fromJson(json, MateriaPrimaDTO.class);
                fieldUtil.updateFieldsDtoToModel(materiaBD,materiaPrimaDTO);
                em.persist(materiaPrima);
                return ResponseBuilder.responseOk(materiaBD);
            }
            throw new RuntimeException();
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    public Response delete(String uuid) {
        try {
            MateriaPrima materiaPrima = MateriaPrima.findById(uuid);
            if(materiaPrima != null){
                materiaPrima.delete();
                return ResponseBuilder.responseOk(materiaPrima);
            }
            throw new RuntimeException();
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    public MateriaPrima findOne(String uuid) {
        return em.createQuery("SELECT m FROM MateriaPrima m WHERE m.uuid = :uuid",MateriaPrima.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }
}
