package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.models.asaas.BillingType;
import org.acme.models.enums.StatusCompra;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

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
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAtualizacao;
    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        ultimaAtualizacao = LocalDateTime.now();
    }



    public Compra() {
    }

}