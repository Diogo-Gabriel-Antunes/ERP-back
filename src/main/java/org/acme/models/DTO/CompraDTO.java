package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Cliente;
import org.acme.models.CondicoesArmazenamentoETransporte;
import org.acme.models.Garantia;
import org.acme.models.Itens;
import org.acme.models.asaas.BillingType;

import java.util.List;

@Getter
@Setter
public class CompraDTO implements DTO {

    @LabelForm("Itens")
    private List<Itens> itens;
    private BillingType formaDePagamento;
    private Long numeroDaFatura;
    private Long reciboCompra;
    @LabelForm("Responsavel pela venda")
    private Cliente responsavelPelaVenda;
    private Garantia garantia;
    private CondicoesArmazenamentoETransporte condicoesArmazenamentoETransporte;

}
