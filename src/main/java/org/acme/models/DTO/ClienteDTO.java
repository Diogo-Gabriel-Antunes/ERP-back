package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.FieldUtil;
import org.acme.models.Cliente;
import org.acme.models.Endereco;

import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
public class ClienteDTO implements DTO {
    private String cnpj;
    private String cpf;
    private String idEstrangeiro;
    private String xNome;
    private String indIEDest;
    private String ie;
    private String isuf;
    private String im;
    private String email;
    private Endereco endereco;
    private LocalDate dataCriacao;
    private LocalDate ultimaAtualização;
    public static ClienteDTO convert(Cliente cliente) {
        ClienteDTO clienteDTO = new ClienteDTO();
        FieldUtil fieldUtil = new FieldUtil();
        cliente.setUuid(null);
        fieldUtil.updateFieldsModelToDTO(cliente,clienteDTO);
        return clienteDTO;
    }
}
