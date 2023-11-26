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
        return equipamentos.add(equipamento);
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
