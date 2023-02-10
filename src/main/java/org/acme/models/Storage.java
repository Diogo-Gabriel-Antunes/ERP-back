package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CascadeType;

@Entity
@Getter
@Setter
public class Storage extends PanacheEntityBase implements Model {

    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @OneToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private Product produto;
    private Long quantidade;
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