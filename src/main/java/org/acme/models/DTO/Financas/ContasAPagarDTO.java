package org.acme.models.DTO.Financas;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Cliente;
import org.acme.models.DTO.ClienteDTO;
import org.acme.models.DTO.DTO;
import org.acme.models.asaas.ContasApagar.ContasAPagar;
import org.acme.models.asaas.Status;

import javax.persistence.OneToOne;

@Getter
@Setter
public class ContasAPagarDTO implements DTO {
    private String identificationField;
    private Status status;
    private String scheduleDate;
    private String description;
    private double discount;
    private double interest;
    private double fine;
    private String dueDate;
    private String paymentDate;
    private String fee;
    private String companyName;
    private String transactionReceiptUrl;
    private String canBeCancelled;
    private String failReasons;
    private double value;
    private boolean retorno;
    @Type(Cliente.class)
    private ClienteDTO cliente;

}
