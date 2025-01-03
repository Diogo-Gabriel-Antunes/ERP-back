package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Imposto;
import org.acme.models.enums.Estado;
import org.acme.models.enums.TipoImposto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Issqn extends Imposto {
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
