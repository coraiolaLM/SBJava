package ControleDeFilmes.Service;

import ControleDeFilmes.Models.Filmes;
import ControleDeFilmes.Models.Usuario;
import ControleDeFilmes.Repository.RepositoryFilmes;
import ControleDeFilmes.Repository.RepositoryUsuarios;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import java.util.List;

@Service
public class ServiceFilmes {
    public String adicionarFilme(Filmes filme, Model modelo) {
        RepositoryFilmes.salvarFilme(filme);
        modelo.addAttribute("msg", "Filme adicionado com sucesso!");
        return "redirect:/cadastrarfilme";
    }
    
    public List<Filmes> listarTodosFilmesComUsuarios() {
        List<Filmes> filmes = RepositoryFilmes.carregarFilmes();

        for (Filmes filme : filmes) {
            Usuario usuario = RepositoryUsuarios.buscarUsuarioPorEmail(filme.getEmailUsuarioQueAdicionou());
            if (usuario != null) {
                filme.setNomeUsuarioQueAdicionou(usuario.getNome());
            } else {
                // Para debug
                System.out.println("⚠️ Usuário não encontrado para email: " + filme.getEmailUsuarioQueAdicionou());
                filme.setNomeUsuarioQueAdicionou("Desconhecido");
            }
        }

        return filmes;
    }
}