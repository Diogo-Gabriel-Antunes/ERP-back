package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
@Getter
@Setter
@Entity
public class Parcelas {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String numero;
    private String dataVencimento;
    private double valor;
}
