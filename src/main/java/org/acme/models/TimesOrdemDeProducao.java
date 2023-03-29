package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
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
public class TimesOrdemDeProducao extends PanacheEntityBase implements Model {
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

