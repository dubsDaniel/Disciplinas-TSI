package quest�es;
import java.util.Scanner;

public class Questao2 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int contC = 0;
		int contG = 0;
		int contTotal = 0;
		double porcC = 0.0;
		double porcG = 0.0;
		int idade = 0;
		int oldDog = 0;
		int oldCat = 0;
		double peso = 0.0;
		double kgCat = 0.0;
		String sexo = "";
		String tipo = "";
		do {
			System.out.println("Por favor, informe o tipo do animal para exame (C - C�o, G - Gato e X para finalizar): ");
			tipo = scn.next();
			if ( tipo == "C" || tipo == "G") {//se dog ou cat, contabiliza o numero de animais para calculo no final
				if ( tipo == "C") {
					contC = contC + 1;
				} else if ( tipo == "G") {
					contG = contG + 1;
				}
			}
			System.out.println("Por favor, informe a idade do animal (em meses): ");
			idade = scn.nextInt();//nesse ponto j� realizo a verifica��o se � a primeira vez passando ou se a idade do dog foi superior a �ltima maior
			if ( tipo == "C") {
				if ( oldDog == 0) {
					oldDog = idade;
				} else if (idade > oldDog) {
						oldDog = idade;
					}
				}
			System.out.println("Por favor, informe o sexo do animal (M / F): ");
			sexo = scn.next();
			System.out.println("Por favor, informe o peso do animal: ");
			peso = scn.nextDouble();//nesse ponto verifico se � felino, se � femea, se � a primeira vez no c�digo, ou se � inferior a �ltima passagem, atibuindo a idade para proxima verifica��o e atribuindo o peso da gata mais nova
			if ( tipo == "G") {
				if ( sexo == "F") {
					if ( oldCat == 0) {
						oldCat = idade;
						kgCat = peso;
					} else if (idade < oldCat) {
							oldCat = idade;
							kgCat = peso;
					}
				}
			}
		} while (tipo != "X");
		contTotal = (contC + contG);
		porcC = (contC * 100) / (contTotal);
		porcG = (contG * 100) / (contTotal);
		System.out.format("�/S�o um total de %d animais, sendo um total de %.1f de Cachorros e %.1f de Gatos", contTotal, porcC, porcG);
		System.out.format("O Cachorro mais velho atendido tinha: %d", oldDog);
		System.out.format("O peso da Gata mais jovem atendida foi: %.1f", kgCat);
	}

}
//n�o consegui fazer ele parar se o usu�rio informar X, mas creio que o restante segue a l�gica