package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.CascadeType;
import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class Request implements Model{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String numberRequest;
    @ManyToMany(mappedBy = "pedido")
    @Cascade(CascadeType.SAVE_UPDATE)
    private List<Itens> itens;
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Cliente cliente;
    private LocalDate finishDate;
    @ManyToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private StatusRequest status;
    private Double value;
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
