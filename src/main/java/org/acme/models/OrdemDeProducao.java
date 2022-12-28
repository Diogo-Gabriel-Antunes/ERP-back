package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.enums.Status;
import org.acme.models.enums.StatusDaProducao;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
public class OrdemDeProducao extends PanacheEntityBase implements Model{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @OneToOne
    private Product product;
    private Long quantidade;
    @Column(length = 999999999)
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusDaProducao status;
    @OneToMany(mappedBy = "ordemDeProducao")
    private List<TimesOrdemDeProducao> atualizadoEm = new ArrayList<>();
    private LocalDate inicioDaProducao;
    private LocalDate finalizadoEm;
}
