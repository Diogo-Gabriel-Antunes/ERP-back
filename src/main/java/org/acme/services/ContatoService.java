package org.acme.services;

import org.acme.Util.StringUtil;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.Contato;
import org.acme.models.DTO.ContatoDTO;
import org.acme.models.DTO.EnderecoNFEDTO;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ContatoService extends Service {

    public Contato convertDtoToModel(Contato contato, ContatoDTO contatoDTO){
        if(contato == null){
            contato = new Contato();
        }
        fieldUtil.updateFieldsDtoToModel(contato,contatoDTO);
        return contato;
    }

    public void validaContato(ValidacaoException validacao, ContatoDTO contato, boolean obrigatorio) {
        boolean validaExiste = validacao == null;
        if(validacao == null){
            validacao = new ValidacaoException();
        }
        if(contato != null){

            if(!StringUtil.stringValida(contato.getDdd())){
                validacao.add("Campo ddd invalido");
            }
            if(!StringUtil.stringValida(contato.getTelefone())){
                validacao.add("Campo telefone invalido");
            }
            if(!StringUtil.stringValida(contato.getEmail())){
                validacao.add("Campo email invalido");
            }
        }else{
            if(obrigatorio){
                validacao.add("Informações de contato invalidas");
            }
        }
        if(validaExiste){
            validacao.lancaErro();
        }
    }
}
