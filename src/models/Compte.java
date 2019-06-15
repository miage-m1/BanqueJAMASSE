package models;

import java.util.Date;

public abstract class Compte extends lib.PropertyChangeSupport {

    protected int idCompte;
    protected float solde;
    protected Date dateOuverture;
    protected Client proprietaire;

    public Compte(float solde, Client proprietaire){
        super();
        this.solde = solde;
        this.proprietaire = proprietaire;
        this.dateOuverture = new Date();
    }


    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public float getSolde() {
        return solde;
    }

    public void setSolde(float solde) {
        this.solde = solde;
    }

    public Client getPropietaire() {
        return proprietaire;
    }

    public void setPropietaire(Client proprietaire) {
        this.proprietaire = proprietaire;
    }

    public Date proprietaire() {
        return dateOuverture;
    }
}
