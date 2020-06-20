package geldzaken;

import kantine.Artikel;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "factuurregel")
public class FactuurRegel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "regel_id", unique = true)
    private long factuurRegelId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "factuur")
    private Factuur factuur;

    @Embedded
    private Artikel artikel;

    public FactuurRegel(){}

    public FactuurRegel(Factuur factuur, Artikel artikel){
        this.factuur = factuur;
        this.artikel = artikel;
    }

    @Override
    public String toString(){
        return "\nArtikel: "+artikel.getNaam() + ", Prijs: "+artikel.getPrijs();
    }



}
