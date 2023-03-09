package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.models.Nota_fiscal_eletronica.Cor;

@Getter
@Setter
public class VeiculoDTO implements DTO{
    private String uuid;
    private Integer tipoOperacao;
    private String modeloDoVeiculo;
    @LabelForm("Chassi")
    private String chassi;
    @LabelForm("Placa")
    private String placa;
    private String uf;
    private String rntc;
    @Type(Cor.class)
    @LabelForm("Cor")
    private CorDTO cor;
    private Long potenciaMotor;
    private Long pesoBruto;
    private String numeroSerie;
    private Integer tipoCombustivel;
    private String numeroMotor;
    private Long capacidadeTracao;
    private String distanciaEixos;
    @LabelForm("Ano modelo")
    private Long anoModelo;
    @LabelForm("Ano de fabricação")
    private Long anoFabricacao;
    private String tipoPintura;
    private Long tipo;
    private Long condicao;
    private String condicaoVin;
    @LabelForm("Codigo Modelo")
    private String codigoModelo;
    private Long codigoCorDenatran;
    @LabelForm("Lotação maxima")
    private Long lotacaoMaxima;
    private Long restricao;
}
