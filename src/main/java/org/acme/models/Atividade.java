package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.Prioridade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Atividade extends PanacheEntityBase implements Model{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;
    private String versao;

    private String descricao;
    private String categoria;
    private int prazo;
    private LocalDate dataDeInicio;
    private LocalDate dataDeFinalizacao;
    private String titulo;
    private int progressao;
    @ManyToOne
    private Funcionario funcionario;
}
