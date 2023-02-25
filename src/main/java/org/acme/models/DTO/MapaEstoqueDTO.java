package org.acme.models.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Produto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Getter
@Setter
public class MapaEstoqueDTO implements DTO {
    private String uuid;
    private String tipoLocal;
    private String identificacao;
    private String localPosicao;
    @Type(Produto.class)
    private ProdutoDTO produto;
}
