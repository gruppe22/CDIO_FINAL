package logic;

import dao.ReceptDAO;
import dto.ReceptDTO;
import java.util.List;

public class ReceptLogic {
    private ReceptDAO dao = new ReceptDAO();

    public ReceptDTO getRecept(int id) throws Exception {
        try {
            ReceptDTO dto = dao.getRecept(id);
            if (dto.getReceptId() != 0)
                return dto;
            else
                return null;
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<ReceptDTO> getReceptList() throws Exception {
        try {
            return dao.getReceptList();
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public ReceptDTO createRecept(ReceptDTO recept) throws Exception {

        if (recept.getReceptNavn().equals(""))
            throw new Exception("Felterne må ikke være tomme");

        try {
            dao.createRecept(recept);
            return recept;
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }
}
