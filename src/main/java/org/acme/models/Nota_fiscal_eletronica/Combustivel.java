package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Imposto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Combustivel {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String codigoAnp;
    private String descricaoAnp;
    private Double percentualGlp;
    private Double percentualGnn;
    private double percentualGni;
    private double valorPartida;
    private String codigoAutorizacao;
    private long faturamentoTemperaturaAmbiente;
    private String estadoConsumo;
    @OneToOne
    private Imposto cide;
    @OneToOne
    private Encerrante encerrante;
}

@Getter
@Setter
@Entity
class Encerrante{
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String numeroBico;
    private String numeroBomba;
    private String numeroTanque;
    private double valorInicio;
    private double valorFinal;
}