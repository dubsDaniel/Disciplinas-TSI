package Ex01_Orcamento;

public class Imposto implements ItemOrcamento {
	String fatoGerador;
	double baseCalculo;
	double aliquota;

	public Imposto(String fatoGerador, double baseCalculo, double aliquota) {
		super();
		this.fatoGerador = fatoGerador;
		this.baseCalculo = baseCalculo;
		this.aliquota = aliquota;
	}

	@Override
	public String getDescricao() {
		return fatoGerador;
	}

	@Override
	public double getValor() {

		return (aliquota / 100) * baseCalculo;
	}

}
