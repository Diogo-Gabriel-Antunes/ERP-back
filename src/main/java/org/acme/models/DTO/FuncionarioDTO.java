package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Beneficios;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;
import org.acme.models.enums.NivelDeEscolaridade;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class FuncionarioDTO implements DTO {
    private String nome;
    private EnderecoNFEDTO endereco;
    private String pis;
    private NivelDeEscolaridade nivelDeEscolaridade;
    private String cpf;
    private String rg;
    private boolean valeTransporte;
    private boolean servicoMilitar;
    private boolean salarioFamilia;
    private String tituloDeEleitor;
    private String cargo;
    private String funcao;
    private LocalDate dataAdmissao;
    private LocalDate dataNascimento;
    private String nacionalidade;
    private String naturalidade;
    private Double salario;
    private Set<Beneficios> beneficios;
    private boolean ativo;
}
