package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EstoqueDTO implements DTO{
    private String uuid;
    private ProdutoDTO product;
    private Long quantidade;
    private LocalDate lastUpdate;
}