package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.Util.FieldUtil;
import org.acme.models.Cliente;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;

import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO implements DTO {
    private String uuid;
    private String cpfCnpj;
    private String idEstrangeiro;
    private String xNome;
    private String indIEDest;
    private String ie;
    private String isuf;
    private String im;
    private String email;
    private String asaasId;

    @Type(EnderecoNFE.class)
    private EnderecoNFEDTO endereco;

    public static ClienteDTO convert(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        FieldUtil fieldUtil = new FieldUtil();
        cliente.setUuid(null);
        fieldUtil.updateFieldsModelToDTO(cliente,clienteDTO);
        return clienteDTO;
    }
}
