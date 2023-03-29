package org.acme.models;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.models.enums.PrioridadeCarga;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class ItemMovimentado extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @ManyToOne
    private ItensExternos itensExternos;
    @Enumerated(EnumType.STRING)
    private PrioridadeCarga prioridade;
    @ManyToOne
    private Itens itens;
    private Long quantidade;
    private LocalDateTime dataCriacao;
}
