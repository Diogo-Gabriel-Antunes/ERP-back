package org.acme.services;

import org.acme.Util.StringUtil;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.EnderecoNFEDTO;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoService extends Service {

    public EnderecoNFE convertDtoToModel(EnderecoNFE endereco, EnderecoNFEDTO enderecoDTO) {
        if (endereco == null) {
            endereco = new EnderecoNFE();
        }
        fieldUtil.updateFieldsDtoToModel(endereco, enderecoDTO);
        em.persist(endereco);
        return endereco;
    }

    public void validaEndereco(ValidacaoException validacaoException, EnderecoNFEDTO endereco,boolean obrigatorio) {
        if (endereco != null) {
            if (!StringUtil.stringValida(endereco.getCep())) {
                validacaoException.add("Campo CEP esta invalido");
            }
            if (!StringUtil.stringValida(endereco.getBairro())) {
                validacaoException.add("Campo Bairro esta invalido");
            }
            if (endereco.getEstado() != null) {
                validacaoException.add("Campo Estado esta invalido");
            }
            if (!StringUtil.stringValida(endereco.getLogradouro())) {
                validacaoException.add("Campo Endereço esta invalido");
            }
            if (!StringUtil.stringValida(endereco.getNumero())) {
                validacaoException.add("Campo Numero esta invalido");
            }
            if (!StringUtil.stringValida(endereco.getDescricaoCidade())) {
                validacaoException.add("Campo Cidade esta invalido");
            }
            if (!StringUtil.stringValida(endereco.getDescricaoPais())) {
                validacaoException.add("Campo Pais esta invalido");
            }
        }else{
            if(obrigatorio){
                validacaoException.add("Endereço é obrigatorio");
            }
        }
    }
}
