package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.FieldUtil;
import org.acme.models.Cliente;
import org.acme.models.ContasAPagar;
import org.acme.models.cobranca.BillingType;

import java.time.LocalDate;

@Getter
@Setter
public class ContasAPagarDTO implements DTO {
    private String descricao;
    private double valor;
    private BillingType tipoDePagamento;
    private Cliente cliente;
    private LocalDate dataPagamento;
    private LocalDate dataQueFoiPago;

    public static ContasAPagarDTO convert(ContasAPagar contasAPagar) {
        ContasAPagarDTO contasAPagarDTO = new ContasAPagarDTO();
        FieldUtil fieldUtil = new FieldUtil();
        contasAPagar.setUuid(null);
        fieldUtil.updateFieldsModelToDTO(contasAPagar,contasAPagarDTO);
        return contasAPagarDTO;
    }
}
