package main;

import enumeration.TypeEpargne;
import enumeration.TypeOperation;
import events.Context;
import models.Client;
import models.CompteCourant;
import models.CompteEpargne;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Context context = new Context();
        Map<Integer, Client> clients = new HashMap<>();
        //Création du client Maxime avec un compte Courant
        Client Maxime = new Client(clients.size() + 1, "Guénégo", "Maxime", "57 boulevard de la villette Paris", "maxime.guenego@gmail.com");
        clients.put(Maxime.getIdClient(), Maxime);
        CompteCourant compteC = new CompteCourant(0, Maxime, -100, 4500, null, null);
        compteC.setIdCompte(context.getMaxIdCompte() + 1);
        context.addCompte(compteC);




        //Création du client Samuel avec un compte courant
        Client Samuel = new Client(clients.size() + 1, "Kervarec", "Samuel", "2 place de la mairie Lorient", "sam.kervarec@gmail.com");
        clients.put(Samuel.getIdClient(), Samuel);

        CompteCourant compteCSam = new CompteCourant(100, Samuel, -500, 25000, null, null);
        compteCSam.setIdCompte(context.getMaxIdCompte() + 1);

        CompteEpargne compteEpargneSam = new CompteEpargne(100000,Samuel,compteCSam,3,1000000,TypeEpargne.CODEVI);
        context.addCompte(compteEpargneSam);
        compteCSam.setCompteEpargneMax(compteEpargneSam);
        compteCSam.setCompteEpargneMin(compteEpargneSam);

        context.addCompte(compteCSam);

        System.out.println("AVANT MODIFICATION");
        System.out.println("COMPTE EPARGNE : " + compteEpargneSam.getSolde());
        System.out.println("COMPTE CCOURANT : "+ compteCSam.getSolde());
        compteCSam.setSolde(50000);


        System.out.println("APRES MODIFICATION");
        System.out.println("COMPTE EPARGNE : " + compteEpargneSam.getSolde());
        System.out.println("COMPTE CCOURANT : "+ compteCSam.getSolde());



    }
}
