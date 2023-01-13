package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.time.LocalDate;
import org.hibernate.annotations.CascadeType;

@Getter
@Setter
@Entity
public class Transportadora extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String cnpj;
    private String cpf;
    private String nome;
    private String ie;
    @OneToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private Endereco endereco;
    private LocalDate dataCriacao;
    private LocalDate ultimaAtualizacao;

}
