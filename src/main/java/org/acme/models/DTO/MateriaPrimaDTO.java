package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Itens;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Getter
@Setter
public class MateriaPrimaDTO implements DTO{
    private String uuid;
    private String nome;
    private String descricao;
    private int quantidade;
    private double precoUnitario;
}
