import java.util.ArrayList;

public class Equipes {
    private ArrayList<Equipe> equipes;

    public Equipes(){
        this.equipes = new ArrayList<Equipe>();
    }

    public boolean addEquipe(Equipe equipe){
        for (Equipe aux : equipes){
            if (aux.getCodinome().equals(equipe.getCodinome())){
                return false;
            }
        }
        return equipes.add(equipe);
    }

    public boolean removeEquipeNome(String nome){
        for (Equipe aux : equipes){
            if (aux.getCodinome().equals(nome)){
                return equipes.remove(aux);
            }
        }
        return false;
    }

    public Equipe getEquipe(String nome){
        for (Equipe aux : equipes){
            if (aux.getCodinome().equals(nome)){
                return aux;
            }
        }
        return null;
    }

    public ArrayList<Equipe> getEquipes(){
        return this.equipes;
    }
}