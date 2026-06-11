package model;

public class UsuarioSistema extends Pessoa {

    private int idUsuario;
    private String perfil;

    public UsuarioSistema(int idUsuario, String nome, String perfil) {
        super(idUsuario, nome);
        this.idUsuario = idUsuario;
        this.perfil = perfil;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    @Override
    public void exibirDados() {
        System.out.println("----- Usuario do Sistema -----");
        System.out.println("ID......: " + idUsuario);
        System.out.println("Nome....: " + getNome());
        System.out.println("Perfil..: " + perfil);
    }
}
