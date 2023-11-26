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

	public double getCustoDia() {
		return this.custoDia;
	}
}
