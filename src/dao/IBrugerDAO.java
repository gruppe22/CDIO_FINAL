package dao;

import dto.BrugerDTO;

import java.util.List;

public interface IBrugerDAO {
    BrugerDTO getBruger(int oprId) throws  DALException;
    List<BrugerDTO> getBrugerList() throws DALException;
    void createBruger(BrugerDTO opr) throws DALException;
    void updateBruger(BrugerDTO opr) throws DALException;
    void deleteBruger(BrugerDTO opr) throws DALException;

    class DALException extends Exception {
        public DALException(String message) {
            super(message); }
    }
}