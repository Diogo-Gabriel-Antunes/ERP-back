package org.acme.services;

import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.ProdutoDTO;
import org.acme.models.InformacaoDeFabricacao;
import org.acme.models.MateriaPrima;
import org.acme.models.Produto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProdutoService extends Service{



    public List<Produto> listAll() {
        return em.createQuery("SELECT p from Produto p", Produto.class).getResultList();
    }

    public Response createProduct(String json) {
            ProdutoDTO produtoDTO = gson.fromJson(json, ProdutoDTO.class);
            validaProduto(produtoDTO);
            Produto product = new Produto();
            produtoDTO.setStatus(true);
            fieldUtil.updateFieldsDtoToModel(product, produtoDTO);
            Produto produtoMerged = em.merge(product);
            infosDeFabricacaoCreate(produtoMerged,produtoDTO);
            em.flush();
            return ResponseBuilder.responseOk(produtoMerged);
    }

    @Transactional
    public void infosDeFabricacaoCreate(Produto produto, ProdutoDTO produtoDTO) {
        if(produtoDTO.getInformacaoDeFabricacao() != null && !produtoDTO.getInformacaoDeFabricacao().isEmpty()){
            produtoDTO.getInformacaoDeFabricacao().forEach(infos->{
                MateriaPrima materia = MateriaPrima.findById(infos.getMateriaPrima().getUuid());
                InformacaoDeFabricacao infosModel = new InformacaoDeFabricacao();
                fieldUtil.updateFieldsDtoToModel(infosModel,infos);
                em.persist(materia);
                infosModel.setMateriaPrima(materia);
                em.persist(infosModel);
                if(produto.getInformacaoDeFabricacao() == null){
                    produto.setInformacaoDeFabricacao(new ArrayList<>());
                }
                produto.getInformacaoDeFabricacao().add(infosModel);
                System.out.println(produto);
            });
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
            ProdutoDTO produtoDTO = gson.fromJson(newProduct, ProdutoDTO.class);
            Produto produto = new Produto();
            fieldUtil.updateFieldsDtoToModel(produto,produtoDTO);
            em.merge(produto);
            em.flush();
            return ResponseBuilder.responseOk(produto);
    }


    public void deleteProduct(String id) {
        em.createQuery("delete from Produto WHERE uuid = :id")
                .setParameter("id",id).executeUpdate();

    }
}
