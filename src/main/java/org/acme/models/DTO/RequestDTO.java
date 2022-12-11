package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.models.Product;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
public class RequestDTO implements DTO {

    private String uuid;
    private String numberRequest;
    private Set<Product> products;
    private LocalDate createDate;
    private LocalDate finishDate;
}
