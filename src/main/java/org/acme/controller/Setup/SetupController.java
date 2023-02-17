package org.acme.controller.Setup;

import org.acme.models.Setup.LinkMenuLateral;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
@Path("setup")
public class SetupController {

    @GET
    @Path("menulateral")
    public List<LinkMenuLateral> listLinkMenuLateral(){
        List<LinkMenuLateral> linkMenuLaterals = new ArrayList<>(){{
            add(new LinkMenuLateral("/produtos","Produtos"));
            add(new LinkMenuLateral("/estoque","Estoque"));
            add(new LinkMenuLateral("/pedidos","Pedidos"));
            add(new LinkMenuLateral("/boletos","Boletos"));
            add(new LinkMenuLateral("/cobrancas","Cobrancas"));
            add(new LinkMenuLateral("/transportadoras","Transportadoras"));
            add(new LinkMenuLateral("/clientes","Clientes"));
            add(new LinkMenuLateral("/unidades","Unidades"));
            add(new LinkMenuLateral("/contasapagar","Contas a pagar"));
            add(new LinkMenuLateral("/ordemdeproducao","Ordem de produção"));
            add(new LinkMenuLateral("/criarpedido","Criar Pedido"));
            add(new LinkMenuLateral("/funcionario","Funcionario"));
            add(new LinkMenuLateral("/atividades","Atividades"));
            add(new LinkMenuLateral("/compras","Compras"));
            add(new LinkMenuLateral("/projetos","Projetos"));
            add(new LinkMenuLateral("/nfe","Nota fiscal - NFE"));
            add(new LinkMenuLateral("/veiculo","Veiculo"));
            add(new LinkMenuLateral("/cor","Cor"));
            add(new LinkMenuLateral("/imposto","Imposto"));
            add(new LinkMenuLateral("/medida","Medida"));
            add(new LinkMenuLateral("/assinatura","Assinatura"));
            add(new LinkMenuLateral("/loja","Loja"));
            add(new LinkMenuLateral("/materiaprima","Materia Prima"));
            add(new LinkMenuLateral("/saidadeproduto","Saida de produto"));
        }};
        return linkMenuLaterals;
    }
}
