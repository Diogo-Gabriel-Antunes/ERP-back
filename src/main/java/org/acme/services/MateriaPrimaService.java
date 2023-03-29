package org.acme.services;

import org.acme.Util.ConversorDeUnidadesUtils;
import org.acme.Util.PrimitiveUtil.StringUtil;
import org.acme.response.ResponseBuilder;
import org.acme.exceptions.ValidacaoException;
import org.acme.models.DTO.MateriaPrimaDTO;
import org.acme.models.InformacaoDeFabricacao;
import org.acme.models.MateriaPrima;
import org.acme.models.Produto;
import org.acme.models.SaidaDeProduto;
import org.acme.models.enums.UnidadeDeMedida;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.Response;
import java.util.Optional;


@ApplicationScoped
public class MateriaPrimaService extends Service {

    @Transactional
    public Response create(String json) {
            MateriaPrimaDTO dto = gson.fromJson(json, MateriaPrimaDTO.class);
            validacaoMetariaPrima(dto);
            MateriaPrima materiaPrima = new MateriaPrima();
            fieldUtil.updateFieldsDtoToModel(materiaPrima, dto);
            em.persist(materiaPrima);
            return ResponseBuilder.responseOk(materiaPrima);
    }

    private void validacaoMetariaPrima(MateriaPrimaDTO dto) {
        ValidacaoException validacao = new ValidacaoException();

        if (!StringUtil.stringValida(dto.getNome())) {
            validacao.add("Campo nome invalido");
        }
        if (!StringUtil.stringValida(dto.getDescricao())) {
            validacao.add("Campo nome invalido");
        }
        if (dto.getQuantidade() <= 0) {
            validacao.add("Campo quantidade invalido");
            validacao.add("Campo quantidade deve ser maior ou igual a 0");
        }
        if (dto.getPrecoUnitario() < 0) {
            validacao.add("Preco unitario deve ser maior que 0");
        }

        validacao.lancaErro();

    }

    public Response update(String uuid, String json) {
            Optional<MateriaPrima> materiaPrima = MateriaPrima.findByIdOptional(uuid);
            if (materiaPrima.isPresent()) {
                MateriaPrima materiaBD = em.merge(materiaPrima.get());
                MateriaPrimaDTO materiaPrimaDTO = gson.fromJson(json, MateriaPrimaDTO.class);
                fieldUtil.updateFieldsDtoToModel(materiaBD, materiaPrimaDTO);
                em.persist(materiaPrima);
                return ResponseBuilder.responseOk(materiaBD);
            }
            throw new RuntimeException();
    }

    public Response delete(String uuid) {
            MateriaPrima materiaPrima = MateriaPrima.findById(uuid);
            if (materiaPrima != null) {
                materiaPrima.delete();
                return ResponseBuilder.responseOk(materiaPrima);
            }
            throw new RuntimeException();
    }

    public MateriaPrima findOne(String uuid) {
        return em.createQuery("SELECT m FROM MateriaPrima m WHERE m.uuid = :uuid", MateriaPrima.class)
                .setParameter("uuid", uuid)
                .getSingleResult();
    }

