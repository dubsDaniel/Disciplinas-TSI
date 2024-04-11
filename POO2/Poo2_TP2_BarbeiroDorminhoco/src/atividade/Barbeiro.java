package atividade;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Barbeiro implements Runnable {

    Cliente cliente;
    static SalaEspera sala;
    final Lock lock = new ReentrantLock();
    final Condition filaVazia = lock.newCondition();//exemplo sala
    final Condition filaNaoVazia = lock.newCondition();//exemplo sala

    public Barbeiro(SalaEspera sala) {
        this.sala = sala;
    }

    @Override
    public void run() {
    	//ele está criando apenas 1 Thread, quando é colocado dentro do while(true), só está Thread funciona 
        lock.lock(); //lock no barbeiro que estará ocupado cortando cabelo
        try {
                if (sala.filaVazia() == true) { //caso não tenha clientes barbeiro dorme
                    filaVazia.await();
                    System.out.println("Não tinha clientes, então o Barbeiro dormiu!");
                } else { //caso contrario cliente sai da sala de espera e vai cortar o cabelo
                    filaNaoVazia.signal();
                    sala.desocuparFila();
                    System.out.println("Cliente acordou o Barbeiro que começou o trabalho!");
                    int tempo = ThreadLocalRandom.current().nextInt(1000, 10000);//demora de 1 a 10 segundos
                    Thread.sleep(tempo);
                    System.out.println("O Barbeiro terminou o corte!");//finaliza o corte
                    //cliente.signal();
                }
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    public static void parar() {
        Thread.currentThread().interrupt();
    }
}
