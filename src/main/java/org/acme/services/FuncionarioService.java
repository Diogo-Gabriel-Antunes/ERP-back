package org.acme.services;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import org.acme.Util.FieldUtil;
import org.acme.models.DTO.FuncionarioDTO;
import org.acme.models.Endereco;
import org.acme.models.Funcionario;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.ws.rs.core.Response;

@ApplicationScoped
public class FuncionarioService {

    private EntityManager em = Funcionario.getEntityManager();
    private FieldUtil fieldUtil = new FieldUtil();
    public Response create(FuncionarioDTO funcionarioDTO){
        try{
            Funcionario funcionario = new Funcionario();


            Endereco enderecoBD = em.merge(funcionarioDTO.getEndereco());

            fieldUtil.updateFieldsDtoToModel(funcionario,funcionarioDTO);
            funcionario.setEndereco(enderecoBD);
            em.persist(enderecoBD);

            em.merge(funcionario);

            return Response.ok(funcionario).build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    public Response update(String uuid, FuncionarioDTO funcionarioDTO) {
        try{
            Endereco endereco = null;
            Funcionario funcionario = Funcionario.findById(uuid);
            if(funcionarioDTO.getEndereco() != null){
                endereco = em.merge(funcionarioDTO.getEndereco());
            }
            funcionarioDTO.setEndereco(endereco);
            funcionarioDTO.getBeneficios().addAll(funcionario.getBeneficios());

            fieldUtil.updateFieldsDtoToModel(funcionario,funcionarioDTO);
            Funcionario.persist(funcionario);
            return Response.ok(funcionario).build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    public Response delete(String uuid) {
        try{
            Funcionario.delete("uuid",uuid);
            return Response.ok().build();
        }catch (Throwable t){
            t.printStackTrace();
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
