package org.acme.models.asaas;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.Model;
import org.acme.models.asaas.Assinatura.Assinatura;
import org.hibernate.annotations.GenericGenerator;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
public class Split implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private String walletId;
    private double fixedValue;
    private double percentualValue;
    @JsonbTransient
    @ManyToMany(mappedBy = "splits")
    private Set<CobrancaParcelada> cobrancaParcelada;
    @JsonbTransient
    @ManyToMany
    private Set<CobrancaParceladaRetorno> cobrancaParceladaRetorno;
    @JsonbTransient
    @OneToMany(mappedBy = "split")
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
