package view;

import controller.GerenciadorEventos;
import model.Evento;
import model.Usuario;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class Sistema {
    private GerenciadorEventos gerenciador;
    private Scanner scanner;
    private Usuario usuario;

    public Sistema() {
        gerenciador = new GerenciadorEventos();
        scanner = new Scanner(System.in);
    }

    public void iniciar() {
        System.out.println("=== Sistema de Eventos ===");
        System.out.print("Digite seu nome: ");
        String nome = scanner.nextLine();
        System.out.print("Digite seu email: ");
        String email = scanner.nextLine();
        System.out.print("Digite sua cidade: ");
        String cidade = scanner.nextLine();

        usuario = new Usuario(nome, email, cidade);

        listarEventos(); // Lista ao iniciar
        mostrarEventoMaisProximo(); // Exibe o mais próximo

        int opcao;
        do {
            System.out.println("\n1. Cadastrar evento");
            System.out.println("2. Listar eventos");
            System.out.println("3. Excluir evento");
            System.out.println("4. Modificar evento");
            System.out.println("0. Sair");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumir \n
            switch (opcao) {
                case 1 -> cadastrarEvento();
                case 2 -> listarEventos();
                case 3 -> excluirEvento();
                case 4 -> modificarEvento();
            }
        } while (opcao != 0);

        System.out.println("Saindo...");
    }

    private void cadastrarEvento() {
        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Categoria (Festa, Show, Esporte): ");
        String categoria = scanner.nextLine();
        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String data = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime horario = LocalDateTime.parse(data, formatter);
            Evento evento = new Evento(nome, endereco, categoria, horario, descricao);
            gerenciador.adicionarEvento(evento);
            System.out.println("Evento cadastrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Formato de data inválido. Use dd/MM/yyyy HH:mm");
        }
    }

    private void listarEventos() {
        System.out.println("\n=== Eventos cadastrados ===");
        List<Evento> eventos = gerenciador.listarEventos();
        if (eventos.isEmpty()) {
            System.out.println("Nenhum evento encontrado.");
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy 'às' HH:mm");
            for (int i = 0; i < eventos.size(); i++) {
                Evento e = eventos.get(i);
                System.out.println("[" + i + "] " + e.getNome() + " | " + e.getCategoria() + " | " +
                        e.getHorario().format(formatter) + " | " + e.getEndereco());
                System.out.println(e.getDescricao());
            }
        }
    }


    private void excluirEvento() {
        listarEventos();
        System.out.print("Digite o número do evento que deseja excluir: ");
        int indice = scanner.nextInt();
        scanner.nextLine();
        if (gerenciador.excluirEvento(indice)) {
            System.out.println("Evento excluído com sucesso.");
        } else {
            System.out.println("Índice inválido.");
        }
    }

    private void modificarEvento() {
        listarEventos();
        System.out.print("Digite o número do evento que deseja modificar: ");
        int indice = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Digite os novos dados:");

        System.out.print("Nome do evento: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Categoria: ");
        String categoria = scanner.nextLine();
        System.out.print("Data e hora (dd/MM/yyyy HH:mm): ");
        String data = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();

        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
            LocalDateTime horario = LocalDateTime.parse(data, formatter);
            Evento novoEvento = new Evento(nome, endereco, categoria, horario, descricao);
            if (gerenciador.atualizarEvento(indice, novoEvento)) {
                System.out.println("Evento modificado com sucesso.");
            } else {
                System.out.println("Índice inválido.");
            }
        } catch (Exception e) {
            System.out.println("Data inválida.");
        }
    }

    private void mostrarEventoMaisProximo() {
        Evento maisProximo = gerenciador.eventoMaisProximo();
        if (maisProximo != null) {
            Duration duracao = Duration.between(LocalDateTime.now(), maisProximo.getHorario());
            long horas = duracao.toHours();
            long minutos = duracao.toMinutesPart();
            System.out.println("\nPróximo evento: " + maisProximo.getNome() +
                    " em " + horas + "h " + minutos + "min");
        } else {
            System.out.println("Nenhum evento futuro encontrado.");
        }
    }
}
