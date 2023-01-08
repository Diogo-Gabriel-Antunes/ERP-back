package org.acme.models.DTO;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import lombok.Getter;
import lombok.Setter;
import org.acme.models.Model;
import org.acme.models.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class VendasDTO implements DTO {

    private String uuid;
    private LocalDate dataDaVenda;

    private Set<Product> products;

}
