public class Equipamento {

	private int id;

	private String nome;

	private double custoDia;

	private String codinome;

	public Equipamento(int id, String nome, double custoDia, String codinome) {
		this.id = id;
		this.nome = nome;
		this.custoDia = custoDia;
		this.codinome = codinome;
	}

	public int getId() {
		return this.id;
	}

	public String getNome() {
		return this.nome;
	}

	public double getCustoDia() {
		return this.custoDia;
	}

	public String getCodinome() {
		return this.codinome;
	}

	public String toString() {
		return "ID: " + this.id + ", Nome: " + this.nome + ", Custo por Dia: " + this.custoDia + ", Codinome: "
				+ this.codinome;
	}

	public String toCSVString() {
		return this.id + ";" + this.nome + ";" + this.custoDia + ";" + this.codinome;
	}
}
