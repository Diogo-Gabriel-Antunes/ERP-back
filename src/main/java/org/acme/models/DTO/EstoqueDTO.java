package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;

@Getter
@Setter
public class EstoqueDTO implements DTO {
    private String uuid;
    private ProdutoDTO product;
    private String localNoEstoque;
    private Long quantidade;
}