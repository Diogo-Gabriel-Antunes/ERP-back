package org.acme.models.Nota_fiscal_eletronica;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.CorDTO;
import org.acme.models.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
@Entity
public class Veiculo extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String modeloDoVeiculo;
    private Integer tipoOperacao;
    private String chassi;
    private String placa;
    private String uf;
    private String rntc;
    @OneToOne
    private Cor cor;
    private Long potenciaMotor;
    private Long pesoBruto;
    private String numeroSerie;
    private Integer tipoCombustivel;
    private String numeroMotor;
    private Long capacidadeTracao;
    private String distanciaEixos;
    private Long anoModelo;
    private Long anoFabricacao;
    private String tipoPintura;
    private Long tipo;
    private Long condicao;
    private String condicaoVin;
    private String codigoModelo;
    private Long codigoCorDenatran;
    private Long lotacaoMaxima;
    private Long restricao;
}
