package models;

public class CompteCourant extends Compte {

    private int seuilMin;
    private int seuilMax;
    private CompteEpargne compteEpargneMin;
    private CompteEpargne compteEpargneMax;

    public CompteCourant(float solde, Client proprietaire, int seuilMin, int seuilMax, CompteEpargne compteEpargneMin, CompteEpargne compteEpargneMax) {
        super(solde, proprietaire);
        this.seuilMin = seuilMin;
        this.seuilMax = seuilMax;
        this.compteEpargneMin = compteEpargneMin;
        this.compteEpargneMax = compteEpargneMax;
    }

    @Override
    public void setSolde(float solde) {
        Float oldValue = this.solde;
        this.solde = solde;
        this.firePropertyChange("solde", oldValue, solde);
    }




    public int getSeuilMin() {
        return seuilMin;
    }

    public void setSeuilMin(int seuilMin) {
        this.seuilMin = seuilMin;
    }

    public int getSeuilMax() {
        return seuilMax;
    }

    public void setSeuilMax(int seuilMax) {
        this.seuilMax = seuilMax;
    }

    public CompteEpargne getCompteEpargneMin() {
        return compteEpargneMin;
    }

    public void setCompteEpargneMin(CompteEpargne compteEpargneMin) {
        this.compteEpargneMin = compteEpargneMin;
    }

    public CompteEpargne getCompteEpargneMax() {
        return compteEpargneMax;
    }

    public void setCompteEpargneMax(CompteEpargne compteEpargneMax) {
        this.compteEpargneMax = compteEpargneMax;
    }
}
