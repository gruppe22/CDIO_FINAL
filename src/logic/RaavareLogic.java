package logic;

import dao.RaavareDAO;
import dto.RaavareDTO;
import java.util.List;

public class RaavareLogic {
    private RaavareDAO dao = new RaavareDAO();

    public RaavareDTO getRaavare(int id) throws Exception {
        try {
            RaavareDTO dto = dao.getRaavare(id);
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
            return dao.getRaavareList();
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public RaavareDTO createRaavare(RaavareDTO raavare) throws Exception {

        if (raavare.getRaavareNavn().equals("") || raavare.getLeverandoer().equals(""))
            throw new Exception("Felterne må ikke være tomme");

        try {
            dao.createRaavare(raavare);
            return dao.getRaavare(raavare.getRaavareId());
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
            dao.updateRaavare(raavare);
            return dao.getRaavare(raavare.getRaavareId());
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
