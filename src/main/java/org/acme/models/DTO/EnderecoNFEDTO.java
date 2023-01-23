package org.acme.models.DTO;

import br.com.swconsultoria.nfe.dom.enuns.EstadosEnum;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.TipoLogradouro;


@Getter
@Setter
public class EnderecoNFEDTO {
    private String bairro;
    private String cep;
    private String codigoCidade;
    private EstadosEnum estado;
    private  String logradouro;
    private String numero;
    private TipoLogradouro tipoLogradouro;
    private String codigoPais;
    private String complemento;
    private String descricaoCidade;
    private String descricaoPais;
    private TipoBairro tipoBairro;
}
