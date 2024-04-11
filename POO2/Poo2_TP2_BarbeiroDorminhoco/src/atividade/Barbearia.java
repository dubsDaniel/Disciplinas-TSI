package atividade;

import atividade.Barbeiro;
import atividade.Recepcionista;
import atividade.SalaEspera;

public class Barbearia {
	public static boolean aberta = false;
    public static void main(String[] args) {
        
        abrir(); //método para abrir a barbearia
        
        try {
            Thread.sleep(60000);//tempo de 1 minuto para fechar
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        
        fechar();//método fechando a barbearia
        
    }
    
    public static void abrir(){

        aberta = true;
        System.out.println("A barbearia está aberta!");
        SalaEspera sala = new SalaEspera();
        Barbeiro barbeiro = new Barbeiro(sala);
        Recepcionista recepcionista = new Recepcionista(sala, aberta);
        
        recepcionista.run();
        barbeiro.run();
    }
    
    public static void fechar(){
    	aberta = false;
        Recepcionista.parar();
        Barbeiro.parar();
        System.out.println("A barbearia fechou");
    }
}