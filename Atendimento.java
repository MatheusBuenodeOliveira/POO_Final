public class Atendimento {

	private int cod;

	private String dataInicio;

	private int duracao;

	private String status;

	private Evento evento;

	private Equipe equipe;

	private Double deslocamento;

	public Atendimento(int cod, String dataInicio, int duracao, String status, Evento evento) {
		this.cod = cod;
		this.dataInicio = dataInicio;
		this.duracao = duracao;
		this.status = status;
		this.evento = evento;
		this.equipe = null;
	}

	public int getCod() {
		return this.cod;
	}

	public String getDataInicio() {
		return this.dataInicio;
	}

	public int getDuracao() {
		return this.duracao;
	}

	public String getStatus() {
		return this.status;
	}

	public Evento getEvento() {
		return this.evento;
	}

	public Equipe getEquipe() {
		return this.equipe;
	}

	public Double getDeslocamento() {
		return this.deslocamento;
	}

	public void setDeslocamento(double deslocamento) {
		this.deslocamento = deslocamento;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

	public void setStatus(String status) {	
		this.status = status;	
	}

	public double calculaCusto() {

		return (duracao * 250 * equipe.getQuantidade()) + (duracao * equipe.custoDiario()) + (deslocamento * (100 * equipe.getQuantidade() + 0.1 * equipe.custoDiario()));
	}

	public String toString() {
    return "Código: " + cod + 
           "\nData de Início: " + dataInicio + 
           "\nDuração: " + duracao + 
           "\nStatus: " + status + 
        	(evento != null ? "\nEvento: " + evento.getCodigo() :  "")
			 + (equipe != null ? "\nEquipe: " + equipe.getCodinome() : "");
	}

	public String toString2(){
        return "Código: " + cod + 
           "\nData de Início: " + dataInicio + 
           "\nDuração: " + duracao + 
           "\nStatus: " + status + 
		(evento != null ? "\nEvento: " + evento.toString() :  "")
			 + (equipe != null ? "\nEquipe: " + equipe.toString(): "");
	}


	public String toCSVString() {
		return cod + ";" + dataInicio + ";" + duracao + ";" + status + ";" + 
       (evento != null ? evento.getCodigo() : "" ) + ";" + 
       // Aqui você precisa decidir o que fazer se `equipe` for nulo. 
       // Estou assumindo que você vai querer retornar uma string vazia nesse caso.
       (equipe != null ? equipe.getCodinome() + ";" + 
       deslocamento + ";" + calculaCusto() : "") + ";";
	}


	public double calculaDistancia(Equipe equipe, Evento evento) {

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
