package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
public class NotaReferenciada {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @OneToMany
    private List<NFe> nfe;
    @OneToMany
    private List<NFePapel> nFePapel;
    @OneToMany
    private List<ProdutorRural> produtorRural;
    private String chaveCte;

}
