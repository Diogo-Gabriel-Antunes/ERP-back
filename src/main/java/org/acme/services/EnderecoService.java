package org.acme.services;

import org.acme.models.DTO.EnderecoNFEDTO;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EnderecoService extends Service {

    public EnderecoNFE convertDtoToModel(EnderecoNFE endereco, EnderecoNFEDTO enderecoDTO){
        if(endereco == null){
            endereco = new EnderecoNFE();
        }
        fieldUtil.updateFieldsDtoToModel(endereco,enderecoDTO);
        em.persist(endereco);
        return endereco;
    }
}
