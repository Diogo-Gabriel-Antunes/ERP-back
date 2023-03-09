package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.models.enums.Estado;
import org.acme.models.enums.TipoLogradouro;


@Getter
@Setter
public class EnderecoNFEDTO implements DTO {
    @LabelForm("Bairro")
    private String bairro;
    @LabelForm("CEP")
    private String cep;
    private String codigoCidade;
    @LabelForm("Estado")
    private Estado estado;
    @LabelForm("Endereço")
    private String logradouro;
    @LabelForm("Numero Endereço")
    private String numero;
    private TipoLogradouro tipoLogradouro;
    private String codigoPais;
    private String complemento;
    @LabelForm("Cidade")
    private String descricaoCidade;
    @LabelForm("Pais")
    private String descricaoPais;
    private TipoBairro tipoBairro;
}
