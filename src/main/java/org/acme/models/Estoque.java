package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.Response.ResponseFactory;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

import org.hibernate.annotations.CascadeType;

@Entity
@Getter
@Setter
public class Estoque extends PanacheEntityBase implements Model {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @OneToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private Produto produto;
    private Long quantidade;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAtualizacao;
    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        ultimaAtualizacao = LocalDateTime.now();
    }

    public ValidacaoException validaQuantidade(SaidaDeProduto saidaDeProduto){
        ValidacaoException validacaoException = new ValidacaoException();
        if(saidaDeProduto.getQuantidade() - quantidade <= 0){
            validacaoException.add("Quantidade maior que o numero de itens em estoque");
        }
        if(produto.getQuantidadeMinima() <= quantidade - saidaDeProduto.getQuantidade() ){
            validacaoException.add("Seu estoque esta a baixo da quantidade minima");
        }
        if(produto.getQuantidadeMinima() + 20 <= quantidade - saidaDeProduto.getQuantidade() ){
            validacaoException.add("Seu estoque esta baixo");
        }
        return validacaoException;
    }
}