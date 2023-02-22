package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;
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
    private EnderecoNFE endereco;
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
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAtualizacao;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(nullable = true)
    private Loja loja;
    @ManyToMany(fetch = FetchType.EAGER)
    @JsonbTransient
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name = "funcionarios_atividade",joinColumns = {@JoinColumn(name = "funcionario_id")},inverseJoinColumns = {@JoinColumn(name = "atividade_id")})
    private List<Atividade> atividades;
    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name="funcionario_beneficios", joinColumns=
            {@JoinColumn(name="funcionario_id")}, inverseJoinColumns=
            {@JoinColumn(name="beneficio_id")})
    private Set<Beneficios> beneficios;

    @PrePersist
    public void prePersist(){
        if(endereco != null){
            this.endereco.setUuid(getEntityManager().merge(endereco).getUuid());
        }
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        ultimaAtualizacao = LocalDateTime.now();
    }

}


