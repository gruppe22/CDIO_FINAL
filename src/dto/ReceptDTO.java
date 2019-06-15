package dto;

public class ReceptDTO
{
    /** recept id i området 1-99999999 */
    int receptId;
    /** Receptnavn min. 2 max. 20 karakterer */
    String receptNavn;
    int raavareId;

    public ReceptDTO(int receptId, String receptNavn) {
        this.receptId = receptId;
        this.receptNavn = receptNavn;
        this.raavareId = raavareId;
    }

    public ReceptDTO(){}

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }

    public String getReceptNavn() {
        return receptNavn;
    }

    public void setReceptNavn(String receptNavn) {
        this.receptNavn = receptNavn;
    }

    public int getRaavareId(){return raavareId;}

    public void setRaavareId(int raavareId){this.raavareId = raavareId;}
}