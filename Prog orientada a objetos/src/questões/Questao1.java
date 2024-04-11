package quest�es;
import java.util.Scanner;

public class Questao1 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Informe a dist�ncia percorrida (Km): ");
		int dist = scn.nextInt();//insere var da distancia
		if (dist <= 0) {
			while (dist <= 0) {
				System.out.println("O sistema foi feito para calcular apenas distancias em Km, por favor informe em Km");
				dist = scn.nextInt();//insere var da distancia novamente, caso o usu�rio informe um valor negativo ou em metros
			}
		}
		System.out.println("Tempo necess�rio (em horas): ");
		int horas = scn.nextInt();//insere var de horas
		if (horas <= 0) {
			while (horas <= 0) {
				System.out.println("O sistema foi feito para calcular apenas em horas, informe novamente: ");
				horas = scn.nextInt();//insere var de horas novamente, caso o usu�rio informe um valor negativo ou em minutos
			}
		}
		System.out.println("Combust�vel (litros): ");
		double comblt = scn.nextDouble();
		if (comblt <= 0.0) {//insere var da quantidade de combustivel usado
			while (comblt <= 0.0) {
				System.out.println("N�o gastou nenhum litro de combustivel? Por favor informe novamente: ");
				comblt = scn.nextDouble();//insere var da quantidade de combustivel usado novamente, caso o usuario informe um valor negativo ou 0
			}
		}
		System.out.println("Pre�o do litro do combustivel: ");
		double vlcomb = scn.nextDouble();;//insere var do valor do combustivel
		if (vlcomb <= 0.0) {
			while (vlcomb <= 0.0) {
				System.out.println("O combustivel ta de gra�a?? Vou imediatamente a este posto! Por favor informe o pre�o corretamente");
				vlcomb = scn.nextDouble();//insere var do valor do combustivel usado novamente, caso o usuario informe um valor negativo ou 0
			}
		}
		double velmed = (dist / horas);
		double custmed = (dist / comblt);
		double vlkm = ((comblt * vlcomb) / dist);
	System.out.format("A Velocidade m�dia foi: %.1f\n", velmed);
	System.out.format("O Consumo m�dio foi: %.2f\n", custmed);
	System.out.format("Custo do kil�metro rodado: %.2f\n", vlkm);
	
	}

}
