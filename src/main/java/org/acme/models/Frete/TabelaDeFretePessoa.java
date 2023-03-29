package org.acme.models.Frete;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Date;

@Getter
@Setter
public class TabelaDeFretePessoa extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;
    private TabelaDeFrete tabelaDeFrete;


    @Enumerated(EnumType.STRING)
    private FobDirigido fobDirigido;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "oid_pessoa", insertable = false, updatable = false)
    private Pessoa pessoa;

    private boolean principal;

    private TipoTabela tipoPessoa;

    private Date vigencia;

    private Date validade;

    private Negociacao negociacao;
    public enum Negociacao {
        CIF, FOB, AMBOS
    }
    public enum FobDirigido {
        CONFORME_TABELA,
        PERMITE_FOB_DIRIGIDO,
        NAO_PERMITE_FOB_DIRIGIDO,
        APENAS_FOB_DIRIGIDO
    }
}
