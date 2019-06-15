package main;

import events.Context;
import models.Client;
import models.CompteCourant;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Context context = new Context();
        Map<Integer, Client> clients = new HashMap<>();
        //Création du client Maxime avec un compte Courant
        Client Maxime = new Client(clients.size() + 1, "Guénégo", "Maxime", "57 boulevard de la villette Paris", "maxime.guenego@gmail.com");
        clients.put(Maxime.getIdClient(),Maxime);
        CompteCourant compteC = new CompteCourant(0, Maxime, -100, 4500, null, null);
        compteC.setIdCompte(context.getMaxIdCompte());



        //Création du client Samuel avec un compte courant
        Client Samuel = new Client(clients.size() + 1, "Kervarec", "Samuel", "2 place de la mairie Lorient", "sam.kervarec@gmail.com");
        clients.put(Samuel.getIdClient(), Samuel);
        CompteCourant compteCSam = new CompteCourant(100, Samuel, -500, 25000, null, null);
        compteCSam.setIdCompte(context.getMaxIdCompte());
        context.addCompte(compteCSam);
        compteCSam.setSolde(50000);




    }
}
