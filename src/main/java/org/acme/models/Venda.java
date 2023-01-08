package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity(name = "VENDAS")
public class Venda extends PanacheEntityBase implements Model {
    @Id

    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private LocalDate dataDaVenda;
    @ManyToMany
    @JoinTable(name="produtos_vendas", joinColumns=
            {@JoinColumn(name="vendas_id")}, inverseJoinColumns=
            {@JoinColumn(name="produtos_id")})
    private Set<Product> products;
    private Double valorTotal;
    @Enumerated(EnumType.STRING)
    private MetodoDePagamento metodoDePagamento;
    @OneToOne
    private Cliente cliente;

}

enum MetodoDePagamento{
    PIX,BOLETO,CREDITO,DEBITO;
}