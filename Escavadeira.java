public class Escavadeira extends Equipamento {

	private String combustivel;

	private double carga;

	public Escavadeira(int id, String nome, double custoDia, String codinome,  String combustivel, double carga) {
		super(id, nome, custoDia, codinome);
		this.combustivel = combustivel;
		this.carga = carga;
	}

	
}
