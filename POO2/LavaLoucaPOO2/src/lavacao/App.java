package lavacao;

public class App {

		private static Escorredor escorredor;
		private static Enxugador enxugador;
		private static Lavador lavador;
		private static Thread threadLavador;
		private static Thread threadEnxugador;
		
		static void work() throws InterruptedException {
			escorredor = new Escorredor(10);
			lavador = new Lavador(escorredor);
			threadLavador = new Thread(lavador);
			threadLavador.run();
			enxugador = new Enxugador(escorredor);
			threadEnxugador = new Thread(enxugador);
			
			lavador.done(false);
			enxugador.done(false);

			threadLavador.start();
			threadEnxugador.start();
			Thread.sleep(10000);

			
			stop();
		}
		
		static void stop() throws InterruptedException {
			lavador.done(true);
			threadLavador.join();
			enxugador.done(true);
			threadEnxugador.join();
			System.out.println("Encerrando a lavação");
		}
		
		
		public static void main(String[] args) throws InterruptedException {
			System.out.println("Começando a lavação");
			work();
			
	}

}
