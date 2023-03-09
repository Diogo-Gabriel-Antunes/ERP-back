package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.DateUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.Categoria;
import org.acme.models.DTO.*;
import org.acme.models.Estoque;
import org.acme.models.Produto;
import org.acme.models.consulta.ProdutoEstoque;
import org.acme.models.enums.StatusDaProducao;
import org.acme.repository.ProdutoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.Response;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@ApplicationScoped
public class EstoqueService extends Service {

    @Inject
    ProdutoRepository produtoRepository;

    public Response create(String json) {
        try {
            ProdutoDTO produtoDTO = gson.fromJson(json, ProdutoDTO.class);
            validaProduto(produtoDTO);
            Estoque estoque = new Estoque();
            Produto produto = new Produto();
            fieldUtil.updateFieldsDtoToModel(produto, produtoDTO);
            estoque.setProduto(produto);
            estoque.setQuantidade(0L);
            Categoria categoria = categoryService.getOne(produtoDTO.getCategoria().getUuid());
            Categoria categoriaMerged = em.merge(categoria);
            produto.setCategoria(categoriaMerged);
            em.persist(categoria);
            produtoService.infosDeFabricacaoCreate(produto,produtoDTO);
            em.persist(produto);
            em.persist(estoque);
            em.flush();
            return ResponseBuilder.responseOk(em.merge(estoque));
        }catch (JsonSyntaxException n){
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    private void validaProduto(ProdutoDTO produtoDTO) {
        ValidacaoException validacao = new ValidacaoException();

        if(!StringUtil.stringValida(produtoDTO.getNome())){
            validacao.add("O campo nome esta invalido");
        }
        if(produtoDTO.getPrecoUnitario() == null){
            validacao.add("O campo preço unitario esta invalido");
        }
        if(produtoDTO.getPrecoUnitario()<=0){
            validacao.add("O preço deve ser maior que 0");
        }
        if(!StringUtil.stringValida(produtoDTO.getCategoria().getUuid())){
            validacao.add("O campo categoria esta invalido");
        }
        if(produtoDTO.getQuantidadeMinima() <= 0){
            validacao.add("O campo quantidade minima esta invalido ");
        }
        if(produtoDTO.getPesoCubico() == null || produtoDTO.getPesoCubico() <= 0){
            validacao.add("Campo peso cubico esta invalido");
        }

        validacao.lancaErro();
    }

    public List<Estoque> findAll() {
        return em.createQuery("SELECT s FROM Estoque s", Estoque.class)
                .getResultList();
    }

    public Estoque findOne(String uuid) {
        return em.createQuery("SELECT s FROM Estoque s WHERE uuid = :id", Estoque.class)
                .setParameter("id", uuid)
                .getSingleResult();
    }

    public Response update(String uuid, String json) {
        try {
            Estoque estoque = findOne(uuid);
            EstoqueDTO estoqueDTO = gson.fromJson(json, EstoqueDTO.class);
            fieldUtil.updateFieldsDtoToModel(estoque, estoqueDTO);
            em.persist(estoque);
            return ResponseBuilder.responseOk(estoque);
        } catch (ValidacaoException e) {
            return ResponseBuilder.returnResponse(e);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }

    }

    public Estoque findByProduct(Produto produto) {
        return em.createQuery("SELECT e FROM Estoque e WHERE e.produto.uuid = :uuid", Estoque.class)
                .setParameter("uuid", produto.getUuid()).getSingleResult();
    }

    public List<Estoque> findMonth() {
        LocalDate hoje = LocalDate.now();
        try {
            LocalDate umMesAtras = LocalDate.of(hoje.getYear(), hoje.getMonth().getValue() - 1, hoje.getDayOfMonth() - 1);
            if (new DateUtil().validaData(hoje)) {
                return em.createQuery("SELECT s FROM Estoque s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras", Estoque.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras", umMesAtras)
                        .getResultList();
            } else {
                return em.createQuery("SELECT s FROM Estoque s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras", Estoque.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras", umMesAtras)
                        .getResultList();
            }
        } catch (DateTimeException e) {
            LocalDate umMesAtras = LocalDate.of(hoje.getYear() - 1, 12, hoje.getDayOfMonth() - 1);
            if (new DateUtil().validaData(hoje)) {
                return em.createQuery("SELECT s FROM Estoque s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras", Estoque.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras", umMesAtras)
                        .getResultList();
            } else {
                return em.createQuery("SELECT s FROM Estoque s WHERE s.ultimaAtualizacao <= :hoje AND s.ultimaAtualizacao >= :umMesAtras", Estoque.class)
                        .setParameter("hoje", hoje)
                        .setParameter("umMesAtras", umMesAtras)
                        .getResultList();
            }
        }


    }


    public void validaQuantidadeMinima(ItensDTO item) {
        ValidacaoException validacaoException = new ValidacaoException();
        ProdutoEstoque produtoEstoque = produtoRepository.findProdutoEstoque(item);

        if(item.getQuantidade() >= produtoEstoque.getEstoque().getQuantidade()){
            if(item.getQuantidade() - produtoEstoque.getEstoque().getQuantidade() <= produtoEstoque.getProduto().getQuantidadeMinima()){
                validacaoException.add("Opa, Seu estoque esta baixo");
            }
        }else{
            OrdemDeProducaoDTO ordemDeProducaoDTO = new OrdemDeProducaoDTO();
            ordemDeProducaoDTO.setQuantidade(item.getQuantidade() - produtoEstoque.getEstoque().getQuantidade());
            ordemDeProducaoDTO.setDescricao("Gerado apartir de um pedido");
            ordemDeProducaoDTO.setStatus(StatusDaProducao.FORNECEDOR);
            ordemDeProducaoDTO.setInicioDaProducao(LocalDate.now());
            ordemDeProducaoDTO.setProduct(item.getProduto());

            TimesOrdemDeProducaoDTO timesOrdemDeProducaoDTO = new TimesOrdemDeProducaoDTO();
            timesOrdemDeProducaoDTO.setOrdemDeProducao(ordemDeProducaoDTO);
            timesOrdemDeProducaoDTO.setStatusDaProducao(StatusDaProducao.FORNECEDOR);
            timesOrdemDeProducaoDTO.setTime(LocalDateTime.now());

            ordemDeProducaoDTO.getAtualizadoEm().add(timesOrdemDeProducaoDTO);
            ordemDeProducaoService.create(gson.toJson(ordemDeProducaoDTO));
        }
    }
}