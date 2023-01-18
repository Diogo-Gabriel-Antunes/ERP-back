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
public class Volume {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private long quantidade;
    private String especie;
    private String marca;
    private long numeracao;
    private Double pesoLiquido;
    private Double pesoBruto;
    @OneToMany
    private List<Lacre> lacres;
}
