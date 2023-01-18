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
public class Servico {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String dataPrestacao;
    private double baseCalculo;
    private double valor;
    private double valorIss;
    private double valorPis;
    private double valorCofins;
    private double valorDeducao;
    private double valorOutros;
    private double valorDescontoCondicionado;
    private double valorDescontoIncondicionado;
    private double valorIssRetido;
    private double codigoRegimeEspecial;

}
