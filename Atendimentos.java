import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Atendimentos {
    private ArrayList<Atendimento> atendimentos;
    private Queue<Atendimento> filaAtendimentos;

    public Atendimentos(){
        this.atendimentos = new ArrayList<Atendimento>();
        this.filaAtendimentos = new LinkedList<Atendimento>();
    }

    // ArrayList

    public boolean addAtendimento(Atendimento atendimento){
        for (Atendimento aux : atendimentos){
            if (aux.getCod() == atendimento.getCod()){
                return false;
            }
        }
        if(atendimento.getStatus().equalsIgnoreCase("pendente")) 
            addAtendimentoFila(atendimento);
            
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

    // Fila

    public boolean addAtendimentoFila(Atendimento atendimento){
        for (Atendimento aux : atendimentos){
            if (aux.getCod() == atendimento.getCod()){
                return false;
            }
        }
        return filaAtendimentos.add(atendimento);
    }

    public Atendimento getPrimeiroFila(){
        return filaAtendimentos.peek();
    } 

    public Queue<Atendimento> getAtendimentosFila(){
        return this.filaAtendimentos;
    }
}
