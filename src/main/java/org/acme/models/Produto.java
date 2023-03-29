package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.persistence.Entity;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Produto extends PanacheEntityBase implements Serializable, Model {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private String codigo;
    private Boolean status;
    @ManyToMany
    @Cascade(CascadeType.ALL)
    @JsonbTransient
    private List<Imposto> imposto;
    @ManyToOne(targetEntity = Categoria.class,cascade = javax.persistence.CascadeType.ALL,fetch = FetchType.EAGER)
    @Cascade(CascadeType.SAVE_UPDATE)
    private Categoria categoria;
    @OneToMany(mappedBy = "produto")
    @JsonbTransient
    private List<Imagem> imagens;
    private Double precoUnitario;
    private Double pesoBruto;
    private Double pesoCubico;

    private Double pesoOriginal;
    private Long quantidadeMinima;
    @ManyToOne
    @JsonbTransient
    private Fornecedor fornecedor;
    @OneToOne(mappedBy = "produto",fetch = FetchType.LAZY)
    private MapaEstoque mapaEstoque;
    @Cascade(CascadeType.ALL)
    @JoinTable(name="informacaodefabricacao_produto", joinColumns=
            {@JoinColumn(name="produto_id")}, inverseJoinColumns=
            {@JoinColumn(name="informacaodefabricacao_id")})
    @ManyToMany(fetch = FetchType.EAGER)
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

