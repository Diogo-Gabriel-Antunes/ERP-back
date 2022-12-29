package org.acme.controller;

import org.acme.models.cobranca.BillingType;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@Path("/tiposdepagamento")
public class TiposDePagamentoController {

    @GET
    public List<BillingType> listAll(){
        List<BillingType> billingTypes = new ArrayList<>();
        billingTypes.add(BillingType.CREDIT_CARD);
        billingTypes.add(BillingType.PIX);
        billingTypes.add(BillingType.BOLETO);
        billingTypes.add(BillingType.UNDEDFINED);
        return billingTypes;
    }
}
