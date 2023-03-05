package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Nota_fiscal_eletronica.Cor;

import javax.persistence.OneToOne;

@Getter
@Setter
public class VeiculoDTO implements DTO{
    private String uuid;
    private Integer tipoOperacao;
    private String chassi;
    private String placa;
    private String uf;
    private String rntc;
    @Type(Cor.class)
    private CorDTO cor;
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
