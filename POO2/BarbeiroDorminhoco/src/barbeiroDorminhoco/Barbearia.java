package barbeiroDorminhoco;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class Barbearia {

	private final AtomicInteger totalCabelosCortados = new AtomicInteger(0);
	private final AtomicInteger clientesPerdidos = new AtomicInteger(0);
	int nrCadeira, nrBarbeiros, barbeirosDisponiveis;
    List<Cliente> listCliente;
    
    Random r = new Random();	 
    
    public Barbearia(int nrBarbeiros, int nrCadeiras){
    
        this.nrCadeira = nrCadeiras;														//number of chairs in the waiting room
        listCliente = new LinkedList<Cliente>();						//list to store the arriving clientes
        this.nrBarbeiros = nrBarbeiros;									//initializing the the total number of barbers
        barbeirosDisponiveis = nrBarbeiros;
    }
 
    public AtomicInteger getTotalCabelosCortados() {
    	
    	totalCabelosCortados.get();
    	return totalCabelosCortados;
    }
    
    public AtomicInteger getClientePerdidos() {
    	
    	clientesPerdidos.get();
    	return clientesPerdidos;
    }
    
    public void CorteCabelo(int idBarbeiro)
    {
        Cliente cliente;
        synchronized (listCliente) {									//listCliente is a shared resource so it has been synchronized to avoid any
        															 	//unexpected errors in the list when multiple threads access it
            while(listCliente.size()==0) {
            
                System.out.println("\nBarbeiro "+idBarbeiro+" está esperando por um cliente e dormiu na cadeira");
                
                try {
                
                    listCliente.wait();								//barber sleeps if there are no clientes in the shop
                }
                catch(InterruptedException e) {
                
                    e.printStackTrace();
                }
            }
            
            cliente = (Cliente)((LinkedList<?>)listCliente).poll();	//takes the first cliente from the head of the list for haircut
            
            System.out.println("Cliente "+cliente.getClienteId()+
            		" encontrou o barbeiro: "+idBarbeiro+" dormindo e o acordou");
        }
        
        int delay=0;
                
        try {
        	
        	barbeirosDisponiveis--; 										//decreases the count of the disponivel barbers as one of them starts 
        																//cutting hair of the cliente and the cliente sleeps
            System.out.println("Barbeiro: "+idBarbeiro+" está cortando o cabelo do cliente: "+
            		cliente.getClienteId()+ " então, cliente na sala de espera dorme");
        	
            double random = r.nextGaussian() * 2000 + 4000;				//time taken to cut the cliente's hair has a mean of 4000 milliseconds and
        	delay = Math.abs((int) Math.round(random));				//and standard deviation of 2000 milliseconds
        	Thread.sleep(delay);
        	
        	System.out.println("\nCompletou o corte do cabelo do cliente: "+
        			cliente.getClienteId()+" pelo barbeiro: " + 
        			idBarbeiro +" em "+delay+ " milliseconds.");
        
        	totalCabelosCortados.incrementAndGet();
            															//exits through the door
            if(listCliente.size()>0) {									
            	System.out.println("Barbeiro "+idBarbeiro+					//barber finds a sleeping cliente in the waiting room, wakes him up and
            			" acordou o cliente na sala de espera");			//and then goes to his chair and sleeps until a cliente arrives		
            }
            
            barbeirosDisponiveis++;											//barber is disponivel for haircut for the next cliente
        }
        catch(InterruptedException e) {
        
            e.printStackTrace();
        }
        
    }
 
    public void add(Cliente cliente) {
    
        System.out.println("\nCliente "+cliente.getClienteId()+
        		" entrou na barbearia em "
        		+cliente.getTempo());
 
        synchronized (listCliente) {
        
            if(listCliente.size() == nrCadeira) {							//No chairs are disponivel for the cliente so the cliente leaves the shop
            
                System.out.println("\nNão há cadeira disponível para o cliente: "+cliente.getClienteId()+
                		", então o cliente sai da barbearia");
                
              clientesPerdidos.incrementAndGet();
                
                return;
            }
            else if (barbeirosDisponiveis > 0) {							//If barber is disponivel then the cliente wakes up the barber and sits in
            															//the chair
            	((LinkedList<Cliente>)listCliente).offer(cliente);
				listCliente.notify();
			}
            else {														//If barbers are busy and there are chairs in the waiting room then the cliente
            															//sits on the chair in the waiting room
            	((LinkedList<Cliente>)listCliente).offer(cliente);
                
            	System.out.println("Todos os barbeiros estão ocupados, então o cliente: "+
            			cliente.getClienteId()+
                		" senta em uma cadeira de espera");
                 
                if(listCliente.size()==1)
                    listCliente.notify();
            }
        }
    }
}