package org.acme.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.json.bind.annotation.JsonbTypeAdapter;
import javax.persistence.*;
import java.io.Serializable;

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
    @OneToOne
    private Produto produto;

    public MapaEstoque(){
        produto = new Produto();
    }

    public Produto getProduto(){
        return this.produto;
    }
}
