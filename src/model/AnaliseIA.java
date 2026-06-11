package model;

import java.util.ArrayList;
import java.util.List;

public class AnaliseIA {

    private int idAnalise;
    private String sentimentoGeral;
    private double scoreRisco;
    private double scoreOportunidade;
    private Reuniao reuniao;
    private List<Insight> insights;

    public AnaliseIA(int idAnalise, Reuniao reuniao) {
        this.idAnalise = idAnalise;
        this.reuniao = reuniao;
        this.sentimentoGeral = "Nao analisado";
        this.insights = new ArrayList<>();
    }

    public int getIdAnalise() {
        return idAnalise;
    }

    public void setIdAnalise(int idAnalise) {
        this.idAnalise = idAnalise;
    }

    public String getSentimentoGeral() {
        return sentimentoGeral;
    }

    public void setSentimentoGeral(String sentimentoGeral) {
        this.sentimentoGeral = sentimentoGeral;
    }

    public double getScoreRisco() {
        return scoreRisco;
    }

    public void setScoreRisco(double scoreRisco) {
        this.scoreRisco = scoreRisco;
    }

    public double getScoreOportunidade() {
        return scoreOportunidade;
    }

    public void setScoreOportunidade(double scoreOportunidade) {
        this.scoreOportunidade = scoreOportunidade;
    }

    public Reuniao getReuniao() {
        return reuniao;
    }

    public void setReuniao(Reuniao reuniao) {
        this.reuniao = reuniao;
    }

    public List<Insight> getInsights() {
        return insights;
    }

    public void adicionarInsight(Insight insight) {
        insights.add(insight);
    }

    public void gerarAnalise(String sentimentoGeral, double scoreRisco, double scoreOportunidade) {
        this.sentimentoGeral = sentimentoGeral;
        this.scoreRisco = scoreRisco;
        this.scoreOportunidade = scoreOportunidade;
    }

    public void exibirResultado() {
        System.out.println("----- Analise #" + idAnalise + " -----");
        System.out.println("Sentimento Geral......: " + sentimentoGeral);
        System.out.printf("Score de Risco........: %.2f%%%n", scoreRisco);
        System.out.printf("Score de Oportunidade.: %.2f%%%n", scoreOportunidade);
        System.out.println("Insights gerados......: " + insights.size());
        for (Insight insight : insights) {
            insight.exibirInsight();
        }
    }
}
