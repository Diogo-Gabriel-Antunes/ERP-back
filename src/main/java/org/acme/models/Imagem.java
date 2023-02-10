package org.acme.models;


import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class Imagem {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String uuid;
    private String urlImagem;
    @ManyToOne
    @JsonbTransient
    @Cascade(CascadeType.SAVE_UPDATE)
    private Product produto;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAtualização;
    @PrePersist
    public void prePersist(){
        dataCriacao = LocalDateTime.now();
        ultimaAtualização = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        ultimaAtualização = LocalDateTime.now();
    }
}