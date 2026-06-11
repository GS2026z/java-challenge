package model;

public class ProdutoTotvs {

    private int idProduto;
    private String nomeProduto;
    private String categoria;

    public ProdutoTotvs(int idProduto, String nomeProduto, String categoria) {
        this.idProduto = idProduto;
        this.nomeProduto = nomeProduto;
        this.categoria = categoria;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public void exibirProduto() {
        System.out.println("Produto TOTVS: " + nomeProduto + " (" + categoria + ")");
    }
}
