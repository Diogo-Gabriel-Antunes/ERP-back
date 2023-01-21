package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Total {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private double baseCalculoIcms;
    private double valorIcms;
    private double valorFcp;
    private double valorFcpSt;
    private double baseCalculoIrrf;
    private double valorIrrfRetido;
    private double valorFcpStRetido;
    private double valorIcmsDesonerado;
    @ManyToOne
    private BaseCalculo baseCalculoIcmsSt;
    private double valorIcmsSt;
    private double valorProdutosServicos;
    private double valorFrete;
    private double valorSeguro;
    private double valorDesconto;
    private double valorIi;
    private double valorIpi;
    private double valorIpiDevolvido;
    private double valorPis;
    private double valorCofins;
    private double valorOutros;
    private double valorNfe;
    private double valorAproximadoTributos;
    private double valorPisRetido;
    private double valorCofinsRetido;
    private double valorCsllRetido;
    @OneToOne
    private Servico servico;
    private double baseCalculoRetencao;
    private double valorPrevidenciaRetido;
    private double valorIcmsEstadoDestino;
}
