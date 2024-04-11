package controle;

import dados.Aluno;
import dados.DadosSingleton;
import dados.Prova;
import dados.Questoes;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Random;
//import javax.annotation.PreDestroy; tentativa mal sucedida
import javax.enterprise.context.ApplicationScoped;//Diferente do exemplo passado em sala, funciona como única aplicação e não como sessão que exclui ao fechar o navegador
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
//Bean de aplicação
@Named(value = "provasBean")
@ApplicationScoped //Alterado para Application Scoped, pois, não consegui manter a classe singleton quando o usuário fecha a sessão, mas agora não consigo zerar a contagem de questões que já foram
public class ProvaBean implements Serializable {
    private static DadosSingleton salvarProvas = DadosSingleton.getInstancia(); //Minhas pesquisas sobre classes Singleton me levaram a crer que essa instancia
                                                                                // manteria essas listas com a anotação PreDestroy do bean
    private static ArrayList<Prova> listaProvas = salvarProvas.getListaProvas(); //Criando ArrayList para manter o controle das provas
    private static ArrayList<Aluno> listaAlunos = salvarProvas.getListaAlunos(); //Array de alunos
    private static Aluno atual; //Declara aluno atual
    private Questoes questao; //Declarador das questões
    private Prova prova; //Declara prova atual
    private Random rnd = new Random(System.currentTimeMillis()); //Passado na prova
    private int conta = 1; //contabilizador para auxiliar no gerador de questões
    
    public ProvaBean() {
        listaProvas = new ArrayList<>(); //novo ArrayList para controle da prova)
        listaAlunos = new ArrayList<>(); //same
        atual = new Aluno(); //gera novo aluno
        prova = new Prova(); //gera nova prova
        questao = new Questoes(1, rnd.nextInt(5) + 1, rnd.nextInt(5) + 1); //gera questão inicial
    }
    
    //getters e setters
    public Questoes getQuestao() {
        return questao;
    }

    public void setQuestao(Questoes questao) {
        this.questao = questao;
    }

    public Prova getProva() {
        return prova;
    }

    public void setProva(Prova prova) {
        this.prova = prova;
    }
    
     public Aluno getAtual() {
        return atual;
    }

    public void setAtual(Aluno atual) {
        this.atual = atual;
    }
    
    //getter do Array de alunos e provas
    public ArrayList<Prova> getListaProvas() {
        return listaProvas;
    }
    
    public ArrayList<Aluno> getListaAlunos() { 
        return listaAlunos;
    }
    
    public String incluir() { //Método inclui aluno se não estiver cadastrado
        // Verifica se a matrícula já existe na lista de alunos
        if (atual.getMatricula() <= 0) {//só Matricula válida
            FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Informe uma matricula válida", "Matricula não pode ser 0 ou menor"));
                return null; //Recarrega página sem redirecionar dados
        }
        for (Aluno aluno : listaAlunos) {//percorre todo o Array de usuários atualmente salvos
            if (aluno.getMatricula() == atual.getMatricula()) { //Verifica se a matricula atual já existe na lista de alunos
                FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Matricula já fez a prova", "Aluno já fez a prova"));//Erro pq só pode uma vez
                return null; //Recarrega página sem redirecionar dados
            }
        }
        return "questoes.xhtml"; // Boa prova
    }
    
    public void verificaResultado(){ //verifica
        if (questao.getResultado() <= 0 || questao.getResultado() > 100) { //como o professor pediu para que seja um número de 1 a 100, não deve passar de pergunta
            repeteQuestao(); //função com nome auto explicativo
            
        }
        int var1 = questao.getVar1(); //declarando as varíaveis da sessao pra comparar
        int var2 = questao.getVar2(); //same
        int acertou =  (var1 * var2); //calc
        Double notaAtual = prova.getNota(); //Pega resultado do usuário
        if (questao.getResultado() == acertou) {//Compara respostas
            notaAtual += 0.5;//Soma nota
            prova.setNota(notaAtual); //Parabéns
        } 
        if (questao.getResultado() > 0 && questao.getResultado() < 100) {//se o resultado for um número de 1 a 99
            geraNovaQuestao(); //próxima questão
        }
    }  
    
    public String repeteQuestao(){ //volta erro
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_ERROR,
                        "Um erro aconteceu: Talvez você tenha digitado errado, os únicos resultados possíveis vão até 100. Tente novamente", "Entrada inválida"));
        return null;
    }
    
    public String geraNovaQuestao(){//gerador de questão
        if (conta < 10) {//Váriavel conta (usada para alterar a "id da pergunta", começa em 1 por que a identificação precisa começar em "pergunta 1"
            for (int i = conta; i <= 10; i++){//i recebe conta pra ficar mais fácil a contagem e interpretação do significado desse loop
                conta += 1;//soma mais um para alterar id da questão
                questao = new Questoes(conta, rnd.nextInt(5) + 1, rnd.nextInt(5) + 1);//mais uma questão fácil
                break;//quebra loop
            }
        } else if (conta < 20) {//para quando chega na questão 20
                for (int i = conta; i < 20; i++){//para quando id chega em 20
                conta += 1;//soma mais um para alterar id da questão até o 20
                questao = new Questoes(conta, (rnd.nextInt(5) + 5), (rnd.nextInt(5) + 5));//mais uma questão difícil
                break;//quebra loop
            }
        } else conta += 1;  //contador fica em 21  
        return null; //recarrega pg pra nova questão
    }
    
    public String finalizarProva() {//classe finaliza prova
        int var1 = questao.getVar1(); //recebe variáveis da última questão
        int var2 = questao.getVar2(); //same
        int acertou =  (var1 * var2); //para usar na verificação da última questão
        Double notaAtual = prova.getNota(); //same
        Date agora = new Date(); //Data de finalização
        if (questao.getResultado() == acertou) {//verificação de acerto da última questão
            notaAtual += 0.5; //soma nota
            prova.setNota(notaAtual); //atualiza nota
        }
        prova.setNomeAluno(atual.getNome()); //preenche os dados com a informação da classe aluno
        prova.setNumeorMatricul(atual.getMatricula()); //same
        prova.setDataProva(agora); //preenche a data de finalização da prova, nos exemplo da tela ela não apareceu, não acho necessário mostrar
        listaAlunos.add(atual);//adiciona os dados do aluno na lista
        listaProvas.add(prova);//adiciona os dados da prova na lista
        Collections.sort(listaProvas); //para que a lista seja ordenada em ordem decrescente de notas
        atual = new Aluno(); //zera aluno
        prova = new Prova(); //zera prova
        conta = 1; //reseta contagem
        questao = new Questoes(1, rnd.nextInt(5) + 1, rnd.nextInt(5) + 1); //gera questão 1 aleatória
        return "resultado.xhtml";//redireciona pra resultados
    }  
    
    public String novaProva() { //volta pra tela inicial
        return "index.xhtml";
    }
    
    
  //não está funcionando, deveria resetar para pergunta 1 e retirar os dados do usuário atual antes de salva-los,
  //na primeira tentativa retornou um erro que não consegui tirar nem quando exclui do código, mas ao fechar e reabrir o netbean
  //voltou a funcionar, após uma leve alteração que seguindo a lógica deveria funcionar, mas a sessão se mantém,
  //não apresentou o erro novamente
    /*
    @PreDestroy                       
    public void zerando(){   
        conta = 1;           
        atual = new Aluno(); 
        prova = new Prova();
        questao = new Questoes(1, rnd.nextInt(5) + 1, rnd.nextInt(5) + 1);
    }*/
}
