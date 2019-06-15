package logic;

import dao.BrugerDAO;
import dao.IBrugerDAO;
import dto.BrugerDTO;
import java.util.List;

public class BrugerLogic {
    private IBrugerDAO dao = new BrugerDAO();

    public BrugerDTO getBruger(int id) throws Exception {
        try {
            return dao.getBruger(id);
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
        try {
            dao.createBruger(user);
            return dao.getBruger(user.getOprId());
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
