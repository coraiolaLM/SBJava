package ControleDeFilmes.Models;

public class Filmes {
    private String titulo;
    private String ano;
    private String URL;
    private String emailUsuarioQueAdicionou;
    private String nomeUsuarioQueAdicionou;

    
    public String getNomeUsuarioQueAdicionou() {
        return nomeUsuarioQueAdicionou;
    }

    public void setNomeUsuarioQueAdicionou(String nomeUsuarioQueAdicionou) {
        this.nomeUsuarioQueAdicionou = nomeUsuarioQueAdicionou;
    } 
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAno() {
        return ano;
    }
    
    public void setAno(String ano) {
        this.ano = ano;
    }
    
    public String getURL() {
        return URL;
    }
    
    public void setURL(String URL) {
        this.URL = URL;
    }
    
    public String getEmailUsuarioQueAdicionou() {
        return emailUsuarioQueAdicionou;
    }
    
    public void setEmailUsuarioQueAdicionou(String emailUsuarioQueAdicionou) {
        this.emailUsuarioQueAdicionou = emailUsuarioQueAdicionou;
    }
    
    @Override
    public String toString() {
        return titulo + ";" + ano + ";" + URL + ";" + emailUsuarioQueAdicionou;
    }
    
    public static Filmes fromString(String linha) {
        String[] partes = linha.split(";");
        if (partes.length != 4) {
            return null;
        }
        Filmes filme = new Filmes();
        filme.setTitulo(partes[0]);
        filme.setAno(partes[1]);
        filme.setURL(partes[2]);
        filme.setEmailUsuarioQueAdicionou(partes[3]);
        return filme;
    }
}