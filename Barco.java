public class Barco extends Equipamento {

	private int capacidade;

	public Barco(int id, String nome, double custoDia, String codinome, int capacidade) {
		super(id, nome, custoDia, codinome);
		this.capacidade = capacidade;
	}

}
