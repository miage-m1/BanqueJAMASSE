package models;

import enumeration.TypeEpargne;

public class CompteEpargne extends Compte {
    private CompteCourant compteCourant;
    private float txInteret;
    private float soldeMax;
    private TypeEpargne type;


    public CompteEpargne(float solde, Client proprietaire, CompteCourant compteCourant, float txInteret, float soldeMax, TypeEpargne type) {
        super(solde, proprietaire);
        this.compteCourant = compteCourant;
        this.txInteret = txInteret;
        this.soldeMax = soldeMax;
        this.type = type;
    }

    @Override
    public void setSolde(float solde) {
        Float oldValue = this.solde;
        this.solde = solde;
        this.firePropertyChange("alimentation", oldValue, solde);
    }


    public CompteCourant getCompteCourant() {
        return compteCourant;
    }

    public void setCompteCourant(CompteCourant compteCourant) {
        this.compteCourant = compteCourant;
    }

    public float getTxInteret() {
        return txInteret;
    }

    public void setTxInteret(float txInteret) {
        this.txInteret = txInteret;
    }

    public float getSoldeMax() {
        return soldeMax;
    }

    public void setSoldeMax(float soldeMax) {
        this.soldeMax = soldeMax;
    }

    public TypeEpargne getType() {
        return type;
    }

    public void setType(TypeEpargne type) {
        this.type = type;
    }

    public void calculTxInteret() {
        this.setSolde(getSolde() * (1 + txInteret));
    }
}
