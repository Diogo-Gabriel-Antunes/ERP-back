package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.cobranca.BillingType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class ContasAPagar extends PanacheEntityBase implements Model{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String descricao;
    private double valor;
    @Enumerated(EnumType.STRING)
    private BillingType tipoDePagamento;
    @OneToOne
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Cliente cliente;
    private LocalDate dataPagamento;
    private LocalDate dataQueFoiPago;
}
