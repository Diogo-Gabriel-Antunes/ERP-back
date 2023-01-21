package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Imposto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
public class ICMS extends Imposto {
    private String origem;

    @ManyToOne
    private FundoCombatePobreza fundoCombatePobreza;
}


enum ModalidadeDeterminacao{
    MARGEM_VALOR_AGREGADO(0),PAUTO(1),PRECOTABELADOMAX(2),VALOROP(3);

    private int modalidade;

    public int getModalidade(){
        return this.modalidade;
    }
    ModalidadeDeterminacao(int modalidade){
        this.modalidade = modalidade;
    }
}

