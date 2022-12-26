package org.acme.models;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.Status;
import org.acme.models.enums.StatusDaProducao;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class OrdemDeProducao implements Model{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @OneToOne
    private Product product;
    private Long quantidade;
    @Enumerated(EnumType.STRING)
    private StatusDaProducao status;
    private LocalDate inicioDaProducao;
    private LocalDate finalizadoEm;
}
