package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Funcionario;
import org.acme.models.enums.Prioridade;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Getter
@Setter
public class AtividadeDTO implements DTO{
    private String uuid;
    private Prioridade prioridade;
    private String versao;
    private Funcionario funcionario;
    private String descricao;
    private String categoria;
    private int prazo;
    private LocalDate dataDeInicio;
    private LocalDate dataDeFinalizacao;
    private String titulo;
    private int progressao;
}
