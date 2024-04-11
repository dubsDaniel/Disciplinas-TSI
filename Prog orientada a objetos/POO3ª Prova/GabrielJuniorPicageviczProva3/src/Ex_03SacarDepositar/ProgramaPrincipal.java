package Ex_03SacarDepositar;

import javax.swing.JOptionPane;

//Gabriel Junior Picagevicz RA: 2096943

public class ProgramaPrincipal {

	public static void main(String[] args) throws OperacaoIncorreta {
		Conta c1 = new Conta("Roberto", 10, 5800, 10000);

		try {
			c1.depositar(-1);
		} catch (OperacaoIncorreta error) {
			JOptionPane.showMessageDialog(null, "Você não pode depositar um valor negativo", "Erro",
					JOptionPane.ERROR_MESSAGE);
		}

		try {
			c1.sacar(20000);
		} catch (OperacaoIncorreta error) {

			JOptionPane.showMessageDialog(null, "Você não pode sacar um valor maior que saldo + limite", "ERRO",
					JOptionPane.ERROR_MESSAGE);
		}
	}

}
