package org.acme.models.Nota_fiscal_eletronica;

public enum Finalidade {
    NFE_NORMAL("1"), NFECOMPLEMENTAR("2"), NFEAJUSTE("3"), NFEDEVOLUCAO("4");

    private String finalidade;

    public String getFinalidade() {
        return this.finalidade;
    }

    Finalidade(String finalidade) {
        this.finalidade = finalidade;
    }
}
