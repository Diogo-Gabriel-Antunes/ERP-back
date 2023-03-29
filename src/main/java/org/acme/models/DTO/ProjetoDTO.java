package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.enums.StatusProjeto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter

public class ProjetoDTO implements DTO {
    private String objetivoGeral;
    private LocalDate finalizarAte;
    private Double orcamento;

    private List<FuncionarioDTO> equipe = new ArrayList<>();
    private List<AtividadeDTO> atividades = new ArrayList<>();
    private LocalDate dataDeCriacao;
    private LocalDate finalizadoEm;
    private String ultimaAtualizacao;
    private StatusProjeto statusProjeto;
    private Integer percConcluido;
}
