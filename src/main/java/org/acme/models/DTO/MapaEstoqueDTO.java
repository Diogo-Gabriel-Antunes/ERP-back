package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
import org.acme.models.Produto;

@Getter
@Setter
public class MapaEstoqueDTO implements DTO {
    private String uuid;
    @LabelForm("Tipo Local")
    private String tipoLocal;
    @LabelForm("Identificação")
    private String identificacao;
    @LabelForm("Local Posição")
    private String localPosicao;
    @Type(Produto.class)
    private ProdutoDTO produto;
}
