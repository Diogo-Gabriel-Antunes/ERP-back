package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.CondicoesArmazenamentoETransporte;
import org.acme.models.DTO.CompraDTO;
import org.acme.models.Itens;
import org.acme.models.cobranca.BillingType;
import org.acme.models.enums.StatusCompra;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Compra extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToMany(cascade = javax.persistence.CascadeType.ALL)
    @JoinTable(name="compra_itens", joinColumns=
            {@JoinColumn(name="compra_id")}, inverseJoinColumns=
            {@JoinColumn(name="itens_id")})
    @Cascade(CascadeType.ALL)
    private List<Itens> itens ;
    private String dataCompra;
    @Enumerated(EnumType.STRING)
    private BillingType formaDePagamento;
    private Long numeroDaFatura;
    private Long reciboCompra;
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL)
    @Cascade(CascadeType.ALL)
    private Cliente responsavelPelaVenda;
    @OneToOne(cascade = javax.persistence.CascadeType.ALL)
    @Cascade(CascadeType.ALL)
    private Garantia garantia;
    @Cascade(CascadeType.MERGE)
    @OneToOne(cascade = javax.persistence.CascadeType.MERGE)
    private CondicoesArmazenamentoETransporte condicoesArmazenamentoETransporte;
    @Enumerated(EnumType.STRING)
    private StatusCompra statusCompra;

    public Compra(CompraDTO compraDTO) {
        this.dataCompra = compraDTO.getDataCompra();
        this.formaDePagamento = compraDTO.getFormaDePagamento();
        this.numeroDaFatura = compraDTO.getNumeroDaFatura();
        this.reciboCompra = compraDTO.getReciboCompra();
        this.garantia = compraDTO.getGarantia();
        this.responsavelPelaVenda = compraDTO.getResponsavelPelaVenda();
        this.condicoesArmazenamentoETransporte = compraDTO.getCondicoesArmazenamentoETransporte();
    }

    public Compra() {
    }
}