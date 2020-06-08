package geldzaken;

public class Pinpas extends Betaalwijze {

    private double kredietlimiet;

    /**
     * Methode om kredietlimiet te zetten
     *
     * @param kredietlimiet
     */
    public void setKredietLimiet(double kredietlimiet) {
        this.kredietlimiet = kredietlimiet;
    }

    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        if ((super.saldo - kredietlimiet) >= tebetalen) {
            super.saldo -= tebetalen;
        } else {
            throw new TeWeinigGeldException("arm");
        }
//        return (super.saldo - kredietlimiet) >= tebetalen;
    }
}
