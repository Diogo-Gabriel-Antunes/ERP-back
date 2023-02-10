package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Cliente;
import org.acme.models.CondicoesArmazenamentoETransporte;
import org.acme.models.Garantia;
import org.acme.models.Itens;
import org.acme.models.asaas.BillingType;

import java.util.List;

@Getter
@Setter
public class CompraDTO implements DTO{

    private List<Itens> itens;
    private BillingType formaDePagamento;
    private Long numeroDaFatura;
    private Long reciboCompra;
    private Cliente responsavelPelaVenda;
    private Garantia garantia;
    private CondicoesArmazenamentoETransporte condicoesArmazenamentoETransporte;

}
