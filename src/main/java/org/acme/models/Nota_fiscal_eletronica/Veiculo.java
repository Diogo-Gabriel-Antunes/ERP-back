package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Getter
@Setter
@Entity
public class Veiculo {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private int tipoOperacao;
    private String chassi;
    private String placa;
    private String uf;
    private String rntc;
    private String codigoCor;
    private String descricaoCor;
    private long potenciaMotor;
    private long pesoBruto;
    private String numeroSerie;
    private int tipoCombustivel;
    private String numeroMotor;
    private long capacidadeTracao;
    private String distanciaEixos;
    private long anoModelo;
    private long anoFabricacao;
    private String tipoPintura;
    private long tipo;
    private long condicao;
    private String condicaoVin;
    private String codigoModelo;
    private long codigoCorDenatran;
    private long lotacaoMaxima;
    private long restricao;
}
