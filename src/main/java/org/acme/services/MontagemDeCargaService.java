package org.acme.services;

import com.google.gson.JsonSyntaxException;
import org.acme.Util.PrimitiveUtil.ArrayUtil;
import org.acme.Util.PrimitiveUtil.BooleanUtils;
import org.acme.Util.PrimitiveUtil.LongUtil;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.*;
import org.acme.models.DTO.ItensDTO;
import org.acme.models.DTO.MontagemDeCargaDTO;
import org.acme.models.Nota_fiscal_eletronica.Transportador;
import org.acme.models.Nota_fiscal_eletronica.Veiculo;
import org.acme.models.consulta.PreparacaoDeCargaPreview;
import org.acme.models.enums.PrioridadeCarga;
import org.acme.models.enums.TipoDeCarga;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class MontagemDeCargaService extends Service implements ServiceInterface {
    ValidacaoException validacao = new ValidacaoException();
    Integer tamanho = 0;
    Double fracaoDaCarga = 0D;

    public Response listAll() {
        List<MontagemDeCarga> resultado = em.createQuery("SELECT M FROM MontagemDeCarga M "
                        , MontagemDeCarga.class)
                .getResultList();
        if (resultado.isEmpty()) {
            return ResponseBuilder.responseNoContent();
        } else {
            return ResponseBuilder.responseOk(resultado);
        }

    }

    @Override
    public Response create(String json) {
            PreparacaoDeCargaPreview preparacaoDeCargaPreview = gson.fromJson(json, PreparacaoDeCargaPreview.class);
            validaDTO(preparacaoDeCargaPreview.getMontagemDeCargaDTO());
            MontagemDeCarga montagemDeCarga = new MontagemDeCarga();
            if (preparacaoDeCargaPreview.getMontagemDeCargaDTO().getIsManual()) {
                montagemManual(montagemDeCarga, preparacaoDeCargaPreview.getMontagemDeCargaDTO());
            } else {
                montagemAutomatica(montagemDeCarga, preparacaoDeCargaPreview.getMontagemDeCargaDTO());
            }
            convertToModel(montagemDeCarga, preparacaoDeCargaPreview.getMontagemDeCargaDTO());
            em.persist(montagemDeCarga);
            PreparacaoDeCarga preparacaoDeCarga = new PreparacaoDeCarga();
            fieldUtil.updateFieldsDtoToModel(preparacaoDeCarga,preparacaoDeCargaPreview);
            preparacaoDeCarga.setMontagemDeCarga(montagemDeCarga);
            return ResponseBuilder.responseOk(montagemDeCarga);
    }

    private void convertToModel(MontagemDeCarga montagemDeCarga, MontagemDeCargaDTO montagemDeCargaDTO) {
        if (montagemDeCargaDTO.getTipoDeCarga() != null) {
            montagemDeCarga.setTipoDeCarga(montagemDeCargaDTO.getTipoDeCarga());
        }
        if (montagemDeCargaDTO.getIsManual() != null) {
            montagemDeCarga.setIsManual(montagemDeCargaDTO.getIsManual());
        }
        if (montagemDeCargaDTO.getTransportador() != null) {
            Transportador transportador = Transportador.findById(montagemDeCargaDTO.getTransportador().getUuid());
            if (transportador != null) {
                montagemDeCarga.setTransportador(transportador);
            }
        }
        if (montagemDeCargaDTO.getVeiculo() != null) {
            Veiculo veiculo = Veiculo.findById(montagemDeCargaDTO.getVeiculo().getUuid());
            if (veiculo != null) {
                montagemDeCarga.setVeiculo(veiculo);
            }
        }

        if (ArrayUtil.validaArray(montagemDeCargaDTO.getItens())) {
            montagemDeCarga.setItens(new ArrayList<>());
            montagemDeCarga.setItensExternos(new ArrayList<>());
            montagemDeCargaDTO.getItens().forEach(item -> {
                if (TipoDeCarga.INTERNA.equals(montagemDeCargaDTO.getTipoDeCarga())) {
                    Itens itemBD = Itens.findById(item.getUuid());
                    montagemDeCarga.getItens().add(itemBD);
                } else {
                    ItensExternos itemExternoBD = ItensExternos.findById(item.getUuid());
                    montagemDeCarga.getItensExternos().add(itemExternoBD);
                }
            });
        }
    }

    @Override
    public Response update(String uuid, String json) {
            return ResponseBuilder.responseOk(null);
    }

    public void montagemAutomatica(MontagemDeCarga montagemDeCarga, MontagemDeCargaDTO montagemDeCargaDTO) {
        fieldUtil.updateFieldsDtoToModel(montagemDeCarga, montagemDeCargaDTO);

        if (ArrayUtil.validaArray(montagemDeCargaDTO.getItens())) {
            tamanho = montagemDeCargaDTO.getItens().size();
        } else {
            validacao.add("É necessario informar itens para completar a montar a carga");
            validacao.lancaErro();
        }
        if (montagemDeCargaDTO.getVeiculo() != null) {
            fracaoDaCarga = (double) (montagemDeCargaDTO.getVeiculo().getLotacaoMaxima() / tamanho);
        } else {
            validacao.add("Veiculo tem informações de lotação invalidas");
            validacao.lancaErro();
        }

        montagemDeCargaDTO.getItens().forEach(item -> {
            Double porcentagem = porcentagemDoItemNaCarga(item, fracaoDaCarga, tamanho);
            Double quantidade = item.getProduto().getPesoCubico() * porcentagem;
            long valor = quantidade.longValue();
            item.setQuantidade(valor);
        });
    }

    private Double porcentagemDoItemNaCarga(ItensDTO item, Double fracaoDaCarga, Integer tamanho) {
        if (PrioridadeCarga.MUITO_ALTA.equals(item.getPrioridade())) {
            return fracaoDaCarga * 50 / tamanho;
        } else if (PrioridadeCarga.ALTA.equals(item.getPrioridade())) {
            return fracaoDaCarga * 40 / tamanho;
        } else if (PrioridadeCarga.MEDIA.equals(item.getPrioridade())) {
            return fracaoDaCarga * 30 / tamanho;
        } else if (PrioridadeCarga.BAIXA.equals(item.getPrioridade())) {
            return fracaoDaCarga * 20 / tamanho;
        } else if (PrioridadeCarga.MUITO_BAIXA.equals(item.getPrioridade())) {
            return fracaoDaCarga * 10 / tamanho;
        } else {
            return 0D;
        }
    }

    public void montagemManual(MontagemDeCarga montagemDeCarga, MontagemDeCargaDTO montagemDeCargaDTO) {
        fieldUtil.updateFieldsDtoToModel(montagemDeCarga, montagemDeCargaDTO);
        montagemDeCarga.setItens(new ArrayList<>());
        montagemDeCarga.setItensExternos(new ArrayList<>());
        if (TipoDeCarga.EXTERNA.equals(montagemDeCargaDTO.getTipoDeCarga())) {
            montagemDeCargaDTO.getItens().forEach(itens -> {
                ItensExternos itensBD = ItensExternos.findById(itens.getUuid());
                Estoque estoque = estoqueService.findByProduct(itensBD.getProduto());
                if (estoque.getQuantidade() <= itens.getQuantidade()) {
                    validacao.add("Quantidade de itens ultrapassa a quantidade em estoque");
                    validacao.lancaErro();
                }
                montagemDeCarga.getItensExternos().add(itensBD);
            });
        } else {
            montagemDeCargaDTO.getItens().forEach(itens -> {
                Itens itensBD = Itens.findById(itens.getUuid());
                Estoque estoque = estoqueService.findByProduct(itensBD.getProduto());
                if (estoque.getQuantidade() <= itens.getQuantidade()) {
                    validacao.add("Quantidade de itens ultrapassa a quantidade em estoque");
                    validacao.lancaErro();
                }
                montagemDeCarga.getItens().add(itensBD);
            });
        }
    }


    public void validaDTO(MontagemDeCargaDTO montagemDeCargaDTO) {
        if (BooleanUtils.isTrue(montagemDeCargaDTO.getIsManual())) {
            if (!ArrayUtil.validaArray(montagemDeCargaDTO.getItens())) {
                validacao.add("Deve adicionar itens na carga");
            } else {
                montagemDeCargaDTO.getItens().forEach(itens -> {
                    if (itens.getPrioridade() == null) {
                        validacao.add(itens.getProduto().getNome() + " esta sem prioridade, favor informar");
                    }
                });
            }
            if (montagemDeCargaDTO.getTransportador() == null) {
                validacao.add("É Extremamente necessario informar um transportador");
            }
            if (montagemDeCargaDTO.getVeiculo() == null) {
                validacao.add("É Extremamente necessario informar um veiculo");
            }
            if (montagemDeCargaDTO.getVeiculo() != null) {
                if (montagemDeCargaDTO.getVeiculo().getLotacaoMaxima() == null || montagemDeCargaDTO.getVeiculo().getLotacaoMaxima() <= 0) {
                    validacao.add("Seu veiculo esta com problema na lotação, favor arrumar");
                }
            }
            if (montagemDeCargaDTO.getMotorista() == null) {
                validacao.add("É necessario informar um motorista");
            } else if (montagemDeCargaDTO.getMotorista() != null && StringUtil.stringValida(montagemDeCargaDTO.getMotorista().getUuid())) {
                validacao.add("É necessario informar um motorista");
            }
        }

        validacao.lancaErro();
    }

    public Response delete(String uuid) {
            MontagemDeCarga montagemDeCarga = MontagemDeCarga.findById(uuid);
            montagemDeCarga.delete();
            return ResponseBuilder.responseOk(montagemDeCarga);
    }

    public Response criarPreparacaoDeCarga(String json) {
        try {
            MontagemDeCargaDTO montagemDeCargaDTO = gson.fromJson(json, MontagemDeCargaDTO.class);
            validaDTO(montagemDeCargaDTO);
            PreparacaoDeCargaPreview preparacaoDeCarga = criaPreparacaoDeCarga(montagemDeCargaDTO);
            return ResponseBuilder.responseOk(preparacaoDeCarga);
        } catch (JsonSyntaxException j) {
            j.printStackTrace();
            return ResponseBuilder.returnJsonSyntax();
        } catch (ValidacaoException v) {
            return ResponseBuilder.returnResponse(v);
        } catch (Throwable t) {
            t.printStackTrace();
            return ResponseBuilder.returnResponse();
        }

    }

    private PreparacaoDeCargaPreview criaPreparacaoDeCarga(MontagemDeCargaDTO montagemDeCargaDTO) {
        Long capacidade = montagemDeCargaDTO.getVeiculo().getLotacaoMaxima();
        Long tamanhoMedioDosProdutos = 0L;
        Double fatorOcupacao = 1.2;
        for (ItensDTO iten : montagemDeCargaDTO.getItens()) {
            tamanhoMedioDosProdutos += LongUtil.parseFromDouble(iten.getProduto().getPesoCubico());
        }
        tamanhoMedioDosProdutos = tamanhoMedioDosProdutos / montagemDeCargaDTO.getItens().size();
        Long capacidadeTotalDeProdutos = capacidade / tamanhoMedioDosProdutos;

        PreparacaoDeCargaPreview preparacaoDeCargaPreview = new PreparacaoDeCargaPreview();
        preparacaoDeCargaPreview.setCapacidadeVeiculo(capacidade);
        preparacaoDeCargaPreview.setQuantidadeDeItens(LongUtil.parseFromInteger(montagemDeCargaDTO.getItens().size()));
        preparacaoDeCargaPreview.setFatorOcupacao(fatorOcupacao);
        preparacaoDeCargaPreview.setTamanhoMedioDosProdutos(tamanhoMedioDosProdutos);
        preparacaoDeCargaPreview.setCapacidadeTotalDeProdutos(capacidadeTotalDeProdutos);
        preparacaoDeCargaPreview.setMontagemDeCargaDTO(montagemDeCargaDTO);
        return preparacaoDeCargaPreview;


    }
}

