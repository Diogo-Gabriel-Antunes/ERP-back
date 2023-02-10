package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
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
    @Cascade(CascadeType.SAVE_UPDATE)
    private Set<Product> products;
    private Double valorTotal;
    @Enumerated(EnumType.STRING)
    private MetodoDePagamento metodoDePagamento;
    @OneToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private Cliente cliente;
    private Double desconto;
    private Double impostosTotal;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAtualizacao;
    @PrePersist
    public void prePersist(){
        if(cliente != null){
            getCliente().setUuid(getEntityManager().merge(cliente).getUuid());
        }
        if(!products.isEmpty()){
            Set<Product> newProducts = new HashSet<>();
            products.forEach((product) ->{
                Product productMerged = getEntityManager().merge(product);
                newProducts.add(productMerged);
            });
            setProducts(newProducts);
        }
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        ultimaAtualizacao = LocalDateTime.now();
    }
}

enum MetodoDePagamento{
    PIX,BOLETO,CREDITO,DEBITO;
}