package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class SaidaDeProduto extends PanacheEntityBase implements Model {

    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private LocalDateTime dataDaSaida;
    @ManyToOne
    private Produto produto;
    private int quantidade;
    private LocalDateTime dataDeCriacao;
    private LocalDateTime dataDeEdicao;
    @ManyToOne
    private Funcionario funcionario;

    public SaidaDeProduto() {
        funcionario = new Funcionario();
        produto = new Produto();
    }

    @PrePersist
    public void prePersist(){
        dataDeCriacao = LocalDateTime.now();
        dataDeEdicao = LocalDateTime.now();

    }
    public void preUpdate(){
        dataDeEdicao = LocalDateTime.now();
    }
}
