package persoon;

import utility.Datum;

public class Student extends Persoon {
    private int studentNummer;
    private String studieRichting;

    public Student(int BSN, String voornaam, String achternaam, Datum geboorteDatum, char geslacht, int studentNummer, String studieRichting) {
        super(BSN, voornaam, achternaam, geboorteDatum, geslacht);
        this.studentNummer = studentNummer;
        this.studieRichting = studieRichting;
    }

    public Student(int studentNummer, String studieRichting) {
        this.studentNummer = studentNummer;
        this.studieRichting = studieRichting;
    }

    public Student() {
        super();
    }

    public int getStudentNummer() {
        return studentNummer;
    }

    public void setStudentNummer(int studentNummer) {
        this.studentNummer = studentNummer;
    }

    public String getStudieRichting() {
        return studieRichting;
    }

    public void setStudieRichting(String studieRichting) {
        this.studieRichting = studieRichting;
    }
}
