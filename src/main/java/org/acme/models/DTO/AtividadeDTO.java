package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.enums.Prioridade;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class AtividadeDTO implements DTO {
    private String uuid;
    private Prioridade prioridade;
    private String versao;
    private List<FuncionarioDTO> funcionario;
    private String descricao;
    private String categoria;
    private int prazo;
    private LocalDate dataDeInicio;
    private LocalDate dataDeFinalizacao;
    private String titulo;
    private int progressao;
}
