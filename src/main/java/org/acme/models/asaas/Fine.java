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

@Getter
@Setter
@Entity
public class Fine implements Model {
    @Id
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String uuid;
    private double fine;
    @OneToMany(mappedBy = "fine")
    @JsonbTransient
    private List<CobrancaParcelada> cobrancaParcelada;
    @OneToMany(mappedBy = "FineObject")
    @JsonbTransient
    private List<CobrancaParceladaRetorno> cobrancaParceladaRetorno;
    @JsonbTransient
    @OneToMany(mappedBy = "fine")
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
