public class CaminhaoTanque extends Equipamento {

	private double capacidade;

	public CaminhaoTanque(int id, String nome, double custoDia, String codinome, double capacidade) {
		super(id, nome, custoDia, codinome);
		this.capacidade = capacidade;
	}

}
