package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.models.Nota_fiscal_eletronica.Transportador;
import org.acme.models.Nota_fiscal_eletronica.Veiculo;
import org.acme.models.enums.TipoDeCarga;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<ItensExternos> itensExternos;   @ManyToMany
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name="itensMovimentado_montagemDeCarga", joinColumns=
            {@JoinColumn(name="montagem_id")}, inverseJoinColumns=
            {@JoinColumn(name="itensMovimentados_id")})
    private List<ItemMovimentado> itemMovimentados;

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

