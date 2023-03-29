package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.models.enums.StatusDaProducao;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class OrdemDeProducao extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @OneToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private Produto produto;
    private Long quantidade;
    @Column(length = 10485760)
    private String descricao;
    @Enumerated(EnumType.STRING)
    private StatusDaProducao status;
    @OneToMany(mappedBy = "ordemDeProducao")
    private List<TimesOrdemDeProducao> atualizadoEm;
    private LocalDate inicioDaProducao;
    private LocalDate finalizadoEm;
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
