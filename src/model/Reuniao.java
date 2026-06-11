package model;

import java.util.ArrayList;
import java.util.List;

public class Reuniao {

    private int idReuniao;
    private String data;
    private String titulo;
    private UsuarioSistema responsavel;
    private Cliente cliente;
    private Transcricao transcricao;
    private List<AnaliseIA> analises;

    public Reuniao(int idReuniao, String data, String titulo, UsuarioSistema responsavel, Cliente cliente) {
        this.idReuniao = idReuniao;
        this.data = data;
        this.titulo = titulo;
        this.responsavel = responsavel;
        this.cliente = cliente;
        this.analises = new ArrayList<>();
    }

    public int getIdReuniao() {
        return idReuniao;
    }

    public void setIdReuniao(int idReuniao) {
        this.idReuniao = idReuniao;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public UsuarioSistema getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(UsuarioSistema responsavel) {
        this.responsavel = responsavel;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Transcricao getTranscricao() {
        return transcricao;
    }

    public void setTranscricao(Transcricao transcricao) {
        this.transcricao = transcricao;
    }

    public List<AnaliseIA> getAnalises() {
        return analises;
    }

    public void adicionarAnalise(AnaliseIA analise) {
        analises.add(analise);
    }

    public void cadastrarReuniao() {
        System.out.println("Reuniao '" + titulo + "' cadastrada para o cliente " + cliente.getNome() + ".");
    }

    public void exibirReuniao() {
        System.out.println("----- Reuniao #" + idReuniao + " -----");
        System.out.println("Titulo......: " + titulo);
        System.out.println("Data........: " + data);
        System.out.println("Responsavel.: " + responsavel.getNome());
        System.out.println("Cliente.....: " + cliente.getNome());
        System.out.println("Transcricao.: " + (transcricao != null ? "Registrada" : "Nao registrada"));
        System.out.println("Analises....: " + analises.size());
    }
}
