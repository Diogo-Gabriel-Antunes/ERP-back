package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.CondicoesArmazenamentoETransporte;
import org.acme.models.Garantia;
import org.acme.models.Itens;
import org.acme.models.cobranca.BillingType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class CompraDTO implements DTO{

    private Set<Itens> itens;
    private String dataCompra;
    @Enumerated(EnumType.STRING)
    private BillingType formaDePagamento;
    private Long numeroDaFatura;
    private Long reciboCompra;
    private Garantia garantia;
    private CondicoesArmazenamentoETransporte condicoesArmazenamentoETransporte;

}
