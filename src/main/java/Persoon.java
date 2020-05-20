public class Persoon {
    private int BSN;
    private String voornaam;
    private String achternaam;
    private Datum geboorteDatum;
    private char geslacht;

    public Persoon(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht) {
        this.BSN = BSN;
        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboorteDatum = geboorteDatum;
        setGeslacht(geslacht);
    }

    public Persoon() {
        this.geslacht= 'O';
        this.geboorteDatum = null;
        getGeslacht();
        getGeboorteDatum();
    }

    public int getBSN() {return BSN;}

    public void setBSN(int BSN) {this.BSN = BSN;}

    public String getVoornaam() {return voornaam;}

    public void setVoornaam(String voornaam) {this.voornaam = voornaam;}

    public String getAchternaam() {return achternaam;}

    public void setAchternaam(String achternaam) {this.achternaam = achternaam;}

    public String getGeboorteDatum() {return geboorteDatum!= null?geboorteDatum.getDatumAsString():"Onbekend";}

    public void setGeboorteDatum(Datum geboorteDatum) {this.geboorteDatum = geboorteDatum;}

    public String getGeslacht() {
        switch(geslacht) {
            case 'M':
                return "Man";
            case 'V':
                return "Vrouw";
            default:
                return "Onbekend";
        }
    }

    public void setGeslacht(char geslacht) {
        if (geslacht=='M' || geslacht=='V'){
            this.geslacht = geslacht;
        } else{
            System.out.println("geslacht bestaat niet");
            this.geslacht = 'O';
        }
    }

    /*public boolean isGeslacht(char geslacht){
        if(geslacht == 'M'|| geslacht== 'V' ){
            return true;
        } else {
            System.out.println("geslacht bestaat niet");
            return false;
        }
    }*/

    public String toString(){
        return getBSN()+", "+ getVoornaam()+", "+ getAchternaam()+", "+ getGeboorteDatum()+", "+ getGeslacht();
    }
}
