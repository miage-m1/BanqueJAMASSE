package main;

import enumeration.TypeEpargne;
import enumeration.TypeOperation;
import events.Context;
import models.Client;
import models.CompteCourant;
import models.CompteEpargne;
import models.Operation;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Context context = new Context();
        Map<Integer, Client> clients = new HashMap<>();
        //Création du client Maxime avec un compte Courant
        Client Maxime = new Client(clients.size() + 1, "Guénégo", "Maxime", "57 boulevard de la villette Paris", "maxime.guenego@gmail.com");
        clients.put(Maxime.getIdClient(), Maxime);
        CompteCourant compteC = new CompteCourant(100000, Maxime, -100, 200000, null, null);
        compteC.setIdCompte(context.getMaxIdCompte() + 1);
        context.addCompte(compteC);

        CompteEpargne compteEpargneMaxime = new CompteEpargne(0, Maxime, compteC, (float) 0.10, 300000, TypeEpargne.LIVRET_A);
        compteC.setCompteEpargneMin(compteEpargneMaxime);
        compteC.setCompteEpargneMax(compteEpargneMaxime);

        System.out.println("MAXIME " + compteC.getIdCompte());


        //Création du client Samuel avec un compte courant
        Client Samuel = new Client(clients.size() + 1, "Kervarec", "Samuel", "2 place de la mairie Lorient", "sam.kervarec@gmail.com");
        clients.put(Samuel.getIdClient(), Samuel);
        CompteCourant compteCSam = new CompteCourant(100, Samuel, -500, 25000, null, null);
        compteCSam.setIdCompte(context.getMaxIdCompte() + 1);
        context.addCompte(compteCSam);

        CompteEpargne compteEparSam = new CompteEpargne(0, Samuel, compteCSam, (float) 0.10, 300000, TypeEpargne.LIVRET_A);
        compteEparSam.setIdCompte(context.getMaxIdCompte() + 1);
        context.addCompte(compteEparSam);
        compteCSam.setCompteEpargneMax(compteEparSam);
        compteCSam.setCompteEpargneMin(compteEparSam);


        //Maxime fait un virement à samuel de 50 000euros sur compte courrant
        int lastOperation = context.addOperation(new Operation(1, 50000, TypeOperation.VIREMENT, compteC, compteCSam));
        System.out.println("AFFICHAGE DU COMPTE EPARGNE AVANT EVENT " + compteEparSam.getSolde());
        System.out.println("AFFICHAGE DU COMPTE COURRANT AVANT EVENT " + compteCSam.getSolde());
        context.getOperations().get(lastOperation).activeOperation();

        System.out.println("AFFICHAGE DU COMPTE EPARGNE APRES EVENT " + compteEparSam.getSolde());
        System.out.println("AFFICHAGE DU COMPTE COURRANT APRES EVENT " + compteCSam.getSolde());

        //gestion évenement temporel
        System.out.println("AFFICHAGE SOLDE AVANT TIMER TASK");
        System.out.println(compteEparSam.getSolde());
        try {
            Thread.sleep(1000 * 70);
        } catch (Exception e) {

        }
        System.out.println("AFFICHAGE SOLDE APRES TIMER TASK 1er fois");
        System.out.println(compteEparSam.getSolde());

        try {
            Thread.sleep(1000 * 70);
        } catch (Exception e) {

        }
        System.out.println("AFFICHAGE SOLDE APRES TIMER TASK 2eme fois");
        System.out.println(compteEparSam.getSolde());

    }
}
