import java.util.ArrayList;

public class Equipe {
    private String codinome;
    private int quantidade;
    private double latitude;
    private double longitude;
	private ArrayList <Equipamento> equipamentos = new ArrayList <Equipamento>();

    public Equipe(String codinome, int quantidade, double latitude, double longitude){
        this.codinome = codinome;
        this.quantidade = quantidade;
        this.latitude = latitude;
        this.longitude = longitude;
		
    }

    public String getCodinome(){
        return this.codinome;
    }

    public int getQuantidade(){
        return this.quantidade;
    }

    public double getLatitude(){
        return this.latitude;
    }

    public double getLongitude(){
        return this.longitude;
    }

    public void setCodinome(String codinome){
        this.codinome = codinome;
    }

    public void setQuantidade(int quantidade){
        this.quantidade = quantidade;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public void addEquipamento(Equipamento equipamento){
        this.equipamentos.add(equipamento);
    }

	public double custoDiario(){
		double custo = 0;
		for (Equipamento equipamento : equipamentos){
			custo += equipamento.getCustoDia();
		}
		return custo;
	}

    // Método para verificar se um equipamento já está vinculado
    public boolean possuiEquipamento(int equipamentoId) {
        for (Equipamento equipamento : equipamentos) {
            if (equipamento.getId() == equipamentoId) {
                return true;
            }
        }
        return false;
    }

     // Método para remover um equipamento
     public void removerEquipamento(int equipamentoId) {
        equipamentos.removeIf(equipamento -> equipamento.getId() == equipamentoId);
    }

    @Override
    public String toString(){
        return "Codinome: " + this.codinome + "\nQuantidade: " + this.quantidade + "\nLatitude: " + this.latitude + "\nLongitude: " + this.longitude + "\n -----------------";
    }

    public String toCSVString(){
        return this.codinome + ";" + this.quantidade + ";" + this.latitude + ";" + this.longitude;
    }
}