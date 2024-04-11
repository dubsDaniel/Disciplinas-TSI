package questões;
import java.util.Scanner;

public class Questao3 {

	public static void main(String[] args) {
		Scanner scn = new Scanner(System.in);
		int[] ra = new int[30];
		double p1 = 0.0;
		double p2 = 0.0;
		double[] notaFinal = new double[30];
		
		for ( int i = 0; i <= (ra.length - 1); i++) {
			System.out.println("Por favor, informe o RA do aluno: ");
			ra[i] = scn.nextInt();
			System.out.println("Por favor, informe a 1ª nota da prova do aluno (caso não preencha seguirei com 0.0): ");
			p1 = scn.nextDouble();
			System.out.println("Por favor, informe a 2ª nota da prova do aluno (caso não preencha seguirei com 0.0): ");
			p2 = scn.nextDouble();
			notaFinal[i] = ((p1 + 2.0 * p2) / 3);
		}
		System.out.println("Aprovados: ");
		for ( int ap = 0; ap <= (ra.length - 1); ap++) {
			if (notaFinal[ap] >= 7.0) {
				System.out.print(ra[ap]);
				System.out.print(" - ");
				System.out.format("%.1f\n", notaFinal[ap]);
			}
		}
		System.out.println("...");
		System.out.println("Reprovados: ");
		for ( int rp = 0; rp <= (ra.length - 1); rp++) {
			if (notaFinal[rp] < 7.0) {
				System.out.print(ra[rp]);
				System.out.print(" - ");
				System.out.format("%.1f\n", notaFinal[rp]);
			}
		}
	}

}
