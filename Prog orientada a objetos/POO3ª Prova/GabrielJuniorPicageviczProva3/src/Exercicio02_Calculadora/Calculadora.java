package Exercicio02_Calculadora;

public class Calculadora {
	public static double produtoPelaSoma(double x, double y) throws ParametroInvalido {
		if (x + y == 0) {
			throw new ParametroInvalido();
		} else {
			return (y * x) / (x + y);
		}
	}
}
