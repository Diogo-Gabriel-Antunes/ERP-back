package org.acme.models.Nota_fiscal_eletronica;

import org.acme.models.Endereco;

public class Emitente {
    private String cpfCnpj;
    private EnderecoNFE endereco;
    private String email;
    private boolean incentivadorCultural;
    private boolean incentivoFiscal;
    private String inscricaoEstadual;
    private String inscricaoEstadualSubstituto;
    private String inscricaoMunicipal;
    private  String nomeFantasia;
    private String razaoSocial;
    private Integer regimeTributario;
    private Integer regimeTributarioEspecial;
    private boolean simplesNacional;
    private Telefone telefone;

}

class Telefone{
    private String ddd;
    private String numero;
}
