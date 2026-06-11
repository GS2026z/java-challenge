package service;

import model.AnaliseIA;
import model.Insight;
import model.ProdutoTotvs;
import model.Transcricao;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class InsightService {

    private static final double LIMIAR_RISCO = 5.0;
    private static final double LIMIAR_OPORTUNIDADE = 5.0;

    private int proximoId = 1;

    /**
     * Funcionalidade 4 - Gerar Insights.
     * A partir da analise realizada, identifica produtos TOTVS citados
     * (mapeamento de ecossistema), riscos de churn e oportunidades de upsell.
     */
    public List<Insight> gerarInsights(AnaliseIA analise, Transcricao transcricao, List<ProdutoTotvs> catalogoProdutos) {
        List<Insight> insightsGerados = new ArrayList<>();
        String texto = transcricao.getTextoBruto().toLowerCase(Locale.ROOT);

        for (ProdutoTotvs produto : catalogoProdutos) {
            if (texto.contains(produto.getNomeProduto().toLowerCase(Locale.ROOT))) {
                Insight insight = new Insight(proximoId++, "Produto Identificado",
                        "Produto " + produto.getNomeProduto() + " foi mencionado na conversa.",
                        "Media", produto);
                registrarInsight(analise, insightsGerados, insight);
            }
        }

        if (analise.getScoreRisco() >= LIMIAR_RISCO) {
            Insight insight = new Insight(proximoId++, "Risco de Churn",
                    "Foram identificados sinais de insatisfacao ou risco de cancelamento.",
                    "Alta", null);
            registrarInsight(analise, insightsGerados, insight);
        }

        if (analise.getScoreOportunidade() >= LIMIAR_OPORTUNIDADE) {
            Insight insight = new Insight(proximoId++, "Oportunidade de Upsell",
                    "Foram identificados sinais de interesse em novos produtos ou modulos.",
                    "Alta", null);
            registrarInsight(analise, insightsGerados, insight);
        }

        if (insightsGerados.isEmpty()) {
            Insight insight = new Insight(proximoId++, "Observacao",
                    "Nenhum padrao relevante foi identificado nesta transcricao.",
                    "Baixa", null);
            registrarInsight(analise, insightsGerados, insight);
        }

        return insightsGerados;
    }

    private void registrarInsight(AnaliseIA analise, List<Insight> insightsGerados, Insight insight) {
        insight.gerarInsight();
        analise.adicionarInsight(insight);
        insightsGerados.add(insight);
    }
}
