package org.acme.models;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Nota_fiscal_eletronica.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Tributos {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToOne
    private Partilha partilha;
    @ManyToOne
    private ICMS icms;
    @ManyToOne
    private IPI ipi;
    @ManyToOne
    private Pis pis;
    @ManyToOne
    private Cofins cofins;
    @ManyToOne
    private Issqn issqn;
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
