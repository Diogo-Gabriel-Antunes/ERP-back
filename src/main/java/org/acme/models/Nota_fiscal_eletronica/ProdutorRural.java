package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
@Getter
@Setter
@Entity
public class ProdutorRural {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String estado;
    private LocalDate dataEmissao;
    private String cpfCnpj;
    private String inscricaoEstadual;
    private String modelo;
    private String serie;
    private String numero;
}
