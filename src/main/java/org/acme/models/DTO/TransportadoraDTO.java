package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.FieldUtil;
import org.acme.models.Endereco;
import org.acme.models.Transportadora;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
public class TransportadoraDTO implements DTO{

    private String uuid;
    private String cnpj;
    private String cpf;
    private String nome;
    private String ie;
    private Endereco endereco;
    private LocalDate dataCriacao;
    private LocalDate ultimaAtualizacao;

    public static TransportadoraDTO convert(Transportadora transportadora) {
        TransportadoraDTO transportadoraDTO = new TransportadoraDTO();
        FieldUtil fieldUtil = new FieldUtil();
        transportadora.setUuid(null);
        fieldUtil.updateFieldsModelToDTO(transportadora,transportadoraDTO);
        return transportadoraDTO;
    }
}
