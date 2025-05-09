package controller;

import model.Evento;

import java.io.*;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.util.*;

public class GerenciadorEventos {
    private List<Evento> eventos;
    private static final String ARQUIVO = "events.data";

    public GerenciadorEventos() {
        eventos = new ArrayList<>();
        carregarEventos();
    }

    private void carregarEventos() {
        Path caminho = Paths.get(ARQUIVO);

        if (!Files.exists(caminho)) return;

        try (BufferedReader reader = Files.newBufferedReader(caminho)) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                if (linha.trim().isEmpty()) continue;
                Evento evento = Evento.fromDataString(linha);
                if (evento != null) {
                    eventos.add(evento);
                }
            }
        } catch (IOException e) {
            System.out.println("Erro ao ler o arquivo: " + e.getMessage());
        }
    }

    private void salvarEventos() {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(ARQUIVO))) {
            for (Evento e : eventos) {
                writer.write(e.toDataString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos: " + e.getMessage());
        }
    }

    public void adicionarEvento(Evento evento) {
        eventos.add(evento);
        salvarEventos();
    }

    public List<Evento> listarEventos() {
        return eventos;
    }

    public boolean excluirEvento(int indice) {
        if (indice >= 0 && indice < eventos.size()) {
            eventos.remove(indice);
            salvarEventos();
            return true;
        }
        return false;
    }

    public boolean atualizarEvento(int indice, Evento novoEvento) {
        if (indice >= 0 && indice < eventos.size()) {
            eventos.set(indice, novoEvento);
            salvarEventos();
            return true;
        }
        return false;
    }

    public Evento eventoMaisProximo() {
        return eventos.stream()
                .filter(e -> e.getHorario().isAfter(LocalDateTime.now()))
                .min(Comparator.comparing(Evento::getHorario))
                .orElse(null);
    }
}
