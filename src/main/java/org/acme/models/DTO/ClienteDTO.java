package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.LabelForm;
import org.acme.Anotacao.Type;
import org.acme.Util.FieldUtil;
import org.acme.models.Cliente;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO implements DTO {
    private String uuid;
    @LabelForm("CNPJ/CPF")
    private String cpfCnpj;
    private String idEstrangeiro;
    @LabelForm("Nome")
    private String xNome;
    private String indIEDest;
    @LabelForm("Inscrição estadual")
    private String ie;
    private String isuf;
    @LabelForm("Inscrição estadual")
    private String im;
    private String email;
    private String asaasId;

    @Type(EnderecoNFE.class)
    private EnderecoNFEDTO endereco;


}
