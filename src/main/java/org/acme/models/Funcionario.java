package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.NivelDeEscolaridade;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Funcionario extends PanacheEntityBase implements Model{

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String nome;
    @OneToOne
    private Endereco endereco;
    private String pis;
    @Enumerated(EnumType.STRING)
    private NivelDeEscolaridade nivelDeEscolaridade;
    private String cpf;
    private String rg;
    private boolean valeTransporte;
    private boolean servicoMilitar;
    private boolean salarioFamilia;
    private String tituloDeEleitor;
    private String cargo;
    private String funcao;
    private LocalDate dataAdmissao;
    private LocalDate dataNascimento;
    private String nacionalidade;
    private String naturalidade;
    private Double salario;
    private boolean ativo;
    @OneToMany(mappedBy = "funcionario")
    @JsonbTransient
    private List<Atividade> atividades;
}


