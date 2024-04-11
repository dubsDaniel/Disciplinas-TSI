package lavacao;

import java.util.Random;

public class Enxugador implements Runnable {
	private Escorredor escorredor;
	private Random random = new Random();
	private boolean stop = true;
	
	public Enxugador(Escorredor escorredor) {
		this.escorredor = escorredor;
	}
	
	public void done(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(stop == false) {
			try {
				long time = random.nextInt(3000, 10000);
				Thread.sleep(time);
				escorredor.pegarPrato();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
