package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class Adicoes {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String numero;
    private String numeroSequencia;
    private String codigoFabricante;
    private double valorDesconto;
    private String numeroDrawback;
    @ManyToOne
    private ImportacaoDados importacaoDados;
}
