package org.acme.models;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.TipoImposto;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@Entity
public class Imposto implements Serializable {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @Enumerated(EnumType.STRING)
    private TipoImposto tipoImposto;
    private int aliquota;
    @ManyToOne
    @JsonbTransient
    private Product products;
}
