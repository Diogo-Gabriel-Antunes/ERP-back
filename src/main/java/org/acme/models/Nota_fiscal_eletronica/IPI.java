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
public class IPI extends Imposto {
    private String cnpjProdutor;
    @OneToOne
    private SeloControle seloControle;
    private String codigoEnquadramentoLegal;
    @OneToOne
    private UnidadeNFE unidade;

}

