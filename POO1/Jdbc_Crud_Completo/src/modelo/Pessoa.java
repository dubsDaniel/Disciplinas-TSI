
package modelo;

import java.util.Objects;


public class Pessoa {
    
    private int codigo;
    private String nome;
    private String login;
    private String senha;
    private String nivel;
    
    public Pessoa(){
        
    }

    public Pessoa(String nome, String cep, String bairro, int numero) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.nivel = nivel;
    }
    
    

    public Pessoa(String nome, String cep, String bairro, int numero, int codigo) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.nivel = nivel;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String bairro) {
        this.login = login;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int numero) {
        this.senha = senha;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int codigo) {
        this.nivel = codigo;
    }


    @Override
    public String toString() {
        return "Pessoa{" + "nome=" + nome + ", codigo=" + codigo + ", login=" + login + ", senha=" + senha + ", nivel=" + nivel + '}';
    }
    
    
    
    
}
