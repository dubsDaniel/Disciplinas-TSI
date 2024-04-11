package questões;
import java.util.Scanner;
import java.util.Arrays;

public class Questao4 {

	public static void main(String[] args) {
		int ar1[] = new int[20]; //vetor com 20 endereços
		int ar2[] = new int[20];
		int comecoAr2 = 0;
		int finalAr2 = ar2.length -1;
		Scanner scn = new Scanner(System.in);
		System.out.print("Informe 20 números, irei colocar os pares em ordem crescente e os impares em decrescente: ");
		for (int i = 0; i < ar1.length ; i++) {
			ar1[i] = scn.nextInt();
		}
		Arrays.sort(ar1); //metodo de ordenação
		for ( int i = 0; i <= 19; i++) {
			if (ar1[i] % 2 == 0) {
				ar2[comecoAr2] = ar1[i];
				comecoAr2 += 1;
		} else {
			ar2[finalAr2] = ar1[i];
			finalAr2 -= 1;
			}
		}
		System.out.println(Arrays.toString(ar2));
	}
}