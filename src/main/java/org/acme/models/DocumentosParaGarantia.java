package org.acme.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class DocumentosParaGarantia {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String documento;
    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private Garantia garantia;
    @ManyToOne
    private Imagem imagem;
}
