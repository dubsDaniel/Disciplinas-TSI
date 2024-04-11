package barbeiroDorminhoco;

import java.util.Date;

class Cliente implements Runnable {

    int clienteId;
    Date tempo;
 
    Barbearia barbearia;
 
    public Cliente(Barbearia barbearia) {
    
        this.barbearia = barbearia;
    }
 
    public int getClienteId() {										//getter and setter methods
        return clienteId;
    }
 
    public Date getTempo() {
        return tempo;
    }
 
    public void setclienteId(int clienteId) {
        this.clienteId = clienteId;
    }
 
    public void setTempo(Date tempo) {
        this.tempo = tempo;
    }
 
    public void run() {													//cliente thread goes to the barbearia for the haircut
    
        CortarCabelo();
    }
    private synchronized void CortarCabelo() {							//cliente is added to the list
    
        barbearia.add(this);
    }
}