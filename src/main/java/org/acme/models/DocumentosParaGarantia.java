package org.acme.models;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class DocumentosParaGarantia implements Model {
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
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAtualizacao;
    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        ultimaAtualizacao = LocalDateTime.now();
    }
}
