package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
public class ICMS {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String origem;
    private String cst;
    private double aliquota;
    @ManyToOne
    private BaseCalculo baseCalculo;
    @ManyToOne
    private SubstituicaoTributaria substituicaoTributaria;
    @ManyToOne
    private FundoCombatePobreza fundoCombatePobreza;
    private double valor;
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

