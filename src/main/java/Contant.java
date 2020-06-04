public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public boolean betaal(double tebetalen) {
        if(super.saldo >= tebetalen){
            super.saldo -= tebetalen;
        }
        return super.saldo >= tebetalen;
    }
}
