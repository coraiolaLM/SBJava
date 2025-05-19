package ControleDeFilmes.Service;

import ControleDeFilmes.Models.Usuario;
import ControleDeFilmes.Repository.RepositoryUsuarios;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class ServiceUsuarios {

    public String cadastrarUsuario(Usuario usr, Model modelo) {
        if (RepositoryUsuarios.emailExistente(usr.getEmail())) {
            modelo.addAttribute("erro", "Este e-mail já está cadastrado!");
            return "cadastro";
        }
        
        RepositoryUsuarios.salvarUsuario(usr);
        modelo.addAttribute("msg", "Usuário cadastrado com sucesso!");
        return "login";
    }

    public String validarCredenciais(Usuario usr, Model modelo, HttpSession sessao) {
        Usuario autenticado = RepositoryUsuarios.autenticar(usr.getEmail(), usr.getSenha());

        if (autenticado != null) {
            sessao.setAttribute("usuarioLogado", autenticado);
            return "redirect:/home";
        } else {
            modelo.addAttribute("msg", "Email ou senha incorretos!");
            return "login";
        }
    }

    public String logout(HttpSession sessao) {
        sessao.invalidate();
        return "redirect:/login";
    }
}