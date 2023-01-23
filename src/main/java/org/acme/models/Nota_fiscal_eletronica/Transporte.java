package org.acme.models.Nota_fiscal_eletronica;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
public class Transporte extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String modalidadeFrete;
    @ManyToOne
    private Transportador transportador;
    @ManyToOne
    private RetencaoICMS retencaoICMS;
    @ManyToOne
    private Veiculo veiculo;
    @OneToMany
    private List<Reboque> reboque;
    @OneToMany
    private List<Volume> volumes;
}
