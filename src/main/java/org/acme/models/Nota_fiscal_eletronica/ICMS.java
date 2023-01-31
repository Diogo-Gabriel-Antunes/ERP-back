package org.acme.models.Nota_fiscal_eletronica;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Imposto;
import org.acme.models.Model;
import org.acme.models.enums.Estado;
import org.acme.models.enums.TipoImposto;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
public class ICMS extends Imposto implements Model {
    private String origem;


}



