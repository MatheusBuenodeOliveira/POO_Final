public class Seca extends Evento {
	private int estiagem;
	public Seca(String codigo, String data, double latitude, double longitude, int estiagem) {
		super(codigo, data, latitude, longitude);
		this.estiagem = estiagem;
	}
	public String toString() {
		return super.toString() + "\nTipo: Seca" + "\nEstiagem: " + this.estiagem;
	}

	public String toCSVString() {
		return super.toCSVString() +";"+"3"+ ";" + this.estiagem;
	}
}