package service;

import model.AnaliseIA;
import model.Reuniao;
import model.Transcricao;

import java.text.Normalizer;
import java.util.Locale;

public class AnaliseService {

    private static final String[] PALAVRAS_RISCO = {
            "cancelar", "cancelamento", "concorrente", "concorrencia", "insatisfeito",
            "insatisfacao", "problema", "reclamacao", "trocar", "ruim", "demora", "demorado", "churn"
    };

    private static final String[] PALAVRAS_OPORTUNIDADE = {
            "interessado", "interesse", "orcamento", "comprar", "expandir",
            "upgrade", "upsell", "modulo", "investir", "contratar", "ampliar"
    };

    private int proximoId = 1;

    /**
     * Funcionalidade 3 - Executar Analise.
     * Le a transcricao da reuniao e calcula indicadores de risco,
     * oportunidade e o sentimento geral do cliente.
     */
    public AnaliseIA executarAnalise(Reuniao reuniao) {
        Transcricao transcricao = reuniao.getTranscricao();
        if (transcricao == null) {
            throw new IllegalStateException("A reuniao nao possui transcricao registrada.");
        }

        String texto = normalizar(transcricao.getTextoBruto());

        double scoreRisco = calcularScore(texto, PALAVRAS_RISCO);
        double scoreOportunidade = calcularScore(texto, PALAVRAS_OPORTUNIDADE);
        String sentimentoGeral = calcularSentimento(scoreRisco, scoreOportunidade);

        AnaliseIA analise = new AnaliseIA(proximoId++, reuniao);
        analise.gerarAnalise(sentimentoGeral, scoreRisco, scoreOportunidade);
        reuniao.adicionarAnalise(analise);

        return analise;
    }

    /**
     * Remove acentos e cedilhas e converte para minusculas, para que palavras
     * como "insatisfação" e "concorrência" possam ser comparadas com as
     * palavras-chave (que estao escritas sem acento) e nao sejam quebradas
     * pelo split de "\W+" (que trata letras acentuadas como separadores).
     */
    private String normalizar(String texto) {
        String semAcentos = Normalizer.normalize(texto, Normalizer.Form.NFD)
                .replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return semAcentos.toLowerCase(Locale.ROOT);
    }

    private double calcularScore(String texto, String[] palavrasChave) {
        String[] palavras = texto.split("\\W+");
        if (palavras.length == 0) {
            return 0.0;
        }

        int ocorrencias = 0;
        for (String palavra : palavras) {
            for (String chave : palavrasChave) {
                if (palavra.equals(chave)) {
                    ocorrencias++;
                }
            }
        }

        return (ocorrencias * 100.0) / palavras.length;
    }

    private String calcularSentimento(double scoreRisco, double scoreOportunidade) {
        if (scoreRisco > scoreOportunidade) {
            return "Negativo";
        } else if (scoreOportunidade > scoreRisco) {
            return "Positivo";
        } else {
            return "Neutro";
        }
    }
}
