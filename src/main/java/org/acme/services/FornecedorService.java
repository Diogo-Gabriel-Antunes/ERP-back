package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.StringUtil;
import org.acme.exceptions.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.FornecedorDTO;
import org.acme.models.Fornecedor;
import org.acme.models.MateriaPrima;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;
import org.acme.models.Produto;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@ApplicationScoped
public class FornecedorService extends Service{
    @Transactional
    public Response create(String json) {
        try{
            FornecedorDTO fornecedorDTO = gson.fromJson(json, FornecedorDTO.class);
            validaFornecedor(fornecedorDTO);
            Fornecedor fornecedor = new Fornecedor();
            convertDTOtoModel(fornecedorDTO,fornecedor);
            fornecedor.persistAndFlush();
            return ResponseBuilder.responseOk(fornecedor);
        }catch (JsonSyntaxException j){
            j.printStackTrace();
            return ResponseBuilder.returnJsonSyntax();
        }catch (ValidacaoException v){
            return ResponseBuilder.returnResponse(v);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }
    @Transactional
    public Response update(String uuid, String json) {
        try{
            FornecedorDTO fornecedorDTO = gson.fromJson(json, FornecedorDTO.class);
            validaFornecedor(fornecedorDTO);
            Fornecedor fornecedor = getOne(uuid);
            convertDTOtoModel(fornecedorDTO,fornecedor);
            fornecedor.persistAndFlush();
            return ResponseBuilder.responseOk(fornecedor);
        }catch (JsonSyntaxException j ){
            return ResponseBuilder.returnJsonSyntax();
        }catch (ValidacaoException v){
            return ResponseBuilder.returnResponse(v);
        }catch (Throwable t){
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }
    }

    public Fornecedor getOne(String uuid){
        return Fornecedor.findById(uuid);
    }
    private void convertDTOtoModel(FornecedorDTO fornecedorDTO, Fornecedor fornecedor) {
        Set<Produto> produtos = new HashSet<>();
        Set<MateriaPrima> materiaPrimas = new HashSet<>();

        if(fornecedorDTO.getProdutos() != null){
            if(!fornecedorDTO.getProdutos().isEmpty()){
                fornecedorDTO.getProdutos().forEach(produto->{
                    Produto produtoBD = produtoService.getOneProduct(produto.getUuid());
                    Produto produtoMerged = em.merge(produtoBD);
                    produtos.add(produtoMerged);
                });
                fornecedor.setProdutos(produtos);
            }
        }
        if(fornecedorDTO.getMateriaPrimas() != null){
            if(!fornecedorDTO.getMateriaPrimas().isEmpty()){
                fornecedorDTO.getMateriaPrimas().forEach(materiaPrima->{
                    MateriaPrima materiaPrimaBD = materiaPrimaService.findOne(materiaPrima.getUuid());
                    MateriaPrima materiaPrimaMerged = em.merge(materiaPrimaBD);
                    materiaPrimas.add(materiaPrimaMerged);
                });
                fornecedor.setMateriaPrimas(materiaPrimas);
            }
        }
        EnderecoNFE endereco = new EnderecoNFE();
        fieldUtil.updateFieldsDtoToModel(fornecedor,fornecedorDTO);
        fieldUtil.updateFieldsDtoToModel(endereco,fornecedorDTO.getEndereco());
        fornecedor.setEndereco(endereco);
        fornecedor.setProdutos(produtos);
        fornecedor.setMateriaPrimas(materiaPrimas);
        em.persist(fornecedor.getEndereco());

    }

    private void validaFornecedor(FornecedorDTO fornecedorDTO) {
        ValidacaoException validacao = new ValidacaoException();

        enderecoService.validaEndereco(validacao,fornecedorDTO.getEndereco(),true);

        if(fornecedorDTO.getProdutos() != null || fornecedorDTO.getMateriaPrimas() != null){
            if(fornecedorDTO.getProdutos().isEmpty() && fornecedorDTO.getMateriaPrimas().isEmpty()){
                validacao.add("Alguma materia prima ou produto deve ser informado");
            }
        }else{
            validacao.add("Produto ou materia prima deve ser informado");
        }
        if(!StringUtil.stringValida(fornecedorDTO.getNomeDaEmpresa())){
            validacao.add("Nome da empresa invalida");
        }
        if(!StringUtil.stringValida(fornecedorDTO.getNomeParaContato())){
            validacao.add("Um nome para contato deve ser informado");
        }
        if(!StringUtil.stringValida(fornecedorDTO.getInscricaoMunicipal())){
            validacao.add("Inscrição estudal esta invalido");
        }
        if(!StringUtil.stringValida(fornecedorDTO.getInscricaoEstadual())){
            validacao.add("Inscrição estudal esta invalido");
        }
        validacao.lancaErro();
    }


}
