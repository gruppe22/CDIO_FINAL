package logic;

import dao.BrugerDAO;
import dao.IBrugerDAO;
import dto.BrugerDTO;
import java.util.List;

public class BrugerLogic {
    private IBrugerDAO dao = new BrugerDAO();

    public BrugerDTO getBruger(int id) throws Exception {
        try {
            BrugerDTO dto = dao.getBruger(id);
            if (dto.getOprId() != 0)
                return dto;
            else
                throw new Exception("Bruger findes ikke.");
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<BrugerDTO> getBrugerList() throws Exception {
        try {
            return dao.getBrugerList();
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public BrugerDTO createBruger(BrugerDTO user) throws Exception {

        if (user.getOprNavn().equals("") || user.getIni().equals("") || user.getCpr().equals("") ||user.getRolle().equals(""))
            throw new Exception("Felterne må ikke være tomme");

        try {
            dao.createBruger(user);
            return user;
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public  BrugerDTO updateBruger(BrugerDTO user) throws Exception {

        if (getBruger(user.getOprId()) == null)
            throw new Exception("Brugeren findes ikke");

        if (user.getOprNavn().equals("") || user.getIni().equals("") || user.getCpr().equals("") ||user.getRolle().equals(""))
            throw new Exception("Felterne må ikke være tomme");

        try {
            dao.updateBruger(user);
            return user;
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
