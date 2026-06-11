package model;

public class Transcricao {

    private int idTranscricao;
    private String textoBruto;
    private String idioma;

    public Transcricao(int idTranscricao, String textoBruto, String idioma) {
        this.idTranscricao = idTranscricao;
        this.textoBruto = textoBruto;
        this.idioma = idioma;
    }

    public int getIdTranscricao() {
        return idTranscricao;
    }

    public void setIdTranscricao(int idTranscricao) {
        this.idTranscricao = idTranscricao;
    }

    public String getTextoBruto() {
        return textoBruto;
    }

    public void setTextoBruto(String textoBruto) {
        this.textoBruto = textoBruto;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void cadastrarTranscricao() {
        System.out.println("Transcricao #" + idTranscricao + " registrada com sucesso (idioma: " + idioma + ").");
    }

    public void exibirTranscricao() {
        System.out.println("----- Transcricao #" + idTranscricao + " -----");
        System.out.println("Idioma: " + idioma);
        System.out.println("Texto.: " + textoBruto);
    }
}
