package org.acme.services.CobrancasService;

import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.Financas.FineDTO;
import org.acme.models.DTO.Financas.InterestDTO;
import org.acme.services.Service;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FineService extends Service {
    public void validaFine(ValidacaoException validacao, FineDTO fineDTO) {
       if(fineDTO == null){
           validacao.add("Valor de multa para pagamento apos vencimento não informado");
       }
    }
}
