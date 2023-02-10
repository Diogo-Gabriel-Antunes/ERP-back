package org.acme.models.asaas;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.asaas.Assinatura.Assinatura;
import org.acme.models.Model;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
public class Interest implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private double value;
    @OneToMany(mappedBy = "interest")
    @JsonbTransient
    private List<CobrancaParcelada> cobrancaParcelada;
    @OneToMany(mappedBy = "InterestObject")
    @JsonbTransient
    private List<CobrancaParceladaRetorno> cobrancaParceladaRetorno;
    @JsonbTransient
    @OneToMany(mappedBy = "interest")
    private List<Assinatura> assinatura;
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
