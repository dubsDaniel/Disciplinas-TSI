package Exercicio02_Calculadora;

public class ParametroInvalido extends Exception {
	public ParametroInvalido() {
		super("x e y n�o pode ser negativo.");
	}
}