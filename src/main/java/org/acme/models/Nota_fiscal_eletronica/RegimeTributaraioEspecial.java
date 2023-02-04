package org.acme.models.Nota_fiscal_eletronica;

public enum RegimeTributaraioEspecial {
    SEMREGIME(0), MICROEMPRESAMUNICIPAL(1), ESTIMATIVA(2), SOCIEDADEPROFISSIONAIS(3), COOPERATIVA(4), MEI(5), MEEPP(6);

    private int codigo;

    RegimeTributaraioEspecial(int codigo) {
        this.codigo = codigo;
    }
}