    public void validaQuantidadeDeMateriaPrima(Produto produto, SaidaDeProduto saidaDeProduto, ValidacaoException validacaoException) {
        produto.getInformacaoDeFabricacao().forEach(infos -> {
            if (validaDistancia(infos.getUnidadeDaQuantidadeGasta())) {
                if (UnidadeDeMedida.METROS.equals(infos.getUnidadeDaQuantidadeGasta()) && UnidadeDeMedida.CENTIMETROS.equals(infos.getMateriaPrima().getUnidadeDeMedida())) {
                    double valorConvertido = ConversorDeUnidadesUtils.metrosParaCentimetros(multiplicacaoParaConversao(saidaDeProduto, infos));
                    valorConvertidoMaiorQueOEsperado(produto, validacaoException, infos, valorConvertido);
                } else if (UnidadeDeMedida.CENTIMETROS.equals(infos.getUnidadeDaQuantidadeGasta()) && UnidadeDeMedida.METROS.equals(infos.getMateriaPrima().getUnidadeDeMedida())) {
                    double valorConvertido = ConversorDeUnidadesUtils.centimetrosParaMetros(multiplicacaoParaConversao(saidaDeProduto, infos));
                    valorConvertidoMaiorQueOEsperado(produto, validacaoException, infos, valorConvertido);
                } else if (UnidadeDeMedida.METROS.equals(infos.getUnidadeDaQuantidadeGasta()) && UnidadeDeMedida.POLEGADAS.equals(infos.getMateriaPrima().getUnidadeDeMedida())) {
                    double valorConvertido = ConversorDeUnidadesUtils.metrosParaPolegadas(multiplicacaoParaConversao(saidaDeProduto, infos));
                    valorConvertidoMaiorQueOEsperado(produto, validacaoException, infos, valorConvertido);
                } else if (UnidadeDeMedida.POLEGADAS.equals(infos.getUnidadeDaQuantidadeGasta()) && UnidadeDeMedida.METROS.equals(infos.getMateriaPrima().getUnidadeDeMedida())) {
                    double valorConvertido = ConversorDeUnidadesUtils.metrosParaPolegadas(multiplicacaoParaConversao(saidaDeProduto, infos));
                    valorConvertidoMaiorQueOEsperado(produto, validacaoException, infos, valorConvertido);
                } else if (UnidadeDeMedida.CENTIMETROS.equals(infos.getUnidadeDaQuantidadeGasta()) && UnidadeDeMedida.POLEGADAS.equals(infos.getMateriaPrima().getUnidadeDeMedida())) {
                    double valorConvertido = ConversorDeUnidadesUtils.centimetrosParaPolegadas(multiplicacaoParaConversao(saidaDeProduto, infos));
                    valorConvertidoMaiorQueOEsperado(produto, validacaoException, infos, valorConvertido);
                } else if (UnidadeDeMedida.POLEGADAS.equals(infos.getUnidadeDaQuantidadeGasta()) && UnidadeDeMedida.CENTIMETROS.equals(infos.getMateriaPrima().getUnidadeDeMedida())) {
                    double valorConvertido = ConversorDeUnidadesUtils.metrosParaPolegadas(multiplicacaoParaConversao(saidaDeProduto, infos));
                    valorConvertidoMaiorQueOEsperado(produto, validacaoException, infos, valorConvertido);
                }else if( infos.getUnidadeDaQuantidadeGasta().equals(infos.getMateriaPrima().getUnidadeDeMedida())){
                    System.out.println("Conversão ocorreu tudo bem");
                }else{
                    validacaoException.add("Unidade distancia não reconhecida");
                }
            } else if (validaPeso(infos.getUnidadeDaQuantidadeGasta())) {
                if (UnidadeDeMedida.KG.equals(infos.getUnidadeDaQuantidadeGasta()) && UnidadeDeMedida.GRAMAS.equals(infos.getMateriaPrima().getUnidadeDeMedida())) {
                    double valorConvertido = ConversorDeUnidadesUtils.kgParaGrama(multiplicacaoParaConversao(saidaDeProduto, infos));
                    valorConvertidoMaiorQueOEsperado(produto, validacaoException, infos, valorConvertido);
                } else if (UnidadeDeMedida.GRAMAS.equals(infos.getUnidadeDaQuantidadeGasta()) && UnidadeDeMedida.KG.equals(infos.getMateriaPrima().getUnidadeDeMedida())) {
                    double valorConvertido = ConversorDeUnidadesUtils.gramaParaKg(multiplicacaoParaConversao(saidaDeProduto, infos));
                    valorConvertidoMaiorQueOEsperado(produto, validacaoException, infos, valorConvertido);
                }else if(infos.getUnidadeDaQuantidadeGasta().equals(infos.getMateriaPrima().getUnidadeDeMedida())){
                    System.out.println("Conversão ocorreu tudo bem");
                }else{
                    validacaoException.add("Unidade distancia não reconhecida");
                }
            } else if (validaLiquido(infos.getUnidadeDaQuantidadeGasta())) {
                if (UnidadeDeMedida.LITROS.equals(infos.getUnidadeDaQuantidadeGasta()) && UnidadeDeMedida.GALOES.equals(infos.getMateriaPrima().getUnidadeDeMedida())) {
                    double valorConvertido = ConversorDeUnidadesUtils.kgParaGrama(multiplicacaoParaConversao(saidaDeProduto, infos));
                    valorConvertidoMaiorQueOEsperado(produto, validacaoException, infos, valorConvertido);
                } else if (UnidadeDeMedida.GALOES.equals(infos.getUnidadeDaQuantidadeGasta()) && UnidadeDeMedida.LITROS.equals(infos.getMateriaPrima().getUnidadeDeMedida())) {
                    double valorConvertido = ConversorDeUnidadesUtils.gramaParaKg(multiplicacaoParaConversao(saidaDeProduto, infos));
                    valorConvertidoMaiorQueOEsperado(produto, validacaoException, infos, valorConvertido);
                }else if(infos.getUnidadeDaQuantidadeGasta().equals(infos.getMateriaPrima().getUnidadeDeMedida())){
                    System.out.println("Conversão ocorreu tudo bem");
                }else{
                    validacaoException.add("Unidade distancia não reconhecida");
                }
            } else {
                validacaoException.add("Algo de errado com a unidade de medida deu errado");
            }
        });
    }

    private static void valorConvertidoMaiorQueOEsperado(Produto produto, ValidacaoException validacaoException, InformacaoDeFabricacao infos, double valorConvertido) {
        if (valorConvertido > infos.getMateriaPrima().getQuantidade()) {
            validacaoException.add("Não existe materia prima suficiente para produzir o produto : " + produto.getNome());
        }
    }

    private static double multiplicacaoParaConversao(SaidaDeProduto saidaDeProduto, InformacaoDeFabricacao infos) {
        return saidaDeProduto.getQuantidade() * infos.getQuantidadeNecessaria();
    }

    private boolean validaLiquido(UnidadeDeMedida unidadeDaQuantidadeGasta) {
        return UnidadeDeMedida.LITROS.equals(unidadeDaQuantidadeGasta) || UnidadeDeMedida.GALOES.equals(unidadeDaQuantidadeGasta);
    }

    private boolean validaPeso(UnidadeDeMedida unidadeDaQuantidadeGasta) {
        return UnidadeDeMedida.KG.equals(unidadeDaQuantidadeGasta) || UnidadeDeMedida.GRAMAS.equals(unidadeDaQuantidadeGasta);
    }

    private boolean validaDistancia(UnidadeDeMedida unidadeDaQuantidadeGasta) {
        return UnidadeDeMedida.METROS.equals(unidadeDaQuantidadeGasta) || UnidadeDeMedida.CENTIMETROS.equals(unidadeDaQuantidadeGasta)
                || UnidadeDeMedida.POLEGADAS.equals(unidadeDaQuantidadeGasta);
    }
}
