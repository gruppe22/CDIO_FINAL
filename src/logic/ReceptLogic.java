package logic;

import dao.ReceptDAO;
import dao.ReceptKompDAO;
import dto.ReceptDTO;
import dto.ReceptKompDTO;

import java.util.List;

public class ReceptLogic {
    private ReceptDAO receptDAO = new ReceptDAO();
    private ReceptKompDAO kompDAO = new ReceptKompDAO();

    public ReceptDTO getRecept(int id) throws Exception {
        try {
            ReceptDTO dto = receptDAO.getRecept(id);
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
            return receptDAO.getReceptList();
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public ReceptDTO createRecept(ReceptDTO recept) throws Exception {

        if (recept.getReceptNavn().equals(""))
            throw new Exception("Felterne må ikke være tomme");

        try {
            receptDAO.createRecept(recept);
            return recept;
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public ReceptKompDTO createReceptKomp(ReceptKompDTO receptKomp) throws Exception {
        try {
            kompDAO.createReceptKomp(receptKomp);
            return receptKomp;
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public List<ReceptKompDTO> getReceptKompList(int id) throws Exception {
        try {
            return kompDAO.getReceptKompList(id);
        }
        catch (Exception ex) {
            throw new Exception(ex);
        }
    }

    public String getRaavareList() { // lav liste med raavare ud fra recept
    return null;}

    public String getRaavareNavn() { // snup et navn fra listen
    return null;}
}
