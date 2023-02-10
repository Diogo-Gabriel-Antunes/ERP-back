package org.acme.services.CobrancasService;

import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.Financas.DiscountDTO;
import org.acme.models.DTO.Financas.InterestDTO;
import org.acme.services.Service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DiscountService extends Service {
    public void validaDiscount(ValidacaoException validacao, DiscountDTO discount) {
        if(discount != null){
            if(discount.getValue() == 0.0){
                validacao.add("Valor de juros apos vencimento invalido");
            }
            if(discount.getDescontType() == null){
                validacao.add("Tipo do desconto não informado");
            }
        }else{
            validacao.add("Valor de desconto não informado");
        }
    }
}
