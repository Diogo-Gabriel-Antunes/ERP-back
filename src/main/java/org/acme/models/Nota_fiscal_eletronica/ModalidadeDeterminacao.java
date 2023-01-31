package org.acme.models.Nota_fiscal_eletronica;

public enum ModalidadeDeterminacao {
    MARGEM_VALOR_AGREGADO(0), PAUTO(1), PRECOTABELADOMAX(2), VALOROP(3);

    private int modalidade;

    public int getModalidade() {
        return this.modalidade;
    }

    ModalidadeDeterminacao(int modalidade) {
        this.modalidade = modalidade;
    }
}
