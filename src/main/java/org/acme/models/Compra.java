package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.CondicoesArmazenamentoETransporte;
import org.acme.models.DTO.CompraDTO;
import org.acme.models.Itens;
import org.acme.models.cobranca.BillingType;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Entity
public class Compra extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToMany
    @JoinTable(name="compra_itens", joinColumns=
            {@JoinColumn(name="compra_id")}, inverseJoinColumns=
            {@JoinColumn(name="itens_id")})
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Itens> itens;
    private String dataCompra;
    @Enumerated(EnumType.STRING)
    private BillingType formaDePagamento;
    private Long numeroDaFatura;
    private Long reciboCompra;
    @ManyToOne
    private Cliente responsavelPelaVenda;
    @OneToOne
    private Garantia garantia;
    @Cascade(CascadeType.ALL)
    @OneToOne
    private CondicoesArmazenamentoETransporte condicoesArmazenamentoETransporte;

    public Compra(CompraDTO compraDTO) {
        this.itens = compraDTO.getItens();
        this.dataCompra = compraDTO.getDataCompra();
        this.formaDePagamento = compraDTO.getFormaDePagamento();
        this.numeroDaFatura = compraDTO.getNumeroDaFatura();
        this.reciboCompra = compraDTO.getReciboCompra();
        this.garantia = compraDTO.getGarantia();
        this.condicoesArmazenamentoETransporte = compraDTO.getCondicoesArmazenamentoETransporte();
    }

    public Compra() {
    }
}