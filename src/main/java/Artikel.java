public class Artikel {
    private String naam;
    private double prijs;

    public Artikel(String naam, float prijs){
        this.naam = naam;
        this.prijs = prijs;
    }

    public Artikel(){
    }

    public void setNaam(String naam) {this.naam = naam;}

    public String getNaam() {return naam;}

    public void setPrijs(String naam) {this.naam = naam;}

    public double getPrijs() {return prijs;}
}