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

}
