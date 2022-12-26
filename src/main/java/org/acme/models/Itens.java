package org.acme.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;

@Getter
@Setter
@Entity
public class Itens {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @OneToOne
    private Product product;
    private Long quantidade;
    @ManyToOne
    @JsonbTransient
    private Request pedido;
}
