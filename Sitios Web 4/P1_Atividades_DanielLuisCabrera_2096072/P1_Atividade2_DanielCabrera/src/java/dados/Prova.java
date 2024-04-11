package dados;

import java.util.Date;

public class Prova implements Comparable<Prova> { //Classe implementa a interface Comparable para usar a nota como comparação
    private int numeorMatricul; //Exemplo do professor
    private String nomeAluno;
    private Date dataProva;
    private double nota;

    public Prova(int numeorMatricul, String nomeAluno, Date dataProva) { //Contrutor com dados fornecidos pelo Bean de alunos
        this.numeorMatricul = numeorMatricul;
        this.nomeAluno = nomeAluno;
        this.dataProva = dataProva;
    }

    public Prova() { //Construtor vazio
    }
    
    //getters e setters
    public int getNumeorMatricul() {
        return numeorMatricul;
    }
    
    public void setNumeorMatricul(int numeorMatricul) {
        this.numeorMatricul = numeorMatricul;
    }

    public String getNomeAluno() {
        return nomeAluno;
    }

    public void setNomeAluno(String nomeAluno) {
        this.nomeAluno = nomeAluno;
    }

    public Date getDataProva() {
        return dataProva;
    }

    public void setDataProva(Date dataProva) {
        this.dataProva = dataProva;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    //Método criado para comparar e listar em ordem decrescente pelo valor da nota
    @Override
    public int compareTo(Prova prova) {
        return Double.compare(prova.getNota(), this.nota);
    }
    
}
