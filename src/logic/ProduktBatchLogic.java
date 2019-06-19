package logic;

import dao.ProduktBatchDAO;
import dto.ProduktBatchDTO;
import java.util.List;

public class ProduktBatchLogic {
    private ProduktBatchDAO dao = new ProduktBatchDAO();

    public ProduktBatchDTO getProduktBatch(int id) throws Exception {
        try {
            ProduktBatchDTO dto = dao.getProduktBatch(id);
            if (dto.getPbId() != 0)
                return dto;
            else
                return null;
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<ProduktBatchDTO> getProduktBatchList() throws Exception {
        try {
            return dao.getProduktBatchList();
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public ProduktBatchDTO createProduktBatch(ProduktBatchDTO produktBatch) throws Exception {

        if (produktBatch.getStatus() < 0 || produktBatch.getStatus() > 2)
            throw new Exception("Status ikke gyldig");

        try {
            dao.createProduktBatch(produktBatch);
            return dao.getProduktBatch(produktBatch.getPbId());
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public ProduktBatchDTO updateProduktBatch(ProduktBatchDTO batch) throws Exception {
        if (getProduktBatch(batch.getPbId()) == null)
            throw new Exception("Batch findes ikke");

        if ((batch.getReceptId() < 0 && batch.getReceptId() >99999999) ||(batch.getStatus() < 0 && batch.getStatus()>9))
            throw new Exception("Felterne må ikke være tomme");

        try {
            dao.updateProduktBatch(batch);
            return batch;
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }


}
