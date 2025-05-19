package ControleDeFilmes.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import ControleDeFilmes.Models.Filmes;

public class RepositoryFilmes {
    private static final String ARQUIVO = "filmes.txt";

    public static void salvarFilme(Filmes filme) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            writer.write(filme.toString());
            writer.newLine();
        } catch (IOException erro) {
            System.out.println("Erro ao salvar filme: " + erro.getMessage());
        }
    }

    public static List<Filmes> carregarFilmes() {
        List<Filmes> filmes = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Filmes filme = Filmes.fromString(linha);
                if (filme != null) {
                    filmes.add(filme);
                }
            }
        } catch (IOException erro) {
            System.out.println("Erro ao carregar filmes: " + erro.getMessage());
        }
        return filmes;
    }
    
    public static List<Filmes> buscarFilmesPorUsuario(String emailUsuario) {
        List<Filmes> todosFilmes = carregarFilmes();
        List<Filmes> filmesUsuario = new ArrayList<>();
        for (Filmes filme : todosFilmes) {
            if (filme.getEmailUsuarioQueAdicionou().equalsIgnoreCase(emailUsuario)) {
                filmesUsuario.add(filme);
            }
        }
        return filmesUsuario;
    }
}