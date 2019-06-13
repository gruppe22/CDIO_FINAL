package dao;

import dto.BrugerDTO;

import java.util.List;

public interface IBrugerDAO {
    BrugerDTO getBruger(int oprId) throws  DALException;
    List<BrugerDTO> getBrugerList() throws DALException;
    void createBruger(BrugerDTO opr) throws DALException;
    void updateBruger(BrugerDTO opr) throws DALException;

    class DALException extends Exception {
        //Til Java serialisering...
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        DALException(String msg) {
            super(msg);
        }

    }
}