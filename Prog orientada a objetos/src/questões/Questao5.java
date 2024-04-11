package questões;
import java.util.Scanner;
import java.util.Arrays;

public class Questao5 {

	public static void main(String[] args) {
		int Nlin, Mcol;
		Scanner scn = new Scanner(System.in);
		System.out.println("Por favor, informe quantas linhas a matriz terá: ");
		Nlin = scn.nextInt();
		System.out.println("Por favor, informe quantas colunas a matriz terá: ");
    	Mcol = scn.nextInt();
    	int matriz[][] = new int[Nlin][Mcol];
    	int vt1[] = new int[Nlin];
    	int vt2[] = new int[Mcol];
    	for (int i = 0; i < Nlin; i++) { //Nota: para inserir dados na matriz, precisa de 2x for
    		for (int j = 0; j < Mcol; j++) {
    			System.out.format("Matriz\n[%d][%d] = ", (i+1), (j+1));
    			matriz[i][j] = scn.nextInt();
    		}
    	}
    	for (int i = 0; i < Nlin; i++) {
    		System.out.print(Arrays.toString(matriz[i]) + "\t\t\t\n");
    	}
	}
}
//Obs: Não consegui gerar os vetores que tinham o maior elemento e mostra-los junto a matriz