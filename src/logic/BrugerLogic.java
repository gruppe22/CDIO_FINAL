package logic;

import dao.BrugerDAO;
import dto.BrugerDTO;
import java.util.List;

public class BrugerLogic {
    private BrugerDAO dao = new BrugerDAO();

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
}
