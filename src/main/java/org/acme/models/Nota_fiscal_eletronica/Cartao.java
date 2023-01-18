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
public class Cartao {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    public long tipoIntegracao;
    private String cnpjCredenciadora;
    private String bandeiraOperadora;
    private String numeroAutorizacao;
}
