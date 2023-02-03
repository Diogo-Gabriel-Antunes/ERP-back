package org.acme.models.Nota_fiscal_eletronica;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.util.List;
@Getter
@Setter
@Entity
public class ImportacaoDados implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String numero;
    private String dataEmissao;
    @Enumerated(EnumType.STRING)
    private ViaTransporte viaTransporte;
    private Double valorAfrmm;
    private int formaImportacao;
    private String codigoExportador;
    @ManyToOne
    private Desembaraco desembaraco;
    @ManyToOne
    private Adquirente adquirente;
    @OneToMany(mappedBy = "importacaoDados")
    @JsonbTransient
    private List<Adicoes> adicoes;
}

