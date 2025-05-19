package ControleDeFilmes.Controllers;

import ControleDeFilmes.Models.Filmes;
import ControleDeFilmes.Repository.RepositoryFilmes;
import ControleDeFilmes.Models.Usuario;
import ControleDeFilmes.Service.ServiceFilmes;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class ControllerFilmes {
    private final ServiceFilmes filmesService;

    @Autowired
    public ControllerFilmes(ServiceFilmes filmesService) {
        this.filmesService = filmesService;
    }

    @GetMapping("/cadastrarfilme")
    public String mostrarTelaCadastroFilme(Model model) {
        List<Filmes> todosFilmes = filmesService.listarTodosFilmesComUsuarios();
        model.addAttribute("todosFilmes", todosFilmes);
        return "cadastrarfilme"; 
    }
    
    @PostMapping("/filmes")
    public String adicionarFilme(@ModelAttribute Filmes filme, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("usuarioLogado") == null) {
            return "redirect:/login";
        }

        Usuario usuario = (Usuario) session.getAttribute("usuarioLogado");
        filme.setEmailUsuarioQueAdicionou(usuario.getEmail());
        
        RepositoryFilmes.salvarFilme(filme);
        redirectAttributes.addFlashAttribute("msg", "Filme adicionado com sucesso!");
        
        return "redirect:/cadastrarfilme";
    }

}