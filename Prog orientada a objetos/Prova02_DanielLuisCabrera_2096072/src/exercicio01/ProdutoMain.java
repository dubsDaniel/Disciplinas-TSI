package exercicio01;
/*Daniel Luis Cabrera - 2096072*/
public class ProdutoMain {

	public static void main(String[] args) {
		Produto prod = new Produto("Feijão");
		//Compra produto
		prod.Comprar(100, 5.99);
		
		//valor  da venda.
		double valorVenda = prod.Vender(5);
		System.out.format("%.2f", valorVenda);
		
		//falta de estoque.
		valorVenda = prod.Vender(100);
		System.out.format("\n%.2f", valorVenda);
		
		//Caracteristicas solicitadas
		System.out.println("\n"+prod);
	}

}
