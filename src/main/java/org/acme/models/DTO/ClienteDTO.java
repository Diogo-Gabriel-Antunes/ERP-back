package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

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
