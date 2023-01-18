package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class IPI {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String cnpjProdutor;
    @OneToOne
    private SeloControle seloControle;
    private String codigoEnquadramentoLegal;
    private String cst;
    @ManyToOne
    private BaseCalculo baseCalculo;
    private double aliquota;
    @OneToOne
    private Unidade unidade;
    private double valor;
}

