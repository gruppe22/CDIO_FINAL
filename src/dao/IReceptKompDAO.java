package dao;

import dto.ReceptKompDTO;

import java.util.List;

public interface IReceptKompDAO {
    ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException;
    List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException;
    List<ReceptKompDTO> getReceptKompList() throws DALException;
    void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException;
    void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException;

    class DALException extends Exception {
        //Til Java serialisering...
        private static final long serialVersionUID = 7355418246336739229L;

        public DALException(String msg, Throwable e) {
            super(msg,e);
        }

        public DALException(String msg) {
            super(msg);
        }

    }
}