package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.Status;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CascadeType;
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
    private String nome;
    private String codigoDeBarras;
    private LocalDate dataCriacao;
    private LocalDate dataAlteracao;
    private boolean status;
    @OneToMany(mappedBy = "products",cascade = javax.persistence.CascadeType.ALL,fetch = FetchType.LAZY)
    @Cascade(CascadeType.ALL)
    @JsonbTransient
    private List<Imposto> imposto;
    @ManyToOne(targetEntity = Category.class,cascade = javax.persistence.CascadeType.ALL)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Category categoria;
    @OneToMany(mappedBy = "produto",fetch = FetchType.EAGER)
    @JsonbTransient
    private List<Imagem> imagens;
    private Double precoUnitario;
    private Double precoForncedor;

}
