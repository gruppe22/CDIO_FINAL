package logic;

import dao.*;
import dto.*;

public class VaegtLogic {
    private IProduktBatchDAO batchDAO = new ProduktBatchDAO();
    private IReceptDAO receptDAO = new ReceptDAO();

    public ReceptDTO getRecept(int receptId) throws Exception {
        try {
            return receptDAO.getRecept(receptId);
        } catch (Exception e) {
            throw new Exception(e);
        }
    }

    public ProduktBatchDTO getProduktBatch(int batchId) {
        try {
            return batchDAO.getProduktBatch(batchId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
