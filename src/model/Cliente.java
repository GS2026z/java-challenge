package model;

import java.util.ArrayList;
import java.util.List;

public class Cliente extends Pessoa {

    private int idCliente;
    private String segmento;
    private String porte;
    private List<Reuniao> reunioes;

    public Cliente(int idCliente, String nome, String segmento, String porte) {
        super(idCliente, nome);
        this.idCliente = idCliente;
        this.segmento = segmento;
        this.porte = porte;
        this.reunioes = new ArrayList<>();
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getSegmento() {
        return segmento;
    }

    public void setSegmento(String segmento) {
        this.segmento = segmento;
    }

    public String getPorte() {
        return porte;
    }

    public void setPorte(String porte) {
        this.porte = porte;
    }

    public List<Reuniao> getReunioes() {
        return reunioes;
    }

    public void adicionarReuniao(Reuniao reuniao) {
        reunioes.add(reuniao);
    }

    public void atualizarCadastro() {
        System.out.println("Cadastro do cliente " + getNome() + " atualizado com sucesso.");
    }

    @Override
    public void exibirDados() {
        System.out.println("----- Cliente -----");
        System.out.println("ID........: " + idCliente);
        System.out.println("Nome......: " + getNome());
        System.out.println("Segmento..: " + segmento);
        System.out.println("Porte.....: " + porte);
        System.out.println("Reunioes..: " + reunioes.size());
    }
}
