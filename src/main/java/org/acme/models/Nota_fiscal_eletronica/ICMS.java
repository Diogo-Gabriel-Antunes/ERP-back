package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Imposto;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class ICMS extends Imposto {
    private String origem;


}



