package tests;

import dao.BrugerDAO;
import dao.IBrugerDAO;
import dto.BrugerDTO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BrugerDAOTest {

    // Setup
    BrugerDTO Kasper = new BrugerDTO(100, "Kasper", "KB", "2510882134", "Administrator");
    BrugerDTO Hans = new BrugerDTO(101, "Hans Henning", "HH", "1903995783", "Pharmaceut");
    BrugerDTO Ray = new BrugerDTO(102, "Ray Charles", "SHG", "1901565234", "Laborant");
    IBrugerDAO dao = new BrugerDAO();





    @org.junit.jupiter.api.Test
    void createBrugerAndGetBruger() throws Exception {
        try {

            // setup
            dao.createBruger(Kasper);
            dao.createBruger(Hans);
            dao.createBruger(Ray);
        } catch (IBrugerDAO.DALException e) {
            e.printStackTrace();
        }
        // Check if users are in DB and have expected ID
        assertEquals(Kasper.getOprId(),dao.getBruger(1).getOprId());
        assertEquals(Hans.getOprId(),dao.getBruger(2).getOprId());
        assertEquals(Ray.getOprId(),dao.getBruger(3).getOprId());

        // teardown

        dao.deleteBruger(Kasper);
        dao.deleteBruger(Hans);
        dao.deleteBruger(Ray);
    }


    @org.junit.jupiter.api.Test
    void getBrugerList() throws IBrugerDAO.DALException {

        // setup
        dao.createBruger(Kasper);
        dao.createBruger(Hans);
        dao.createBruger(Ray);
        // Test if expected amount of users are added to list
        List<BrugerDTO> brugerDTOliste ;
        brugerDTOliste = dao.getBrugerList();

        assertSame(brugerDTOliste.size(),3);

        // teardown
        dao.deleteBruger(Kasper);
        dao.deleteBruger(Hans);
        dao.deleteBruger(Ray);
    }



    @org.junit.jupiter.api.Test
    void updateBruger() throws Exception {
        // setup
        dao.createBruger(Kasper);
        dao.createBruger(Hans);
        dao.createBruger(Ray);
        Kasper.setOprNavn("Kasper1");
        Hans.setOprNavn("Hans1");
        Ray.setOprNavn("Ray1");

        dao.updateBruger(Kasper);
        dao.updateBruger(Hans);
        dao.updateBruger(Ray);

        assertEquals(Kasper.getOprNavn(),dao.getBruger(1).getOprNavn());
        assertEquals(Hans.getOprNavn(),dao.getBruger(2).getOprNavn());
        assertEquals(Ray.getOprNavn(),dao.getBruger(3).getOprNavn());

        // teardown
        dao.deleteBruger(Kasper);
        dao.deleteBruger(Hans);
        dao.deleteBruger(Ray);
    }

    @org.junit.jupiter.api.Test
    void deleteBruger() throws IBrugerDAO.DALException {
        // setup
        dao.createBruger(Kasper);
        dao.createBruger(Hans);
        dao.createBruger(Ray);
        try {
            dao.deleteBruger(Kasper);
            dao.deleteBruger(Hans);
            dao.deleteBruger(Ray);
        } catch (IBrugerDAO.DALException e) {
            e.printStackTrace();
        }
        assertSame(0,dao.getBrugerList().size());
    }


}