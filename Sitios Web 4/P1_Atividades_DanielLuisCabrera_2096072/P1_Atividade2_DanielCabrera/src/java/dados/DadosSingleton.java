package dados;

import java.util.ArrayList;
/**
  * @author dudbub
  */
public class DadosSingleton {
    private static DadosSingleton instancia;
    private static ArrayList<Prova> listaProvas;
    private static ArrayList<Aluno> listaAlunos;
    
    private DadosSingleton() { //o construtor vazio atribui novas listas
        listaProvas = new ArrayList<>();
        listaAlunos = new ArrayList<>();
    }
    //A principio, se a aplicação já foi criada uma vez, ao gerar uma classe do padrão Singleton, também cria uma instancia, se essa instancia já tiver sido
    //instanciada, ela manterá os dados mesmo que a sessão se encerre
    public static DadosSingleton getInstancia() {
        if (instancia == null) {
            instancia = new DadosSingleton();
        }
        return instancia;
    }
    //getter e setter das listas
    public ArrayList<Prova> getListaProvas() {
        return listaProvas;
    }

    public ArrayList<Aluno> getListaAlunos() {
        return listaAlunos;
    }
}
