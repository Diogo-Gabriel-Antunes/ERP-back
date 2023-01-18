package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Getter
@Setter
@Entity
public class Issqn {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private Double valor;
    private double aliquota;
    @ManyToOne
    private BaseCalculo baseCalculo;
    private String codigoServico;
    private String valorDeducao;
    private double valorOutros;
    private double descontoIncondicionado;
    private double decontoCondicionado;
    private double valorRetencaoIss;
    private String codigoMunicipalServico;
    private String codigoMunicipioIncidencia;
    private String codigoMunicipioFatoGerador;
    private String codigoExigibilidade;
    private String numeroProcessoSuspensao;
}
