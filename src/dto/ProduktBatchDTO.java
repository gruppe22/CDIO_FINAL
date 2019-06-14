package dto;

public class ProduktBatchDTO {
    /** produkt batch id i området 1-99999999. Vælges af brugerne */
    int pbId;
    /** status 0: ikke påbegyndt, 1: under produktion, 2: afsluttet */
    int status;
    /** recept id i området 1-99999999. Vælges af brugerne */
    int receptId;
    int rbId;

    public ProduktBatchDTO(int pbId, int status, int receptId, int rbId) {
        this.pbId = pbId;
        this.status = status;
        this.receptId = receptId;
        this.rbId = rbId;
    }

    public int getPbId() {
        return pbId;
    }

    public void setPbId(int pbId) {
        this.pbId = pbId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getReceptId() {
        return receptId;
    }

    public void setReceptId(int receptId) {
        this.receptId = receptId;
    }

    public int getRbId(){
        return rbId;
    }

    public void setRbId(int rbId){
        this.rbId = rbId;
    }
}
