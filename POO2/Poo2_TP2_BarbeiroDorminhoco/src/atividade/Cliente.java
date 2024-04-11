package atividade;

import atividade.SalaEspera;

public class Cliente implements Runnable {

    SalaEspera sala;
    String nome;
    
    public Cliente(SalaEspera sala, String nome){
        this.sala = sala;
        this.nome = nome;
        new Thread(this).start();
    }
    
    @Override
    public void run(){
        if(sala.filaCheia() != true){//verifica se há vaga na sala de espera
        	sala.ocuparFila(this);//cliente aguardar sua vez
            System.out.println("O cliente " +Thread.currentThread().getName()+", espera por sua vez, na sala de espera");
            //barbeiro.signal();
        } else {
            Thread.currentThread().interrupt();//se não tem cadeiras disponivel ele vai embora
            System.out.println("O cliente " +Thread.currentThread().getName() + " vai indo embora e não volta mais");
        }
    }

    public String getNome() {
        return nome;
    }
}