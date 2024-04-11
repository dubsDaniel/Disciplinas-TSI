package Ex01_Orcamento;

//Gabriel Junior Picagevicz RA: 2096943

public class ProgramaPrincipal {

	public static void main(String[] args) {

		ItemOrcamento itens[] = new ItemOrcamento[3];
		itens[0] = new Produto("Gasolina Aditivada", 5.80, 500);
		itens[1] = new Servico("Jurandir (paisagista)", 30, 27.50);
		itens[2] = new Imposto("INSS sobre pagamentos", 235000.00, 6.5);
		listarOrcamento(itens);
	}

	private static void listarOrcamento(ItemOrcamento[] itens) {
		double somar = 0;

		for (int i = 0; i < itens.length; i++) {
			System.out.println("Valor: R$ " + itens[i].getValor() + "  Descrição: " + itens[i].getDescricao());

			somar += itens[i].getValor();
		}
		System.out.print("Valor final do projeto: R$ " + somar);
	}

}
