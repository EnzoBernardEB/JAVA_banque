public class Launcher {

    public static void main(String[] args) {
        Banque banque = new Banque();
        InteractionBanque menuBanque = new InteractionBanque(banque);
        menuBanque.interagirBanque();

    }
}
