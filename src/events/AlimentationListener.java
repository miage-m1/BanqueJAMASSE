package events;

import models.CompteCourant;
import models.CompteEpargne;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class AlimentationListener implements PropertyChangeListener {

    final private Context context;

    public AlimentationListener(Context context) {
        this.context = context;
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (predicat_evt(evt)) trigger_evt(evt);
    }

    private boolean predicat_evt(PropertyChangeEvent evt) {
        if (evt.getPropertyName().equals("solde")) {
            CompteCourant cc = (CompteCourant) evt.getSource();
            int seuilMinimum = cc.getSeuilMin();
            int seuilMaximum = cc.getSeuilMax();
            return (Float) evt.getOldValue() >= seuilMinimum && (Float) evt.getNewValue() < seuilMinimum
                    || (Float) evt.getOldValue() <= seuilMaximum && (Float) evt.getNewValue() > seuilMaximum;
        }
        return false;
    }

    private void trigger_evt(PropertyChangeEvent evt) {
        CompteCourant compteCourant = (CompteCourant) evt.getSource();
        int seuilMinimum = compteCourant.getSeuilMin();
        int seuilMaximum = compteCourant.getSeuilMax();


        if ((Float) evt.getOldValue() >= seuilMinimum && (Float) evt.getNewValue() < seuilMinimum) {
            CompteEpargne compteEpargne = compteCourant.getCompteEpargneMin();
        } else {
            CompteEpargne compteEpargne = compteCourant.getCompteEpargneMax();
        }

        System.out.println("JE SUIS DANS L'EVENT DE MODIFICATION DE SOLDE CRITIQUE !");



    }
}
