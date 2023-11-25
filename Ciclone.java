public class Ciclone extends Evento {

	private double velocidade;
	private double precipitacao;


	public Ciclone(String codigo, String data, double latitude, double longitude, double velocidade, double precipitacao) {
		super(codigo, data, latitude, longitude);
		this.velocidade = velocidade;
		this.precipitacao = precipitacao;
	}

	public String toString() {
		return super.toString() + "\nTipo: Ciclone" + "\nVelocidade: " + this.velocidade + "\nPrecipitação: " + this.precipitacao;
	}
}
