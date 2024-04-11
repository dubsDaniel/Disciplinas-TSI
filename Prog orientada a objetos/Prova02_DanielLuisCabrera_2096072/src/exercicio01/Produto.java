package exercicio01;
/*Daniel Luis Cabrera - 2096072*/
public class Produto {
	private String prod;
	private int estoque;
	private double vlUltCompra;
	private double precoVenda;
	
	public Produto(String produto) {
		super();
		this.prod = produto;
	}
	
	public void Comprar(int qtde, double preco) {
		estoque += qtde;
		precoVenda = (preco + (preco * 0.8));
		vlUltCompra = preco;
	}
	
	public double Vender(int qtde) {
		if (qtde > estoque) {
			System.out.print("\n\n-1\nN�o h� itens suficientes em estoque, atualmente o estoque �: ");
			return estoque;
		} else {
			estoque -= qtde;
			return precoVenda * qtde;
		}
	}
	
	//Imprime o produto e suas caracteristicas.
	@Override
	public String toString() {
		return "\nProduto: " + prod + ", Estoque: " + estoque + ", Pre�o da �tima compra: " + vlUltCompra + ", Pre�o da Venda: " + String.format("%.2f", precoVenda);
	}
	
}
