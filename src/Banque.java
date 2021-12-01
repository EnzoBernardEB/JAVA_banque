import java.util.ArrayList;
import java.util.List;

public class Banque {
    private List<Client> Clients = new ArrayList<Client>();
    private int identifiantClient = 1;


    Client ajouterClient(String nom){
        Clients.add(new Client(nom,identifiantClient));
        Client nouveauClient = this.recupererClientParId(identifiantClient);
        this.identifiantClient++;
        return nouveauClient;
    }

    List<Client> getClients() {
        return Clients;
    }

    Client recupererClientParNom(String nomClient) {
        for (Client client: Clients) {
            if(client.getNom() == nomClient) {
                return client;
            }
        }
        return null;
    }

    void bilanClient(long clienId) {
        Client clientCible = this.recupererClientParId(clienId);
        clientCible.afficherSolde();
    }

    void afficherBilan() {
        float bilanGeneral = 0;
        for (Client client: Clients) {
            bilanGeneral = bilanGeneral + client.getSolde();
        }
        System.out.println(bilanGeneral);
    }

    private Client recupererClientParId(long clientId){
        for (Client client: Clients) {
            if(client.getId() == clientId) {
                return client;
            }
        }
        return null;
    }

}
