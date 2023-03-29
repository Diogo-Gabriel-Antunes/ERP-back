package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class PreparacaoDeCarga extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private Long capacidadeVeiculo;
    private Long quantidadeDeItens;
    private Double fatorOcupacao;
    private Long tamanhoMedioDosProdutos;
    private Long capacidadeTotalDeProdutos;
    @ManyToOne
    private MontagemDeCarga montagemDeCarga;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
