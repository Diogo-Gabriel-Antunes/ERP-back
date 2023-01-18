package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
@Getter
@Setter
@Entity
public class Cobranca {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String numero;
    private Double valorTotal;
    private double valorDesconto;
    private double valorLiquido;
    @OneToMany
    private List<Parcelas> parcelas;
}
