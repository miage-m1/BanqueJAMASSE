package events;

import enumeration.TypeOperation;
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
        }else{
            return true;
        }
    }

    private void trigger_evt(PropertyChangeEvent evt) {
        if(evt.getPropertyName().equals("solde")) {
            CompteCourant compteCourant = (CompteCourant) evt.getSource();
            int seuilMinimum = compteCourant.getSeuilMin();
            int seuilMaximum = compteCourant.getSeuilMax();

            CompteEpargne compteEpargne;
            int idOperation = this.context.getOperations().size() + 1;
            Operation operation;
            if ((Float) evt.getOldValue() >= seuilMinimum && (Float) evt.getNewValue() < seuilMinimum) {
                compteEpargne = compteCourant.getCompteEpargneMin();
                operation = new Operation(idOperation, compteCourant.getSolde() - seuilMinimum, TypeOperation.AlimentationInterne, compteEpargne, compteCourant);
            } else {
                compteEpargne = compteCourant.getCompteEpargneMax();
                System.out.println(compteEpargne);
                operation = new Operation(idOperation, compteCourant.getSolde() - seuilMaximum, TypeOperation.AlimentationInterne, compteCourant, compteEpargne);
            }
            operation.activeOperation();
            this.context.getOperations().put(idOperation, operation);


            System.out.println("JE SUIS DANS L'EVENT DE MODIFICATION DE SOLDE CRITIQUE !");
        }
    }
}
