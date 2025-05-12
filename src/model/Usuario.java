package model;

public class Usuario {
    // Atributos do usuário
    private String nome;
    private String email;
    private String cidade;

    // Construtor para inicializar os atributos
    public Usuario(String nome, String email, String cidade) {
        this.nome = nome;
        this.email = email;
        this.cidade = cidade;
    }

    // Métodos getters para acessar os atributos
    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
    }

    public String getCidade() {
        return cidade;
    }
}