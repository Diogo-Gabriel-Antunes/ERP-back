package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.Type;
import org.acme.models.Categoria;
import org.acme.models.Imagem;
import org.acme.models.Imposto;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ProdutoDTO implements DTO {
    private String uuid;
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private String codigo;
    private boolean status;
    private List<Imposto> imposto;
    private Categoria categoria;
    private List<Imagem> imagens;
    private Double precoUnitario;
    private Double pesoBruto;
    private Double pesoOriginal;
    private Double pesoCubico;
    @Column(nullable = true)
    private long quantidadeMinima;
    @Type(List.class)
    private List<InformacaoDeFabricacaoDTO> informacaoDeFabricacao;

}
