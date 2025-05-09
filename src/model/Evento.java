package model;

import java.time.LocalDateTime;

public class Evento {
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;

    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    // Getters
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }


    public String toDataString() {
        return nome + ";" + endereco + ";" + categoria + ";" +
                horario.toString() + ";" + descricao;
    }

    public static Evento fromDataString(String data) {
        String[] partes = data.split(";");
        return new Evento(
                partes[0],
                partes[1],
                partes[2],
                LocalDateTime.parse(partes[3]),
                partes[4]
        );
    }

}


