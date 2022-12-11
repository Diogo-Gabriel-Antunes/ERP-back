package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Category;
import org.acme.models.enums.Status;

import java.time.LocalDate;

@Getter
@Setter
public class ProductDTO implements DTO {
    private String uuid;
    private String name;
    private String codeBar;
    private Category category;
    private LocalDate dateCreate;

    private LocalDate dataAlteracao;
    private Status status;
}
