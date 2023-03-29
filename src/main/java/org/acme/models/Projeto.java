package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.models.enums.StatusProjeto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class Projeto extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String nome;
    private String objetivoGeral;
    private LocalDate finalizarAte;
    private  Double orcamento;

    @ManyToMany
    @JoinTable(name = "funcionarios_projetos",joinColumns = {@JoinColumn(name = "projeto_id")},inverseJoinColumns = {@JoinColumn(name = "funcionario_id")})
    private List<Funcionario> equipe = new ArrayList<>();
    @OneToMany(mappedBy = "projeto")
    private List<Atividade> atividades = new ArrayList<>();
    private LocalDate dataDeCriacao;
    private LocalDate finalizadoEm;
    private String ultimaAtualizacao;
    @Enumerated(EnumType.STRING)
    private StatusProjeto statusProjeto;
    private Integer percConcluido;
    @OneToOne
    private Funcionario lider;
}
