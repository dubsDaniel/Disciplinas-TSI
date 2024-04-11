package dados;

public class Aluno { //Classe Aluno
    private int matricula;
    private String nome;

    public Aluno(int matricula, String nome) { //Novo Contrutor com dados
        this.matricula = matricula;
        this.nome = nome;
    }

    public Aluno() { //Construtor vazio
    }
    
    //getters e setters
    public int getMatricula() { 
        return matricula;
    }

    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
}
