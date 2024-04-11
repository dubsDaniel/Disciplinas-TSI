package Ex01_Orcamento;

public class Produto implements ItemOrcamento {
	String descricao;
	double quantidade;
	double valorUnitario;

	public Produto(String descricao, double quantidade, double valorUnitario) {
		super();
		this.quantidade = quantidade;
		this.valorUnitario = valorUnitario;
		this.descricao = descricao;
	}
	@Override
	public String getDescricao() {
		return descricao;

	}

	@Override
	public double getValor() {
		return quantidade * valorUnitario;

	}

}
