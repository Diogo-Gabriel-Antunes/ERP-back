package org.acme.models.cobranca;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
public class Refunds {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String dateCreated;
    @Enumerated(EnumType.STRING)
    private Status status;
    private String value;
    private String description;
    private String transactionReceiptUrl;
    @JsonbTransient
    @OneToMany(mappedBy = "refunds")
    private List<CobrancaParceladaRetorno> cobrancaParceladaRetorno;

}
