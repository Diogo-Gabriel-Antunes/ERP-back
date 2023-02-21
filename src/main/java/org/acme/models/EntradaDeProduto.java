package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.TipoDeMovimentacao;
import org.hibernate.annotations.GenericGenerator;

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
    private Cliente fornecedor;
    @ManyToOne
    private Funcionario responsavel;
    private LocalDateTime dataDaMovientacao;
    private LocalDateTime ultimaAtualizacao;
    private LocalDateTime dataCriação;

    public EntradaDeProduto() {
        produto = new Produto();
        fornecedor = new Cliente();
        responsavel = new Funcionario();
    }
}

