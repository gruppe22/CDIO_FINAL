import dao.BrugerDAO;
import dao.IBrugerDAO;
import dto.BrugerDTO;

public class Main {
    public static void main(String[] args) throws IBrugerDAO.DALException {
        BrugerDTO Kasper = new BrugerDTO(1,"Kasper","KB","251088-2935","Administrator");
        BrugerDAO DAO = new BrugerDAO();

        DAO.createBruger(Kasper);
    }
}
