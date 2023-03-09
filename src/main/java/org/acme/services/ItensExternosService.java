package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.DateUtil;
import org.acme.Util.JsonUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.Cliente;
import org.acme.models.DTO.ItensDTO;
import org.acme.models.ItensExternos;
import org.acme.models.Produto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ItensExternosService extends Service {


    @Transactional
    public Response create(String json){
            ItensDTO itensDTO = gson.fromJson(json, ItensDTO.class);
            validaDTO(itensDTO);
            ItensExternos itensExternos = new ItensExternos();
            convertDtoToModel(itensExternos,itensDTO);
            em.persist(itensExternos);
            return ResponseBuilder.responseOk(itensExternos);
    }
    @Transactional
    public Response update(String uuid, String json) {

            ItensDTO itensDTO = gson.fromJson(json, ItensDTO.class);
            validaDTO(itensDTO);
            ItensExternos itensExternos = new ItensExternos();
            convertDtoToModel(itensExternos,itensDTO);
            em.persist(itensExternos);
            return ResponseBuilder.responseOk(itensExternos);
    }
    private void validaDTO(ItensDTO itensDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if(itensDTO.getProduto() == null){
            validacao.add("Deve ser informado um produto");
        }
        if(itensDTO.getQuantidade() < 0  ){
            validacao.add("A quantidade não pode ser menor que zero");
        }
        if(itensDTO.getQuantidade() == null){
            validacao.add("A quantidade deve ser informada");
        }
        if(itensDTO.getCliente() == null){
            validacao.add("A quantidade deve ser informada");
        }

        validacao.lancaErro();
    }

    public List<ItensExternos> findAll() {
        return em.createQuery("SELECT s FROM ItensExternos s", ItensExternos.class)
                .getResultList();
    }

    public ItensExternos findOne(String uuid) {
        return em.createQuery("SELECT s FROM ItensExternos s WHERE uuid = :uuid",ItensExternos.class)
                .setParameter("uuid",uuid)
                .getSingleResult();
    }


    public ItensExternos findByProduct(String productUuid){
        return em.createQuery("SELECT e FROM ItensExternos e WHERE e.produto.uuid = :uuid", ItensExternos.class)
                .setParameter("uuid", productUuid).getSingleResult();
    }

    public List<ItensExternos> findMonth() {
        LocalDate hoje = LocalDate.now();
        try{
            LocalDate umMesAtras = LocalDate.of(hoje.getYear(),hoje.getMonth().getValue() -1 , hoje.getDayOfMonth()-1);
            if(new DateUtil().validaData(hoje)){
                return em.createQuery("SELECT s FROM ItensExternos s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",ItensExternos.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }else{
                return em.createQuery("SELECT s FROM ItensExternos s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",ItensExternos.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }
        }catch (DateTimeException e){
            LocalDate umMesAtras = LocalDate.of(hoje.getYear()-1,12 , hoje.getDayOfMonth()-1);
            if(new DateUtil().validaData(hoje)){
                return em.createQuery("SELECT s FROM ItensExternos s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",ItensExternos.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }else{
                return em.createQuery("SELECT s FROM ItensExternos s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras",ItensExternos.class)
                        .setParameter("hoje",hoje)
                        .setParameter("umMesAtras",umMesAtras)
                        .getResultList();
            }
        }


    }

    public void convertDtoToModel(List<ItensExternos> itens, ItensDTO itensDTO){
        if(itens == null){
            itens = new ArrayList<ItensExternos>();
        }
        ItensExternos itensModel = new ItensExternos();

        fieldUtil.updateFieldsDtoToModel(itensModel,itensDTO);
        itens.add(itensModel);
        em.persist(itensModel);
    }
    public void convertDtoToModel(ItensExternos itens, ItensDTO itensDTO){
        fieldUtil.updateFieldsDtoToModel(itens,itensDTO);
        if(itensDTO.getProduto() != null){
            Produto produto = Produto.findById(itensDTO.getProduto().getUuid());
            itens.setProduto(produto);
        }
        if(itensDTO.getCliente() != null){
            Cliente clienteBD = Cliente.findById(itensDTO.getCliente().getUuid());
            itens.setCliente(clienteBD);
        }
        if(itensDTO.getQuantidade() == null){
            itens.setQuantidade(0l);
        }
    }

}
