package ControleDeFilmes.Controllers;

import ControleDeFilmes.Models.Usuario;
import ControleDeFilmes.Service.ServiceUsuarios;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ControllerUsuarios {

    private final ServiceUsuarios usuarioService;

    @Autowired
    public ControllerUsuarios(ServiceUsuarios usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/login")
    public String mostrar_TelaLogin() {
        return "login";
    }

    @GetMapping("/cadastro")
    public String mostrarTelaCadastro() {
        return "cadastro";
    }

    @GetMapping("/home")
    public String mostrarHome(HttpSession session, Model model) {
        Usuario usuarioLogado = (Usuario) session.getAttribute("usuarioLogado");
        if (usuarioLogado == null) {
            return "redirect:/login";
        }
        model.addAttribute("usuarioLogado", usuarioLogado);
        return "home";
    }

    @PostMapping("/cadastrar")
    public String cadastrarUsuario(@ModelAttribute Usuario usr, Model modelo) {
        return usuarioService.cadastrarUsuario(usr, modelo);
    }

    @PostMapping("/validar")
    public String validarCredenciais(@ModelAttribute Usuario usr, Model modelo, HttpSession sessao) {
        return usuarioService.validarCredenciais(usr, modelo, sessao);
    }

    @GetMapping("/logout")
    public String logout(HttpSession sessao) {
        return usuarioService.logout(sessao);
    }
}