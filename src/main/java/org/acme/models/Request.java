package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Request extends PanacheEntityBase implements Model{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String numberRequest;
    @OneToMany(mappedBy = "pedido")
    private List<Itens> itens;
    @OneToOne
    private Cliente cliente;
    private LocalDate createDate;
    private LocalDate finishDate;
    @ManyToOne
    private StatusRequest status;
    private Double value;
}
