import java.util.ArrayList;

public class Eventos {
    private ArrayList<Evento> eventos;

    public Eventos() {
        this.eventos = new ArrayList<Evento>();
    }

    public void addEvento(Evento evento) {
        int i = 0;
        while (i < this.eventos.size() && evento.getCodigo().compareTo(this.eventos.get(i).getCodigo()) > 0) {
            i++;
        }
        this.eventos.add(i, evento);
    }

    public ArrayList<Evento> getEventos() {
        return this.eventos;
    }

    public void removeEvento(Evento evento) {
        this.eventos.remove(evento);
    }

    
    public boolean containsCodigo(String codigo) {
        for (Evento evento : this.eventos) {
            if (evento.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }
}
