package Ex01_Orcamento;

public class Servico implements ItemOrcamento {
	String nomePrestador;
	double horas;
	double valorHoras;

	public Servico(String nomePrestador, double horas, double valorHoras) {
		super();
		this.nomePrestador = nomePrestador;
		this.horas = horas;
		this.valorHoras = valorHoras;
	}

	@Override
	public String getDescricao() {
		return "Serviço de " + nomePrestador;

	}

	@Override
	public double getValor() {
		return valorHoras * horas;

	}

}
