package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Nota_fiscal_eletronica.Pessoa;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
public class Unidade extends PanacheEntityBase implements Model{
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    @OneToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private Pessoa pessoa;
    private LocalDateTime dataCriacao;
    private LocalDateTime ultimaAtualizacao;
    @PrePersist
    public void prePersist(){
        if(pessoa != null){
            getPessoa().setUuid(getEntityManager().merge(pessoa).getUuid());
        }
        dataCriacao = LocalDateTime.now();
        ultimaAtualizacao = LocalDateTime.now();
    }
    @PreUpdate
    public void preUpdate(){
        ultimaAtualizacao = LocalDateTime.now();
    }
}
