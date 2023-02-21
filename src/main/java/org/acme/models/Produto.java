package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import javax.persistence.Entity;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Produto extends PanacheEntityBase implements Model {

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
    @OneToMany(mappedBy = "produto",fetch = FetchType.EAGER)
    @JsonbTransient
    private List<Imagem> imagens;
    private Double precoUnitario;
    private Double pesoBruto;
    private Double pesoOriginal;
    private long quantidadeMinima;
    @ManyToOne
    @JsonbTransient
    private Fornecedor fornecedor;

    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name="informacaodefabricacao_produto", joinColumns=
            {@JoinColumn(name="produto_id")}, inverseJoinColumns=
            {@JoinColumn(name="informacaodefabricacao_id")})
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<InformacaoDeFabricacao> informacaoDeFabricacao;
    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        dataAlteracao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        dataAlteracao = LocalDateTime.now();
    }
}

