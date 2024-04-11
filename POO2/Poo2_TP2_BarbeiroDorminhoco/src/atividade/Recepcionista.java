package atividade;

import java.util.concurrent.ThreadLocalRandom;

public class Recepcionista implements Runnable {
	
    SalaEspera sala;
    int contadorNomeCliente = 1;
    boolean aberta;

    public Recepcionista(SalaEspera sala, Boolean aberta) {
        this.sala = sala;
        this.aberta = aberta;
    }

    @Override
    public void run() {
    	//ele está criando apenas 1 Thread, quando é colocado dentro do while(true), só está Thread funciona 
    	//while (aberta == true) {
	        produzirCliente();
	        int tempo = ThreadLocalRandom.current().nextInt(1000, 10000);
	        try {
	            Thread.sleep(tempo);
	        } catch (InterruptedException ex) {
	            ex.printStackTrace();
	        }
    	//}
    }

    public Cliente produzirCliente() {
        Cliente cliente = new Cliente(this.sala, "#" + contadorNomeCliente);
        contadorNomeCliente++;
        return cliente;
    }

    public static void parar() {
        Thread.currentThread().interrupt();
    }
}
