package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.Status;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Product extends PanacheEntityBase implements Model {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String name;
    private String codeBar;
    private LocalDate dateCreate;
    private LocalDate dataAlteracao;
    private boolean status;
    @OneToMany(mappedBy = "products",fetch = FetchType.EAGER)
    private List<Imposto> imposto;
    @ManyToOne(targetEntity = Category.class)
    private Category category;
    private Double value;
    @OneToMany(mappedBy = "produto")
    private List<Imagem> imagens;
}
