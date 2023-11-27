import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Equipamentos {
    private ArrayList<Equipamento> equipamentos;

    public Equipamentos(){
        equipamentos = new ArrayList<Equipamento>();
    }

    public boolean addEquipamento(Equipamento equipamento){
        for (Equipamento aux : equipamentos){
            if (aux.getId() == equipamento.getId()){
                return false;
            }
        }
        int i = 0;
        while (i < this.equipamentos.size() && equipamento.getId() > this.equipamentos.get(i).getId()) {
            i++;
        }
        this.equipamentos.add(i, equipamento);
        return true;
    }

    public ArrayList<Equipamento> getEquipamentos(){
        return this.equipamentos;
    }

    public void ordenarEquipamentosPorIdCrescente() {
        Collections.sort(equipamentos, new Comparator<Equipamento>() {
            @Override
            public int compare(Equipamento equipamento1, Equipamento equipamento2) {
                return Integer.compare(equipamento1.getId(), equipamento2.getId());
            }
        });
    }
}
