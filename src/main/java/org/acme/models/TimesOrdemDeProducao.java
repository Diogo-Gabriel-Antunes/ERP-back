package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.StatusDaProducao;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
public class TimesOrdemDeProducao extends PanacheEntityBase {
    @Id @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private LocalDateTime time;
    @Enumerated(EnumType.STRING)
    private StatusDaProducao statusDaProducao;
    @ManyToOne
    @JsonbTransient
    private OrdemDeProducao ordemDeProducao;

}

