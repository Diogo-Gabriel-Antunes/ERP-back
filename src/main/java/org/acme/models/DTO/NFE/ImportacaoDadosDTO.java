package org.acme.models.DTO.NFE;

import lombok.Getter;
import lombok.Setter;
import org.acme.Util.InterfacesUtil.DTO;
import org.acme.models.Nota_fiscal_eletronica.Adicoes;
import org.acme.models.Nota_fiscal_eletronica.Adquirente;
import org.acme.models.Nota_fiscal_eletronica.Desembaraco;
import org.acme.models.Nota_fiscal_eletronica.ViaTransporte;

import java.util.List;

@Getter
@Setter
public class ImportacaoDadosDTO implements DTO {
    private String numero;
    private String dataEmissao;
    private ViaTransporte viaTransporte;
    private Double valorAfrmm;
    private int formaImportacao;
    private String codigoExportador;
    private Desembaraco desembaraco;
    private Adquirente adquirente;
    private List<Adicoes> adicoes;
}
