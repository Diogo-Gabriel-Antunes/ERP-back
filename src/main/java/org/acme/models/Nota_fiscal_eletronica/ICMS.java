package org.acme.models.Nota_fiscal_eletronica;

public class ICMS {
    private String origem;
    private String cst;
    private double aliquota;
    private BaseCalculo baseCalculo;
    private SubstituicaoTributaria substituicaoTributaria;
    private FundoCombatePobreza fundoCombatePobreza;
    private double valor;
}
class BaseCalculo{
    private ModalidadeDeterminacao modalidadeDeterminacao;
    private double valor;
    private double percentualReducao;

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
class SubstituicaoTributaria{
    private double aliquota;
    private BaseCalculo baseCalculo;
    private double margemValorAdicionado;
    private FundoCombatePobreza fundoCombatePobreza;

}

class FundoCombatePobreza{
    private double aliquota;
    private BaseCalculo baseCalculo;
    private double valor;
}