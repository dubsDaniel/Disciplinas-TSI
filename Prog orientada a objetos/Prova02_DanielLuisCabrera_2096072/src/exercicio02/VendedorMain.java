package exercicio02;
/*Daniel Luis Cabrera - 2096072*/
public class VendedorMain {

	public static void main(String[] args) {
		Vendedor vet1 [] = new Vendedor [4];
		vet1[0] = new Vendedor("Daniel", 2750.00);
		vet1[1] = new Comissionado("Davi", 2000.00, 3, 50.00);
		vet1[2] = new Comissionado("Bruno", 2800.00, 1, 100.00);
		vet1[3] = new Vendedor("Natan", 2500.00);
		
		for (int i = 0; i < 4; i++) {
			System.out.format("O/A Vendedor(a) %s\nTeve seu INSS, calculado em: %.2f \nE o Salario liquido será: %.2f\n\n",
					vet1[i].getNome(), vet1[i].calcINSS(), vet1[i].calcSalarioLiq());
		}
	}

}
