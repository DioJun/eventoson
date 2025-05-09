package model;

import java.time.LocalDateTime;

public class Evento {
    // Atributos do evento
    private String nome;
    private String endereco;
    private String categoria;
    private LocalDateTime horario;
    private String descricao;

    // Construtor para inicializar os atributos
    public Evento(String nome, String endereco, String categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    // Métodos getters para acessar os atributos
    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public String getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }

    // Converte o evento para uma string formatada para salvar no arquivo
    public String toDataString() {
        return nome + ";" + endereco + ";" + categoria + ";" +
                horario.toString() + ";" + descricao;
    }

    // Cria um evento a partir de uma string formatada (lida do arquivo)
    public static Evento fromDataString(String data) {
        String[] partes = data.split(";");
        return new Evento(
                partes[0], // Nome
                partes[1], // Endereço
                partes[2], // Categoria
                LocalDateTime.parse(partes[3]), // Data e hora
                partes[4]  // Descrição
        );
    }
}