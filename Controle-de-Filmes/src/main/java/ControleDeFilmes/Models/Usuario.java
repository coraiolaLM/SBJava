  package ControleDeFilmes.Models;

    public class Usuario {
        private String login;
        private String senha;
        private String nome;
        private String email;


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

        @Override
        public String toString() {
            return login + ";" + senha + ";" + nome + ";" + email;
        }

        public static Usuario fromString(String linha) {
            String[] partes = linha.split(";");
            if (partes.length != 4) {
                return null;
            }
            Usuario u = new Usuario();
            u.setLogin(partes[0]);
            u.setSenha(partes[1]);
            u.setNome(partes[2]);
            u.setEmail(partes[3]);
            return u;
        }
    }
