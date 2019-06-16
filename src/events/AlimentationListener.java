package events;

import enumeration.TypeOperation;
import models.Compte;
import models.CompteCourant;
import models.CompteEpargne;
import models.Operation;

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

        float montant;


        if ((Float) evt.getOldValue() >= seuilMinimum && (Float) evt.getNewValue() < seuilMinimum) {
            CompteEpargne compteEpargne = compteCourant.getCompteEpargneMin();
            montant = compteCourant.getSeuilMin() - compteCourant.getSolde();
            if (montant > compteEpargne.getSolde()) {
                System.out.println("Le solde du compte épargne ne permet pas la réalimentation automatique du compte");
                return;
            }
            context.addOperation(createOperation(compteEpargne, compteCourant, montant));
        } else {
            CompteEpargne compteEpargne = compteCourant.getCompteEpargneMax();
            montant = compteCourant.getSolde() - compteCourant.getSeuilMax();
            context.addOperation(createOperation(compteCourant, compteEpargne, montant));
        }

        System.out.println("JE SUIS DANS L'EVENT DE MODIFICATION DE SOLDE A CAUSE D'UN MAX OU D'UN MIN !");

    }


    private Operation createOperation(Compte compteDebiter, Compte compteCrediter, float montant) {
        return new Operation(context.getMaxIdOperation() + 1, montant, TypeOperation.VIREMENT, compteDebiter, compteCrediter);
    }


}
