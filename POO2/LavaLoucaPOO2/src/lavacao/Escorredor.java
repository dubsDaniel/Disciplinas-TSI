package lavacao;

import lavacao.CircularBuffer;
import pratos.Prato;

public class Escorredor {
	private CircularBuffer filaEscorredor;
	private int capacidade;
	private int count = 0;
	
	public Escorredor(int qtdeMaxPratos) {
		filaEscorredor = new CircularBuffer(qtdeMaxPratos);
		capacidade = qtdeMaxPratos;
	}
	
	public synchronized void colocarPrato(Prato prato) throws InterruptedException {
		while(count > capacidade) {
			wait();
		}
		
		if(filaEscorredor.cheio() == false) {
			count++;
			filaEscorredor.add(prato);
			System.out.println("Prato adicionado, agora o escorredor esta com " + situacaoEscorredor() + " pratos");
			wait();
		} else {
			System.out.println("Não foi possível colocar o prato, o escorredor está com " + situacaoEscorredor() + " de lotação");		
		}
	}
	
	public synchronized Prato pegarPrato() throws InterruptedException {
		while(count < 0) {
			wait();
		}
		notify();
		count--;
		return filaEscorredor.get();
		
	}
	
	public synchronized int situacaoEscorredor() {
		return filaEscorredor.getEspacoOcupado();
	}
	
	public synchronized boolean escorredorCheio() {
		if(filaEscorredor.cheio() == true) {
			return true;
		}
		
		return false;
	}
}
