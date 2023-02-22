package org.acme.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.TipoDeMovimentacao;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class EntradaDeProduto extends PanacheEntityBase implements  Model {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToOne
    private Produto produto;
    private int quantidade;
    @Enumerated(EnumType.STRING)
    private TipoDeMovimentacao tipoDeMovimentacao;
    @ManyToOne
    @JsonIgnore
    private Fornecedor fornecedor;
    @ManyToOne
    private Funcionario responsavel;
    private LocalDateTime dataDaMovientacao;
    private LocalDateTime ultimaAtualizacao;
    private LocalDateTime dataCriacao;

    public EntradaDeProduto() {
        produto = new Produto();
        fornecedor = new Fornecedor();
        responsavel = new Funcionario();
    }
}

