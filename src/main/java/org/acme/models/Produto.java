package org.acme.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Produto extends PanacheEntityBase implements Model, Serializable {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private String codigo;
    private boolean status;
    @ManyToMany
    @Cascade(CascadeType.ALL)
    @JsonbTransient
    private List<Imposto> imposto;
    @ManyToOne(targetEntity = Category.class,cascade = javax.persistence.CascadeType.ALL)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Category categoria;
    @OneToMany(mappedBy = "produto")
    @JsonbTransient
    private List<Imagem> imagens;
    private Double precoUnitario;
    private Double pesoBruto;
    private Double pesoOriginal;
    private long quantidadeMinima;
    @ManyToOne
    @JsonbTransient
    private Fornecedor fornecedor;
    @OneToOne(mappedBy = "produto",fetch = FetchType.LAZY)
    private MapaEstoque mapaEstoque;
    @Cascade(CascadeType.ALL)
    @JoinTable(name="informacaodefabricacao_produto", joinColumns=
            {@JoinColumn(name="produto_id")}, inverseJoinColumns=
            {@JoinColumn(name="informacaodefabricacao_id")})
    @ManyToMany
    private List<InformacaoDeFabricacao> informacaoDeFabricacao;
    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        dataAlteracao = LocalDateTime.now();
        InformacaoDeFabricacao.persist(informacaoDeFabricacao);
    }
    @PreUpdate
    public void preUpdate(){
        dataAlteracao = LocalDateTime.now();
    }

    public Produto() {
        informacaoDeFabricacao = new ArrayList<>();
    }
    @JsonbTransient
    public MapaEstoque getMapaEstoque(){
        return this.mapaEstoque;
    }
}

