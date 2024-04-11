package dados;

public class Questoes { //Classe questões contem id para cada pergunta, as variaveis para multiplicar e o resultado
private int id, var1, var2, resultado;
    
    public Questoes(int id, int var1, int var2) { //Contrutor com dados fornecidos pelo Bean
        this.id = id;
        this.var1 = var1;
        this.var2 = var2;
    }

    public Questoes(int resultado) { //Construtor pra pegar resposta do usuário
        this.resultado = resultado;
    }
    
    public Questoes(){ //Construtor vazio
    }

    //getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getVar1() {
        return var1;
    }

    public void setVar1(int var1) {
        this.var1 = var1;
    }

    public int getVar2() {
        return var2;
    }

    public void setVar2(int var2) {
        this.var2 = var2;
    }

    public int getResultado() {
        return resultado;
    }

    public void setResultado(int resultado) {
        this.resultado = resultado;
    }
    
}
