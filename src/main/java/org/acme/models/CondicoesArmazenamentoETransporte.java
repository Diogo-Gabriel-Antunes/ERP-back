package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
public class CondicoesArmazenamentoETransporte extends PanacheEntityBase implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String temperatura;
    private String umidade;
    private String luz;
    private String ventilacao;
    private String transporte;
    private String seguro;
    private String manuseio;
    @OneToOne(mappedBy = "condicoesArmazenamentoETransporte",cascade = CascadeType.MERGE)
    @Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JsonbTransient
    private Compra compra;

}
