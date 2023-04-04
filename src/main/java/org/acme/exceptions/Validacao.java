package org.acme.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Validacao {

    private String mensagem;
    private Integer statusCode;
    private String solucao;
}
