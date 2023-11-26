import java.util.ArrayList;

public class Atendimentos {
    private ArrayList<Atendimento> atendimentos;

    public Atendimentos(){
        this.atendimentos = new ArrayList<Atendimento>();
    }

    public boolean addAtendimento(Atendimento atendimento){
        for (Atendimento aux : atendimentos){
            if (aux.getCod() == atendimento.getCod()){
                return false;
            }
        }
        return atendimentos.add(atendimento);
    }

    public boolean removeAtendimentoCod(int cod){
        for (Atendimento aux : atendimentos){
            if (aux.getCod() == cod){
                return atendimentos.remove(aux);
            }
        }
        return false;
    }

    public Atendimento getAtendimento(int cod){
        for (Atendimento aux : atendimentos){
            if (aux.getCod() == cod){
                return aux;
            }
        }
        return null;
    }

    public ArrayList<Atendimento> getAtendimentos(){
        return this.atendimentos;
    }
}
