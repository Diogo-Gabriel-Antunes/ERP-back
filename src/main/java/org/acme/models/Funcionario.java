package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.NivelDeEscolaridade;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CascadeType;
import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

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
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
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
    @ManyToMany
    @JsonbTransient
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "funcionarios_atividade",joinColumns = {@JoinColumn(name = "funcionario_id")},inverseJoinColumns = {@JoinColumn(name = "atividade_id")})
    private List<Atividade> atividades;

    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name="funcionario_beneficios", joinColumns=
            {@JoinColumn(name="funcionario_id")}, inverseJoinColumns=
            {@JoinColumn(name="beneficio_id")})
    private Set<Beneficios> beneficios;
}


