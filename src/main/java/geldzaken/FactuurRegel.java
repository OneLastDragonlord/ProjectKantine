package geldzaken;

import kantine.Artikel;

import javax.persistence.*;
import java.io.Serializable;
@Entity
@Table(name = "factuurregel")

public class FactuurRegel implements Serializable {

    @Id
    @GeneratedValue
    @Column(name = "regel_id", unique = true)
    private long id;

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

    public String toString(){
        return "a";
    }
}
