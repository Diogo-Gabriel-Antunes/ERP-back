package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Nota_fiscal_eletronica.Transportador;
import org.acme.models.Nota_fiscal_eletronica.Veiculo;
import org.acme.models.enums.PrioridadeCarga;
import org.acme.models.enums.TipoDeCarga;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class MontagemDeCarga extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;

    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name="itens_montagemDeCarga", joinColumns=
            {@JoinColumn(name="montagem_id")}, inverseJoinColumns=
            {@JoinColumn(name="itens_id")})
    private List<Itens> itens;
    @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name="itensExternos_montagemDeCarga", joinColumns=
            {@JoinColumn(name="montagem_id")}, inverseJoinColumns=
            {@JoinColumn(name="itensExternos_id")})
    private List<ItensExternos> itensExternos;

    @ManyToOne
    private Transportador transportador;
    @ManyToOne
    private Veiculo veiculo;
    @ManyToOne
    private Motorista motorista;
    @Enumerated(EnumType.STRING)
    private TipoDeCarga tipoDeCarga;
    private Boolean isManual;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public MontagemDeCarga() {
        itens = new ArrayList<>();
        itensExternos = new ArrayList<>();
    }

    public List<Itens> getItens(){
        return this.itens;
    }
    public List<ItensExternos> getItensExternos(){
        return this.itensExternos;
    }
    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        dataAtualizacao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        dataAtualizacao = LocalDateTime.now();
    }
}

