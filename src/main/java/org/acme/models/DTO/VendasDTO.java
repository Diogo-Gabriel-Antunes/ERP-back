package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class VendasDTO implements DTO {

    private String uuid;
    private LocalDate dataDaVenda;
    private Set<ProdutoDTO> products;

}
