package geldzaken;

import geldzaken.Betaalwijze;
import geldzaken.TeWeinigGeldException;

public class Contant extends Betaalwijze {
    /**
     * Methode om betaling af te handelen
     */
    public void betaal(double tebetalen) throws TeWeinigGeldException {
        if (super.saldo >= tebetalen) {
            super.saldo -= tebetalen;
        } else {
            throw new TeWeinigGeldException("te weinig geld");
        }
//        return super.saldo >= tebetalen;
    }
}
