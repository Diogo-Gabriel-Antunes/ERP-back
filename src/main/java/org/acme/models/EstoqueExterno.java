package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeSerializer;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class EstoqueExterno extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToMany
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    @JoinTable(name="estoque_externo_itens", joinColumns=
            {@JoinColumn(name="estoqueExterno_id")}, inverseJoinColumns=
            {@JoinColumn(name="itens_id")})

    private Set<ItensExternos> itens;
    @OneToOne
    private Cliente cliente;
    @OneToOne
    private Funcionario responsavel;
    private LocalDateTime ultimaAtualizacao;
    private LocalDateTime criadoEm;

    public EstoqueExterno() {
        itens = new HashSet<>();
    }

    @JsonbTransient
    public Set<ItensExternos> getItens(){
        return this.itens;
    }
    @PrePersist
    public void prePersis(){
        ultimaAtualizacao = LocalDateTime.now();
        criadoEm = LocalDateTime.now();
    }

}
