package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.UnidadeDeMedida;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
public class MateriaPrima extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String nome;
    private String descricao;
    private int quantidade;
    private double precoUnitario;
    @Enumerated(EnumType.STRING)
    private UnidadeDeMedida unidadeDeMedida;
    @ManyToOne
    private Fornecedor fornecedor;
}
