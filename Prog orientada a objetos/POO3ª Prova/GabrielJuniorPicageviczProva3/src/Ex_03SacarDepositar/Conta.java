package Ex_03SacarDepositar;

public class Conta {
	String nomeCliente;
	int numero;
	double saldo;
	double limite;

	public Conta(String nomeCliente, int numero, double saldo, double limite) {
		super();
		this.nomeCliente = nomeCliente;
		this.numero = numero;
		this.saldo = saldo;
		this.limite = limite;
	}

	public void depositar(double valor) throws OperacaoIncorreta {
		if (valor < 0) {
			throw new OperacaoIncorreta();
		} else {
			saldo += valor;
		}
	}

	public void sacar(double valor) throws OperacaoIncorreta {
		if (valor < 0 || valor > (saldo + limite)) {
			throw new OperacaoIncorreta();
		} else {
			saldo -= valor;
		}

	}
}
