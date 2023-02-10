package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContatoDTO implements DTO{
    private String email;
    private String ddd;
    private String telefone;
}
