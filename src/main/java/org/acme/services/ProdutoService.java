package org.acme.services;

import org.acme.Util.StringUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.ProdutoDTO;
import org.acme.models.Produto;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.util.List;

@ApplicationScoped
public class ProdutoService extends Service{



    public List<Produto> listAll() {
        return em.createQuery("SELECT p from Produto p", Produto.class).getResultList();
    }

    public Response createProduct(String json) {
        try {
            ProdutoDTO produtoDTO = gson.fromJson(json, ProdutoDTO.class);
            validaProduto(produtoDTO);
            Produto product = new Produto();
            produtoDTO.setStatus(true);
            fieldUtil.updateFieldsDtoToModel(product, produtoDTO);
            Produto produtoMerged = em.merge(product);
            product.getImposto().forEach(imposto -> {
            });
            return ResponseBuilder.responseOk(produtoMerged);
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    public void validaProduto(ProdutoDTO produtoDTO) {
        ValidacaoException validacao = new ValidacaoException();
        validaProduto(produtoDTO,validacao);
    }
    public void validaProduto(ProdutoDTO produtoDTO,ValidacaoException validacao) {

        if(produtoDTO.getPrecoUnitario() <= 0 || produtoDTO.getPrecoUnitario() == null){
            validacao.add("Preço unitario deve ser informado");
        }
        if(produtoDTO.isStatus()){
            validacao.add("Produto informado esta bloqueado");
        }
        if(!StringUtil.stringValida(produtoDTO.getNome())){
            validacao.add("Nome produto invalido");
        }

        validacao.lancaErro();
    }

    public Produto getOneProduct(String id) {
        return em.createQuery("SELECT p from Produto p WHERE uuid = :id", Produto.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public Response updateProduct(String id, String newProduct) {
        try {
            ProdutoDTO produtoDTO = gson.fromJson(newProduct, ProdutoDTO.class);
            Produto produto = new Produto();
            fieldUtil.updateFieldsDtoToModel(produto,produtoDTO);
            em.merge(produto);
            em.flush();
            return ResponseBuilder.responseOk(produto);
        }catch (ValidacaoException e){
            return ResponseBuilder.returnResponse(e);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }


    public void deleteProduct(String id) {
        em.createQuery("delete from Produto WHERE uuid = :id")
                .setParameter("id",id).executeUpdate();

    }
}
