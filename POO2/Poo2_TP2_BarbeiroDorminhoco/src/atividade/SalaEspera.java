package atividade;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;//implementação de fila recomendada

public class SalaEspera {

    Queue<Cliente> fila = new ConcurrentLinkedQueue<>();//instanciando
    
    public boolean filaCheia(){
        if(fila.size() == 5){//tamanho da fila de espera
            return true;
        } else {
            return false;
        }
    }
    //funcoes da fila
    public void ocuparFila(Cliente cliente){
        fila.add(cliente);
    }
    
    public void desocuparFila(){
        fila.poll();
    }
    
    public boolean filaVazia(){
        if(fila.isEmpty()){
            return true;
        } else {
            return false;
        }
    }
    
    public Cliente verificarCliente(){
        return fila.peek();
    }
}