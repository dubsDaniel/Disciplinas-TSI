package Exercicio02_Calculadora;

import javax.swing.JOptionPane;

//Gabriel Junior Picagevicz RA: 2096943

public class ProgramaPrincipal {
	public static void main(String[] args) throws ParametroInvalido {

		double x = 0;
		double y = 0;

		for (int i = 0; i < 2; i++) {

			x = Double.parseDouble(JOptionPane.showInputDialog("Qual o valor de x? "));
			y = Double.parseDouble(JOptionPane.showInputDialog("Qual o valor de Y? "));

			try {
				JOptionPane.showMessageDialog(null, "Resultado: " + Calculadora.produtoPelaSoma(x, y));
			} catch (ParametroInvalido error) {

				JOptionPane.showMessageDialog(null, "O resultado das somas não pode ser 0 (zero)", "Erro",
						JOptionPane.ERROR_MESSAGE);
			}

		}

	}

}
