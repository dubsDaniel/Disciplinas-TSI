package lavacao;

import pratos.Prato;
import pratos.PratosSujosFactory;

public class Lavador implements Runnable {
	private Escorredor escorredor;
	private Prato prato;
	private PratosSujosFactory pratosFactory;
	private boolean stop = true;
	
	public Lavador(Escorredor escorredor) {
		this.escorredor = escorredor;
		pratosFactory = new PratosSujosFactory();
		
	}
	
	public void done(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(stop == false) {
			prato = pratosFactory.criarPratoSujo();
			if(prato.getNivelSujeira() > 0 && prato.getNivelSujeira() <= 0.1) {
				System.out.println("Prato pouco sujo");
				try {
					escorredor.colocarPrato(prato);
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if(prato.getNivelSujeira() > 0.1 && prato.getNivelSujeira() <= 0.4) {
				System.out.println("Prato sujo médio");
				try {
					escorredor.colocarPrato(prato);
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if(prato.getNivelSujeira() > 0.4 && prato.getNivelSujeira() < 10) {
				System.out.println("Prato muito sujo");
				try {
					escorredor.colocarPrato(prato);
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
		}
	}
}
