package dto;

import java.util.List;

public class BrugerDTO {
    /**
     * Bruger id i området 1-99999999. Vælges af brugerne
     */
    int oprId;
    /**
     * Bruger navn (opr_navn) min. 2 max. 20 karakterer
     */
    String oprNavn;
    /**
     * Bruger initialer min. 2 max. 4 karakterer
     */
    String ini;
    /**
     * Bruger cpr-nr 10 karakterer
     */
    String cpr;
    /**
     * Liste over roller
     */
    String rolle;

    boolean status;

    public BrugerDTO(int id, String oprNavn, String ini, String cpr, String rolle, boolean status) {
        this.oprId = id;
        this.oprNavn = oprNavn;
        this.ini = ini;
        this.cpr = cpr;
        this.rolle = rolle;
        this.status = status;
    }

    public BrugerDTO() {    }


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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        String bruger = ("ID:" + " " + oprId + invisibleLine()+
                "Navn:" + " " + oprNavn + invisibleLine()+
                "Ini:" + " " + ini +invisibleLine()+
                "CPR:" + " " + cpr +invisibleLine()+
                "Rolle:" + " " + rolle +invisibleLine() +
                "Status:" + " " + status);

        return bruger;
    }
    String invisibleLine()
    {
        StringBuilder sb = new StringBuilder(20);
        for(int n = 0; n < 20; ++n)
            sb.append(' ');
        sb.append(System.lineSeparator());
        return sb.toString();
    }
}
