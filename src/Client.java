import java.util.ArrayList;
import java.util.List;

public class Client {
    private int Id;
    private Compte[] Comptes = new Compte[100];
    private int NombreDeCompte = 0;
    private String Nom;

    public Client(String nomDuClient, int identifiant) {
        this.Nom = nomDuClient;
        this.Id = identifiant;
    }

    float getSolde(){
        float sommeDesSoldes=0;
        for (int i =0; i< NombreDeCompte; i++) {
            sommeDesSoldes = sommeDesSoldes + Comptes[i].getSolde();
        }
        return sommeDesSoldes;
    }

    Compte[] getComptes() {
        return this.Comptes;
    }

    Compte retournerCompteParId(int compteId) {
        for (Compte compte: Comptes) {
            if(compte.getId() == compteId) {
                return compte;
            }
        }
        return null;
    }

    long getId() {
        return this.Id;
    }

    String getNom() {
        return this.Nom;
    }

    void afficherSolde(){
        System.out.println("Solde totale de vos comptes : "+this.getSolde());
    }

    public int retournerNombreDeCompte() {
        return this.NombreDeCompte;
    }

    void ajouterCompte(float soldeNouveauCompte) {
        if(this.NombreDeCompte<=100) {
            Compte nouveauComtpe = new Compte(soldeNouveauCompte);
            Comptes[NombreDeCompte]=nouveauComtpe;
            this.NombreDeCompte++;
        }
    }
}
