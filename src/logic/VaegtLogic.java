package logic;

import dao.*;
import dto.*;

public class VaegtLogic {
    private IProduktBatchDAO batchDAO = new ProduktBatchDAO();
    private IReceptDAO receptDAO = new ReceptDAO();

    public ReceptDTO getRecept(int receptId) {
        try {
            return receptDAO.getRecept(receptId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
