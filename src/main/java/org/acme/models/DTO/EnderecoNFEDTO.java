package org.acme.models.DTO;

import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.Estado;
import org.acme.models.enums.TipoLogradouro;


@Getter
@Setter
public class EnderecoNFEDTO implements DTO {
    private String bairro;
    private String cep;
    private String codigoCidade;
    private Estado estado;
    private String logradouro;
    private String numero;
    private TipoLogradouro tipoLogradouro;
    private String codigoPais;
    private String complemento;
    private String descricaoCidade;
    private String descricaoPais;
    private TipoBairro tipoBairro;
}
