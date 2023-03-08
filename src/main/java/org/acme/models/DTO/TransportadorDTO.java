package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.Util.FieldUtil;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;
import org.acme.models.Nota_fiscal_eletronica.Transportador;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDate;

@Getter
@Setter
public class TransportadorDTO implements DTO{

    private String uuid;
    private String cnpjCpf;
    private String razaoSocial;
    private String nome;
    private String inscricaoEstadual;
    @Type(EnderecoNFE.class)
    private EnderecoNFEDTO endereco;


}
