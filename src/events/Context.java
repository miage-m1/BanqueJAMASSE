package events;

import models.Compte;
import models.CompteCourant;
import models.CompteEpargne;
import models.Operation;

import java.beans.PropertyChangeListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Context {

    final private Map<Integer, CompteEpargne> comptesEpargne = new HashMap<>();
    final private PropertyChangeListener epargne = new EpargneListener(this);
    final private Map<Integer, CompteCourant> comptesCourant = new HashMap<>();
    final private PropertyChangeListener alimentation = new AlimentationListener(this);
    final private Map<Integer, Operation> operation = new HashMap<>();


    public Integer getMaxIdCompte() {
        if (!this.comptesCourant.isEmpty()) {
            Set<Integer> s = this.comptesCourant.keySet();
            s.addAll(this.comptesEpargne.keySet());
            return Collections.max(s);
        }
        return 0;

    }

    public void addCompte(Compte c) {
        if (c.getClass().equals(CompteEpargne.class)) {
              c.addPropertyChangeListener("solde", this.epargne);
        } else if (c.getClass().equals(CompteCourant.class)) {
            c.addPropertyChangeListener("solde", this.alimentation);
            comptesCourant.put(c.getIdCompte(), (CompteCourant) c);
        }
    }

    public Integer getMaxIdOperation() {
        if (!operation.isEmpty())
            return Collections.max(operation.keySet());
        return 0;
    }


    public void addOperation(Operation operation) {
        this.operation.put(operation.getIdOperation(), operation);
        operation.getCompteCrediter().setSolde(operation.getCompteCrediter().getSolde() + operation.getMontant());
        operation.getCompteDebiter().setSolde(operation.getMontant());
    }
}
