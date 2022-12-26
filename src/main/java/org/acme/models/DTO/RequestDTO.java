package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Cliente;
import org.acme.models.Itens;
import org.acme.models.Product;
import org.acme.models.StatusRequest;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Getter
@Setter
public class RequestDTO implements DTO {

    private String numberRequest;
    private List<Itens> itens;
    private Cliente cliente;
    private LocalDate createDate;
    private LocalDate finishDate;
    private StatusRequest status;
    private Double value;
}
