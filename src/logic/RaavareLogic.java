package logic;

import dao.RaavareBatchDAO;
import dao.RaavareDAO;
import dto.RaavareBatchDTO;
import dto.RaavareDTO;
import java.util.List;

public class RaavareLogic {
    private RaavareDAO raavareDAO = new RaavareDAO();
    private RaavareBatchDAO batchDAO = new RaavareBatchDAO();

    public RaavareDTO getRaavare(int id) throws Exception {
        try {
            RaavareDTO dto = raavareDAO.getRaavare(id);
            if (dto.getRaavareId() != 0)
                return dto;
            else
                return null;
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<RaavareDTO> getRaavareList() throws Exception {
        try {
            return raavareDAO.getRaavareList();
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<RaavareBatchDTO> getRaavareBatchList() throws Exception {
        try {
            return batchDAO.getRaavareBatchList();
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<RaavareBatchDTO> getRaavareBatchList(int batchId) throws Exception {
        try {
            return batchDAO.getRaavareBatchList(batchId);
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public RaavareDTO createRaavare(RaavareDTO raavare) throws Exception {

        if (raavare.getRaavareNavn().equals("") || raavare.getLeverandoer().equals(""))
            throw new Exception("Felterne må ikke være tomme");

        try {
            raavareDAO.createRaavare(raavare);
            return raavareDAO.getRaavare(raavare.getRaavareId());
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public RaavareBatchDTO createRaavareBatch(RaavareBatchDTO batch) throws Exception {
        try {
            batchDAO.createRaavareBatch(batch);
            return batchDAO.getRaavareBatch(batch.getRbId());
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public  RaavareDTO updateRaavare(RaavareDTO raavare) throws Exception {

        if (getRaavare(raavare.getRaavareId()) == null)
            throw new Exception("Raavare findes ikke");

        if (raavare.getRaavareNavn().equals("") || raavare.getLeverandoer().equals(""))
            throw new Exception("Felterne må ikke være tomme");

        try {
            raavareDAO.updateRaavare(raavare);
            return raavareDAO.getRaavare(raavare.getRaavareId());
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
