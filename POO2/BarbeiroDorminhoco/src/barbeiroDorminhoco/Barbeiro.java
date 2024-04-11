package barbeiroDorminhoco;

class Barbeiro implements Runnable {										// initializing the barber

    Barbearia barbearia;
    int idBarbeiro;
 
    public Barbeiro(Barbearia barbearia, int idBarbeiro) {
    
        this.barbearia = barbearia;
        this.idBarbeiro = idBarbeiro;
    }
    
    public void run() {
    
        while(true) {
        
            barbearia.CorteCabelo(idBarbeiro);
        }
    }
}