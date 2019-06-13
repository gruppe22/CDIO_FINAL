package dto;

import java.util.List;

public class BrugerDTO
{
    /** Bruger id i området 1-99999999. Vælges af brugerne */
    int oprId;
    /** Bruger navn (opr_navn) min. 2 max. 20 karakterer */
    String oprNavn;
    /** Bruger initialer min. 2 max. 4 karakterer */
    String ini;
    /** Bruger cpr-nr 10 karakterer */
    String cpr;
    /** Liste over roller */
    String rolle;

    public BrugerDTO(int id, String oprNavn, String ini, String cpr, String rolle){
        this.oprId = id;
        this.oprNavn = oprNavn;
        this.ini = ini;
        this.cpr = cpr;
        this.rolle = rolle;
    }

    public int getOprId() {
        return oprId;
    }

    public void setOprId(int oprId) {
        this.oprId = oprId;
    }

    public String getOprNavn() {
        return oprNavn;
    }

    public void setOprNavn(String oprNavn) {
        this.oprNavn = oprNavn;
    }

    public String getIni() {
        return ini;
    }

    public void setIni(String ini) {
        this.ini = ini;
    }

    public String getCpr() {
        return cpr;
    }

    public void setCpr(String cpr) {
        this.cpr = cpr;
    }

    public String getRolle() {
        return rolle;
    }

    public void setRolle(String rolle) {
        this.rolle = rolle;
    }
}
