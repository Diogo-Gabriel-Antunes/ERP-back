package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class Itens extends PanacheEntityBase {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @OneToOne
    private Product product;
    private Long quantidade;
    @ManyToOne
    @JsonbTransient
    @Cascade(CascadeType.SAVE_UPDATE)
    private Request pedido;
    private Double precoUnitario;
    @ManyToMany(mappedBy = "itens")
    @JsonbTransient
    private Set<Compra> compras;
}
