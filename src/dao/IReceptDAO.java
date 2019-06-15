package dao;

import dto.ReceptDTO;

import java.util.List;

public interface IReceptDAO {
    ReceptDTO getRecept(int receptId) throws DALException;
    List<ReceptDTO> getReceptList() throws DALException;
    void createRecept(ReceptDTO recept) throws DALException;
    void updateRecept(ReceptDTO recept) throws DALException;

    class DALException extends Exception {
        public DALException(String message) {
            super(message); }
    }
}