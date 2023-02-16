package org.acme.services.CobrancasService;

import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.Financas.InterestDTO;
import org.acme.services.Service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class InterestService extends Service {
    public void validaInterest(ValidacaoException validacao, InterestDTO interest) {
        if(interest != null){
            if(interest.getValue() == 0.0){
                validacao.add("Valor de juros apos vencimento invalido");
            }
            if(interest.getValue() <= 2){
                validacao.add("Juros deve ser maior que R$2.00");
            }
        }else{
            validacao.add("Valor de juros apos vencimento não informado");
        }
    }
}
