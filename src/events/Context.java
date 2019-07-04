package events;

import models.*;

import java.beans.PropertyChangeListener;
import java.util.*;

public class Context {

    public static final long TROIS_MINUTES = 1000 * 60;
    final private Map<Integer, CompteEpargne> comptesEpargne = new HashMap<>();
    final private Map<Integer, CompteCourant> comptesCourant = new HashMap<>();
    final private PropertyChangeListener alimentation = new AlimentationListener(this);
    final private PropertyChangeListener epargne = new EpargneListener(this);
    final private Timer agenda = new Timer();
    private Map<Integer, Operation> operations = new HashMap<>();

    public Integer getMaxIdCompte() {
        if (!this.comptesCourant.isEmpty()) {
            Set<Integer> s = this.comptesCourant.keySet();
            s.addAll(this.comptesEpargne.keySet());
            return Collections.max(s);
        }
        return 0;
    }

    public ArrayList<CompteEpargne> getCompteEpargnes() {
        return new ArrayList<>(this.comptesEpargne.values());
    }

    public void updateCompteEpargne(List<CompteEpargne> ceUpdated) {
        for (CompteEpargne ce : ceUpdated) {
            this.comptesEpargne.get(ce.getIdCompte()).setSolde(ce.getSolde());
        }
    }

    public void addCompte(Compte c) {
        if (c.getClass().equals(CompteEpargne.class)) {
            c.addPropertyChangeListener("alimentation", this.epargne);
            comptesEpargne.put(c.getIdCompte(), (CompteEpargne) c);
            this.agenda.schedule(new CalculInteret(this), TROIS_MINUTES);
        } else if (c.getClass().equals(CompteCourant.class)) {
            c.addPropertyChangeListener("solde", this.alimentation);
            c.addPropertyChangeListener("seuilMax", this.alimentation);
            comptesCourant.put(c.getIdCompte(), (CompteCourant) c);
        }
    }

    public int addOperation(Operation operation) {
        int idOperation = this.operations.size() + 1;
        this.operations.put(this.operations.size() + 1, operation);
        return idOperation;
    }

    public Map<Integer, Operation> getOperations() {
        return this.operations;
    }

    public Timer getAgenda(){
        return this.agenda;
    }


}
