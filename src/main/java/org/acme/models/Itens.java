package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Nota_fiscal_eletronica.ImportacaoDados;
import org.acme.models.Nota_fiscal_eletronica.ImportacaoImposto;
import org.acme.models.Nota_fiscal_eletronica.Medicamentos;
import org.acme.models.Nota_fiscal_eletronica.Veiculo;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Itens extends PanacheEntityBase implements Model{

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToOne(cascade = javax.persistence.CascadeType.ALL)
    @Cascade(CascadeType.ALL)
    private Product produto;
    private Long quantidade;
    @ManyToMany
    @JsonbTransient
    @Cascade(CascadeType.SAVE_UPDATE)
    @JoinTable(name="itens_pedido", joinColumns=
            {@JoinColumn(name="itens_id")}, inverseJoinColumns=
            {@JoinColumn(name="pedido_id")})
    private List<Request> pedido = new ArrayList<>();
    @ManyToMany(mappedBy = "itens")
    @JsonbTransient
    private Set<Compra> compras = new HashSet<>();
    private LocalDate dataAtualizacao;
    private LocalDate dataCriacao;


    //Informações NFE
    private ImportacaoImposto importacao;
    private List<ImportacaoDados> importacaoDados;
    private Tributos tributos;
    private String codigoBarrasTributavel;
    @Column(length =1000000)
    private String descricao;
    private String ncm;
    private String cest;
    private String cfop;

}

