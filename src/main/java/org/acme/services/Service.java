package org.acme.services;

import com.google.gson.Gson;
import org.acme.Util.*;
import org.acme.services.CobrancasService.DiscountService;
import org.acme.services.CobrancasService.FineService;
import org.acme.services.CobrancasService.InterestService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.time.format.DateTimeFormatter;

@ApplicationScoped
public class Service {
    @Inject
    protected EntityManager em;
    protected Gson gson = new GsonUtil().parser;
    protected FieldUtil fieldUtil = new FieldUtil();
    protected ArrayUtil arrayUtil = new ArrayUtil();
    protected DataUtil dataUtil = new DataUtil();
    @Inject
    protected MapaEstoqueService mapaEstoqueService;
    @Inject
    protected EnderecoService enderecoService;
    @Inject
    protected AssinaturaService assinaturaService ;
    @Inject
    protected AtividadeService atividadeService;
    @Inject
    protected BoletoService boletoService ;
    @Inject
    protected CategoryService categoryService ;
    @Inject
    protected ClienteService clienteService;
    @Inject
    protected CobrancaService cobrancaService ;
    @Inject
    protected CobrancaRetornoService cobrancaRetornoService ;
    @Inject
    protected CompraService compraService ;
    @Inject
    protected ContasAPagarService contasAPagarService;
    @Inject
    protected ContatoService contatoService ;
    @Inject
    protected CorService corService ;
    @Inject
    protected FluxoDeCaixaService fluxoDeCaixaService ;
    @Inject
    protected FuncionarioService funcionarioService ;
    @Inject
    protected ImportacaoService importacaoService ;
    @Inject
    protected ImpostoService impostoService ;
    @Inject
    protected ItensService itensService ;
    @Inject
    protected LojaService lojaService;
    @Inject
    protected NfeService nfeService ;
    @Inject
    protected OrdemDeProducaoService ordemDeProducaoService;
    @Inject
    protected ProdutoService produtoService ;
    @Inject
    protected ProjetoService projetoService ;
    @Inject
    protected PedidoService pedidoService;
    @Inject
    protected EstoqueService estoqueService;
    @Inject
    protected TransportadoraService transportadoraService;
    @Inject
    protected UnidadeNFEService unidadeNFEService ;
    @Inject
    protected UnidadesService unidadesService ;
    @Inject
    protected VeiculoService veiculoService ;
    @Inject
    protected VendasService vendasService ;
    @Inject
    protected DiscountService discountService ;
    @Inject
    protected FineService fineService ;
    @Inject
    protected InterestService interestService ;
    @Inject
    protected  SaidaDeProdutoService saidaDeProdutoService;
    @Inject
    protected  EntradaDeProdutoService entradaDeProdutoService;
    @Inject
    protected  FornecedorService fornecedorService;
    @Inject
    protected  MateriaPrimaService materiaPrimaService;
    @Inject
    protected DevolucaoService devolucaoService;
    @Inject
    protected MotivoDevolucaoService motivoDevolucaoService;
    @Inject
    protected MontagemDeCargaService montagemDeCargaService;
    @Inject
    protected MotoristaService motoristaService;
}
