package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Garantia extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private Integer quantidadeDeDiasDeGarantia;
    private String descricaoGarantia;
    private String contatoParaReivindicar;
    @OneToMany(mappedBy = "garantia")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<CondicoesDeGarantia> condicoesDeGarantia;
    @OneToMany(mappedBy = "garantia")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<DocumentosParaGarantia> documentosParaGarantias;
    private boolean contemExtencao;
}
