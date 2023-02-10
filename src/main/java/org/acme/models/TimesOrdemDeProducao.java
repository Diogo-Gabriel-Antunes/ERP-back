package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.StatusDaProducao;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
public class TimesOrdemDeProducao extends PanacheEntityBase {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private LocalDateTime time;
    @Enumerated(EnumType.STRING)
    @Cascade(CascadeType.SAVE_UPDATE)
    private StatusDaProducao statusDaProducao;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonbTransient
    @Cascade(CascadeType.SAVE_UPDATE)
    private OrdemDeProducao ordemDeProducao;
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

