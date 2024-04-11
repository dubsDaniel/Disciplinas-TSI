package questões;
import java.util.Scanner;

public class Questao1 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		System.out.println("Informe a distância percorrida (Km): ");
		int dist = scn.nextInt();//insere var da distancia
		if (dist <= 0) {
			while (dist <= 0) {
				System.out.println("O sistema foi feito para calcular apenas distancias em Km, por favor informe em Km");
				dist = scn.nextInt();//insere var da distancia novamente, caso o usuário informe um valor negativo ou em metros
			}
		}
		System.out.println("Tempo necessário (em horas): ");
		int horas = scn.nextInt();//insere var de horas
		if (horas <= 0) {
			while (horas <= 0) {
				System.out.println("O sistema foi feito para calcular apenas em horas, informe novamente: ");
				horas = scn.nextInt();//insere var de horas novamente, caso o usuário informe um valor negativo ou em minutos
			}
		}
		System.out.println("Combustível (litros): ");
		double comblt = scn.nextDouble();
		if (comblt <= 0.0) {//insere var da quantidade de combustivel usado
			while (comblt <= 0.0) {
				System.out.println("Não gastou nenhum litro de combustivel? Por favor informe novamente: ");
				comblt = scn.nextDouble();//insere var da quantidade de combustivel usado novamente, caso o usuario informe um valor negativo ou 0
			}
		}
		System.out.println("Preço do litro do combustivel: ");
		double vlcomb = scn.nextDouble();;//insere var do valor do combustivel
		if (vlcomb <= 0.0) {
			while (vlcomb <= 0.0) {
				System.out.println("O combustivel ta de graça?? Vou imediatamente a este posto! Por favor informe o preço corretamente");
				vlcomb = scn.nextDouble();//insere var do valor do combustivel usado novamente, caso o usuario informe um valor negativo ou 0
			}
		}
		double velmed = (dist / horas);
		double custmed = (dist / comblt);
		double vlkm = ((comblt * vlcomb) / dist);
	System.out.format("A Velocidade média foi: %.1f\n", velmed);
	System.out.format("O Consumo médio foi: %.2f\n", custmed);
	System.out.format("Custo do kilômetro rodado: %.2f\n", vlkm);
	
	}

}
