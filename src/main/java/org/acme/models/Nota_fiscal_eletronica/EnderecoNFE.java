package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.DTO.TipoBairro;
import org.acme.models.Model;
import org.acme.models.enums.Estado;
import org.acme.models.enums.TipoLogradouro;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class EnderecoNFE implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String bairro;
    private String cep;
    private String codigoCidade;
    @Enumerated(EnumType.STRING)
    private Estado estado;
    private  String logradouro;
    private String numero;
    @Enumerated(EnumType.STRING)
    private TipoLogradouro tipoLogradouro;
    private String codigoPais;
    private String complemento;
    private String descricaoCidade;
    private String descricaoPais;
    @Enumerated(EnumType.STRING)
    private TipoBairro tipoBairro;
}






