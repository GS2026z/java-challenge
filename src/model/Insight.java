package model;

public class Insight {

    private int idInsight;
    private String tipoInsight;
    private String descricao;
    private String prioridade;
    private ProdutoTotvs produtoRelacionado;

    public Insight(int idInsight, String tipoInsight, String descricao, String prioridade, ProdutoTotvs produtoRelacionado) {
        this.idInsight = idInsight;
        this.tipoInsight = tipoInsight;
        this.descricao = descricao;
        this.prioridade = prioridade;
        this.produtoRelacionado = produtoRelacionado;
    }

    public int getIdInsight() {
        return idInsight;
    }

    public void setIdInsight(int idInsight) {
        this.idInsight = idInsight;
    }

    public String getTipoInsight() {
        return tipoInsight;
    }

    public void setTipoInsight(String tipoInsight) {
        this.tipoInsight = tipoInsight;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getPrioridade() {
        return prioridade;
    }

    public void setPrioridade(String prioridade) {
        this.prioridade = prioridade;
    }

    public ProdutoTotvs getProdutoRelacionado() {
        return produtoRelacionado;
    }

    public void setProdutoRelacionado(ProdutoTotvs produtoRelacionado) {
        this.produtoRelacionado = produtoRelacionado;
    }

    public void gerarInsight() {
        System.out.println("Insight gerado: [" + tipoInsight + "] " + descricao);
    }

    public void exibirInsight() {
        String produto = produtoRelacionado != null ? " | Produto: " + produtoRelacionado.getNomeProduto() : "";
        System.out.println("  - (" + prioridade + ") [" + tipoInsight + "] " + descricao + produto);
    }
}
