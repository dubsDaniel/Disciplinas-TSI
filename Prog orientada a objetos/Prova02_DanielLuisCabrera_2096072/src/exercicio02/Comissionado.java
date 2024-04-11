package exercicio02;
/*Daniel Luis Cabrera - 2096072*/
public class Comissionado extends Vendedor {
	private double vendas;
	private double comissao;
	
	public Comissionado(String nome, double salario, double vendas, double comissao) {
		super(nome, salario);
		this.vendas = vendas;
		this.comissao = comissao;
	}
	
	//calcular o INSS + comissao.
	@Override
	public double calcINSS() {
		return (getSalario() + (vendas * comissao / 100)) * 0.08;
	}
	
	//calcular o salario + comissao - INSS.
	@Override
	public double calcSalarioLiq() {
		return (getSalario() + (vendas * comissao / 100)) - calcINSS();
	}
	
	public double getVendas() {
		return vendas;
	}
	
	public void setVendas(double vendas) {
		this.vendas = vendas;
	}
	
	public double getComissao() {
		return comissao;
	}
	
	public void setComissao(double comissao) {
		this.comissao = comissao;
	}
}
