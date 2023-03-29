package org.acme.models.asaas.Assinatura;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.models.asaas.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Assinatura extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String customer;
    @Enumerated(EnumType.STRING)
    private BillingType billingType;
    private double value;
    private String nextDueDate;
    private String description;
    private String endDate;
    private String maxPayments;
    private String externalReference;
    @ManyToOne
    private Split split;
    @ManyToOne
    private Discount discount;
    @ManyToOne
    private Interest interest;
    @ManyToOne
    private Fine fine;
    @Enumerated(EnumType.STRING)
    private SubscriptionCycle cycle;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAtualizacao;

    public Assinatura() {
        this.split = new Split();
        this.discount = new Discount();
        this.interest = new Interest();
        this.fine = new Fine();
    }
    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        ultimaAtualizacao = LocalDateTime.now();
    }
}
