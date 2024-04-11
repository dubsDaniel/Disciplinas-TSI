package barbeiroDorminhoco;

import static java.util.concurrent.TimeUnit.SECONDS;
import java.util.Date;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class BarbeiroDorminhoco {
	
	public static void main (String a[]) throws InterruptedException {	
		
		int nrBarbeiros=2, clienteId=1, nrClientes=100, nrCadeiras;	//initializing the number of barber and clientes
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Informe o número de barbeiros:");			//input M barbers
    	nrBarbeiros=sc.nextInt();
    	
    	System.out.println("Informe o número da cadeiras de espera:");
    	nrCadeiras=sc.nextInt();
    	
    	System.out.println("Informe o número de clientes:");			//inout the number of clientes for the barbearia
    	nrClientes=sc.nextInt();
    	
		ExecutorService exec = Executors.newFixedThreadPool(4);		//initializing with 12 threads as multiple of cores in the CPU, here 6
    	Barbearia barbearia = new Barbearia(nrBarbeiros, nrCadeiras);				//initializing the barber barbearia with the number of barbers
    	Random r = new Random();  										//a random number to calculate delays for cliente arrivals and haircut
       	    	
        System.out.println("\nBarbearia abre com "
        		+nrBarbeiros+" barbeiro(s)\n");
        
        long inicio  = System.currentTimeMillis();					//start time of program
        
        for(int i=1; i<=nrBarbeiros;i++) {								//generating the specified number of threads for barber
        	
        	Barbeiro barber = new Barbeiro(barbearia, i);	
        	Thread thbarber = new Thread(barber);
            exec.execute(thbarber);
        }
        
        for(int i=0;i<nrClientes;i++) {								//cliente generator; generating cliente threads
        
            Cliente cliente = new Cliente(barbearia);
            cliente.setTempo(new Date());
            Thread thcliente = new Thread(cliente);
            cliente.setclienteId(clienteId++);
            exec.execute(thcliente);
            
            try {
            	
            	double random = r.nextGaussian() * 2000 + 2000;			//'r':object of Random class, nextGaussian() generates a number with mean 2000 and	
            	int delay = Math.abs((int) Math.round(random));		//standard deviation as 2000, thus clientes arrive at mean of 2000 milliseconds
            	Thread.sleep(delay);								//and standard deviation of 2000 milliseconds
            }
            catch(InterruptedException e) {
            
                e.printStackTrace();
            }
            
        }
        
        exec.shutdown();												//shuts down the executor service and frees all the resources
        exec.awaitTermination(12, SECONDS);								//waits for 12 seconds until all the threads finish their execution
 
        long tempoPassado = System.currentTimeMillis() - inicio;		//to calculate the end time of program
        
        System.out.println("\nBarbearia fechada");
        System.out.println("\nTotal de tempo passado(em segundos)"
        		+ " para servir "+nrClientes+" clientes, por "
        		+nrBarbeiros+" barbeiros com "+nrCadeiras+
        		" cadeiras na sala de espera (em segundos): "
        		+TimeUnit.MILLISECONDS
        	    .toSeconds(tempoPassado));
        System.out.println("\nTotal clientes: "+nrClientes+
        		"\nTotal clientes servidos: "+barbearia.getTotalCabelosCortados()
        		+"\nTotal clientes perdidos: "+barbearia.getClientePerdidos());
               
        sc.close();
    }
}