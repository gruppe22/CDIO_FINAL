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
        public DALException(String message) {
            super(message); }
    }
}