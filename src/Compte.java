public class Compte {
    private long Id;
    private float Solde;

    public Compte(float solde) {
        this.Solde = solde;
    }

    void depot(float somme) {
        this.Solde = this.Solde + somme;
    }

    void retrait(float somme) {
        this.Solde = this.Solde - somme;
    }

    float getSolde() {
        return this.Solde;
    }

    long getId() {
        return this.Id;
    }

    void afficherSolde() {
        System.out.println(this.getSolde());
    }

    void virerA(float somme, Compte destinataire) {
        destinataire.depot(somme);
    }
}
