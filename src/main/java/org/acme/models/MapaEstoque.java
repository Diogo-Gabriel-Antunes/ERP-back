package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class MapaEstoque extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String tipoLocal;
    private String identificacao;
    private String localPosicao;
    @ManyToOne
    private TipoDeLote tipoDeLote;
    @OneToOne
    private Produto produto;

    public MapaEstoque(){
        produto = new Produto();
        tipoDeLote = new TipoDeLote();
    }

    public Produto getProduto(){
        return this.produto;
    }
}
