package models;

import enumeration.TypeOperation;

import java.util.Date;

public class Operation {
    private int idOperation;
    private Date dateOperation;
    private float montant;
    private TypeOperation operation;
    private Compte compteDebiter;
    private Compte compteCrediter;

    public Operation(int idOperation, float montant, TypeOperation operation, Compte compteDebiter, Compte compteCrediter) {
        this.idOperation = idOperation;
        this.montant = montant;
        this.operation = operation;
        this.compteDebiter = compteDebiter;
        this.compteCrediter = compteCrediter;
        this.dateOperation = new Date();
    }

    public int getIdOperation() {
        return idOperation;
    }

    public void setIdOperation(int idOperation) {
        this.idOperation = idOperation;
    }

    public Date getDateOperation() {
        return dateOperation;
    }

    public void setDateOperation(Date dateOperation) {
        this.dateOperation = dateOperation;
    }

    public float getMontant() {
        return montant;
    }

    public void setMontant(float montant) {
        this.montant = montant;
    }

    public TypeOperation getOperation() {
        return operation;
    }

    public void setOperation(TypeOperation operation) {
        this.operation = operation;
    }

    public Compte getCompteDebiter() {
        return compteDebiter;
    }

    public void setCompteDebiter(Compte compteDebiter) {
        this.compteDebiter = compteDebiter;
    }

    public Compte getCompteCrediter() {
        return compteCrediter;
    }

    public void setCompteCrediter(Compte compteCrediter) {
        this.compteCrediter = compteCrediter;
    }
}
