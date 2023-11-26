public class Atendimento {

	private int cod;

	private String dataInicio;

	private int duracao;

	private String status;

	private Evento evento;

	private Equipe equipe;

	private Double deslocamento;// descobir como fazer

	public Atendimento(int cod, String dataInicio, int duracao, String status, Evento evento, Equipe equipe) {
		this.cod = cod;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.status = status;
		this.evento = evento;
		this.equipe = equipe;
	}

	public double calculaCusto() {

		return (duracao * 250 * equipe.getQuantidade()) + (duracao * equipe.custoDiario()) + (deslocamento * (100 * equipe.getQuantidade() + 0.1 * equipe.custoDiario()));
	}


	private double calculaDistancia(Equipe equipe, Evento evento) {

			double raioDaTerra = 6371.0;		
			double lat1 = Math.toRadians(equipe.getLatitude());
			double lon1 = Math.toRadians(equipe.getLongitude());
			double lat2 = Math.toRadians(evento.getLatitude());
			double lon2 = Math.toRadians(evento.getLongitude());
	
			// Diferença entre as latitudes e longitudes
			double dlat = lat2 - lat1;
			double dlon = lon2 - lon1;
	
			// Fórmula de Haversine
			double a = Math.sin(dlat / 2) * Math.sin(dlat / 2) +
					   Math.cos(lat1) * Math.cos(lat2) *
					   Math.sin(dlon / 2) * Math.sin(dlon / 2);
			double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
	
			// Calcular a distância
			double distancia = raioDaTerra * c;
	
			return distancia;
	}
}
