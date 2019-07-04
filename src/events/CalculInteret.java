package events;

import models.CompteEpargne;

import java.util.ArrayList;
import java.util.TimerTask;

public class CalculInteret extends TimerTask {
    final private Context context;

    public CalculInteret(Context context) {
        this.context = context;
    }

    @Override
    public void run() {
        System.out.println("JE SUIS DANS LE RUN DU TASKER ");
        ArrayList<CompteEpargne> compteEpargnes = this.context.getCompteEpargnes();
        ArrayList<CompteEpargne> currentEpargne = new ArrayList<>();
        for (CompteEpargne ce : compteEpargnes) {
            ce.calculTxInteret();
            currentEpargne.add(ce);
        }
        this.context.updateCompteEpargne(currentEpargne);
        this.context.getAgenda().schedule(new CalculInteret(context), 1000*60);


    }
}
