package org.acme.models;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.Util.FieldUtil;
import org.acme.models.DTO.ClienteDTO;
import org.acme.models.Nota_fiscal_eletronica.EnderecoNFE;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@Entity
public class Cliente extends PanacheEntityBase implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String cpfCnpj;
    private String idEstrangeiro;
    private String xNome;
    private String indIEDest;
    private String ie;
    private String isuf;
    private String im;
    private String email;
    private String telefone;
    private String observacao;
    private boolean receberNotificacao;
    @OneToOne
    @Cascade(CascadeType.SAVE_UPDATE)
    private EnderecoNFE endereco;
    private LocalDate dataCriacao;
    private LocalDate ultimaAtualização;

    @PrePersist
    public void prePersist(){
        EntityManager em = getEntityManager();
        em.persist(endereco);
        dataCriacao = LocalDate.now();
        ultimaAtualização = LocalDate.now();
    }
    @PreUpdate
    public void preUpdate(){
        EntityManager em = getEntityManager();
        em.persist(endereco);
        ultimaAtualização = LocalDate.now();
    }


}
