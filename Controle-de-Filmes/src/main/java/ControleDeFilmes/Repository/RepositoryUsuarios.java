package ControleDeFilmes.Repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import ControleDeFilmes.Models.Usuario;

public class RepositoryUsuarios {

    private static final String ARQUIVO = "usuarios.txt";

    public static void salvarUsuario(Usuario usuario) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO, true))) {
            writer.write(usuario.toString());
            writer.newLine();
        } catch (IOException erro) {
            System.out.println("Erro ao salvar usuário: " + erro.getMessage());
        }
    }

    public static List<Usuario> carregarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(ARQUIVO))) {
            String linha;
            while ((linha = reader.readLine()) != null) {
                Usuario usuario = Usuario.fromString(linha);
                if (usuario != null) {
                    usuarios.add(usuario);
                }
            }
        } catch (IOException erro) {
            System.out.println("Erro ao carregar usuários: " + erro.getMessage());
        }
        return usuarios;
    }

    public static Usuario autenticar(String email, String senha) {
        List<Usuario> usuarios = carregarUsuarios();
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email) && u.getSenha().equals(senha)) {
                return u;
            }
        }
        return null;
    }
    
    public static boolean emailExistente(String email) {
        List<Usuario> usuarios = carregarUsuarios();
        return usuarios.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email));
    }
    
    public static Usuario buscarUsuarioPorEmail(String email) {
        List<Usuario> usuarios = carregarUsuarios();
        for (Usuario u : usuarios) {
            if (u.getEmail().equalsIgnoreCase(email)) {
                return u;
            }
        }
        return null;
    }
}