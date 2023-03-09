package org.acme.models.DTO;

import lombok.Getter;
import lombok.Setter;
import org.acme.Anotacao.DTO.LabelForm;
import org.acme.Anotacao.DTO.Type;
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
    @LabelForm("Nome")
    private String nome;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAlteracao;
    private String codigo;
    private boolean status;
    private List<Imposto> imposto;
    @LabelForm("Categoria")
    private Categoria categoria;
    private List<Imagem> imagens;
    @LabelForm("Preço unitario")
    private Double precoUnitario;
    private Double pesoBruto;
    private Double pesoOriginal;
    @LabelForm("Peso cubico")
    private Double pesoCubico;
    @Column(nullable = true)
    @LabelForm("Quantidade Minima")
    private long quantidadeMinima;
    @Type(List.class)
    private List<InformacaoDeFabricacaoDTO> informacaoDeFabricacao;

}
