package exercicio02;
/*Daniel Luis Cabrera - 2096072*/
public class Vendedor {
	private String nome;
	private double salario;
	
	public Vendedor(String nome, double salario) {
		super();
		this.nome = nome;
		this.salario = salario;
	}
	
	//calcular o INSS.
	public double calcINSS() {
		return (salario * 0.08);
	}
	
	//calcular o salario liquido.
	public double calcSalarioLiq() {
		return (salario - calcINSS());
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public double getSalario () {
		return salario;
	}
	
	public void setSalario(double salario) {
		this.salario = salario;
	}
}