package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.LabelForm;
import org.acme.Anotacao.Type;
import org.acme.models.Cliente;
import org.acme.models.Funcionario;
import org.acme.models.Itens;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class EstoqueExternoDTO implements DTO {
    private String uuid;
    @Type(Set.class)
    @LabelForm("Itens")
    private Set<ItensDTO> itens;
    @Type(Cliente.class)
    @LabelForm("Cliente")
    private ClienteDTO cliente;
    @Type(Funcionario.class)
    @LabelForm("Responsavel")
    private FuncionarioDTO responsavel;

}
