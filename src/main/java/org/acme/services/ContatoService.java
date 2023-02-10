package org.acme.services;

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
}
