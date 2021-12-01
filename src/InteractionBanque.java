import java.util.List;
import java.util.Scanner;

public class InteractionBanque {

    private Banque banque;

    public InteractionBanque(Banque banque) {
        this.banque = banque;
    }

    public void interagirBanque() {
        Scanner scanner = new Scanner(System.in);
        menuBanque(scanner);
    }

    private void menuBanque(Scanner scanner) {
        System.out.println("Quel opération voulez-vous effectuer ?");
        System.out.println("1) Ajouter un client");
        System.out.println("2) Effectuer une operation sur un client");
        System.out.println("3) Afficher un bilan");

        int reponse = 0;

        while (reponse != 1 && reponse != 2 && reponse != 3) {
            System.out.println("Choisir entre 1 et 3 :");
            reponse = scanner.nextInt();
        }
        System.out.println("- " + reponse);

        switch (reponse) {
            case 1:
                Client nouveauClient = ajouterUnClient(scanner);
                System.out.println("- " + nouveauClient.getNom());
                System.out.println("Creez un premier compte :");
                System.out.println("Compte numéro 1, quel solde ajouter vous ?");
                float soldeDepose = scanner.nextFloat();
                nouveauClient.ajouterCompte(soldeDepose);
                System.out.printf("Solde bien déposé");
                nouveauClient.afficherSolde();
                interagirBanque();
                break;
            case 2:
                System.out.println("Quel client ?");
                List<Client> clients = listerClients();
                for (int i = 0; i < clients.size(); i++) {
                    System.out.println(i+1 + ") " + clients.get(i).getNom());
                }
                int clientChoisi=0;
                while (true) {
                    System.out.println("Entrez le numéro du client");
                    clientChoisi = scanner.nextInt();
                    if(clientChoisi <= clients.size() && clientChoisi>0) {
                        break;
                    }
                }
                Client clientCible = clients.get(clientChoisi-1);
                System.out.println("- " + clientCible.getNom());

                operationClient(scanner, clientCible);
                break;
            case 3:
                banque.afficherBilan();
                break;
        }

    }

    private Client ajouterUnClient(Scanner scanner) {
        System.out.println("Entrez le nom du client");
        String nomDuClient = scanner.next();
        return banque.ajouterClient(nomDuClient);
    }

    private List<Client> listerClients() {
        return banque.getClients();
    }

    private Client recupererClientParNom(String nomClient) {
        return banque.recupererClientParNom(nomClient);
    }

    private void operationClient(Scanner scanner, Client clientCible) {
        int reponse = 0;
        System.out.println("Quel opération voulez-vous effectuer sur " + clientCible.getNom()+" ?");
        System.out.println("1) Afficher un bilan");
        System.out.println("2) Faire un retrait");
        System.out.println("3) Faire un dépôt");
        System.out.println("4) Faire un virement");
        System.out.println("5) Retour menu");

        while (reponse < 1 || reponse > 5) {
            System.out.println("Entrez le numéro de l'action");
            reponse = scanner.nextInt();
        }

        switch (reponse) {
            case 1:
                banque.bilanClient(clientCible.getId());
                interagirBanque();
                break;
            case 2:
                operationCompte(scanner, clientCible, 0);
                break;
            case 3:
                operationCompte(scanner, clientCible, -1);
                break;
            case 4:
                operationCompte(scanner, clientCible, 1);
                break;
            case 5:
                menuBanque(scanner);
                break;
        }
    }

    private void operationCompte(Scanner scanner, Client clientCible, int contexte) {
        int reponse = 0;
        Compte[] comptesCLient = clientCible.getComptes();
        System.out.println("Sur quel compte ?");
        for (int i = 0; i < clientCible.retournerNombreDeCompte(); i++) {
            System.out.println(i+1 + ") Compte numéro :" + (comptesCLient[i].getId()+1));
        }
        while (reponse <0 && reponse<=comptesCLient.length) {
                System.out.println("Choisissez un compte");
                reponse = scanner.nextInt();
        }
        Compte compteCible = clientCible.retournerCompteParId(reponse);
        System.out.println("- Compte n" + (compteCible.getId()+1));
        if (contexte == -1) {
            System.out.println("Somme à déposer");
            compteCible.depot(scanner.nextFloat());
            System.out.println("Dépot effectué");
            System.out.println("Nouveau solde : "+compteCible.getSolde());
        } else if (contexte == 0) {
            System.out.println("Somme à retirer :");
            compteCible.retrait(scanner.nextFloat());
            System.out.println("Retrait effectué");
            System.out.println("Nouveau solde : "+compteCible.getSolde());
        } else {
            System.out.println("Somme à virer :");
            compteCible.virerA(scanner.nextFloat(), compteCible);
            System.out.println("Virement effectué");
        }
        interagirBanque();
    }
}
