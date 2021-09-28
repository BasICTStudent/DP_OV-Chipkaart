package domain;

public class Adres {
    private int adresId;
    private String postcode;
    private String huisnummer;
    private String straat;
    private String woonplaats;

    private int reizigerId;


    public Adres(int adresId, String postcode, String huisnummer, String straat, String woonplaats, int reizigerId) {
        this.adresId = adresId;
        this.postcode = postcode;
        this.huisnummer = huisnummer;
        this.straat = straat;
        this.woonplaats = woonplaats;
        this.reizigerId = reizigerId;
    }

    public int getAdresId() {
        return adresId;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getHuisnummer() {
        return huisnummer;
    }

    public String getStraat() {
        return straat;
    }

    public String getWoonplaats() {
        return woonplaats;
    }

    public int getReizigerId() {
        return reizigerId;
    }

    public String toString() {
        return "Adres{" +
                "adres_id=" + adresId +
                ", postcode='" + postcode + '\'' +
                ", huisnummer='" + huisnummer + '\'' +
                ", straat='" + straat + '\'' +
                ", woonplaats='" + woonplaats + '\'' +
                ", reiziger_id=" + reizigerId +
                '}';
    }
}
