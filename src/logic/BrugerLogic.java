package logic;

import dao.BrugerDAO;
import dto.BrugerDTO;

public class BrugerLogic {
    private BrugerDAO dao = new BrugerDAO();

    public BrugerDTO getBruger(int id) throws Exception {
        BrugerDTO user = new BrugerDTO();
        try {
            user = dao.getBruger(id);
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
        return user;
    }
}
