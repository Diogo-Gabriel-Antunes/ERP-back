package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Nota_fiscal_eletronica.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class Tributos extends PanacheEntityBase {
    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
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
    private LocalDateTime ultimaAtualizacao;

    @PrePersist
    public void prePersist() {
        if (icms != null) {
            icms.setUuid(getEntityManager().merge(icms).getUuid());
        }
        if (ipi != null) {
            ipi.setUuid(getEntityManager().merge(ipi).getUuid());
        }
        if (pis != null) {
            pis.setUuid(getEntityManager().merge(pis).getUuid());
        }
        if (cofins != null) {
            cofins.setUuid(getEntityManager().merge(cofins).getUuid());
        }
        if (issqn != null) {
            issqn.setUuid(getEntityManager().merge(issqn).getUuid());
        }
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        ultimaAtualizacao = LocalDateTime.now();
    }

}
