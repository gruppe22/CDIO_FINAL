package dao;

import dto.RaavareDTO;

import java.util.List;

public interface IRaavareDAO {
    RaavareDTO getRaavare(int raavareId) throws DALException;
    List<RaavareDTO> getRaavareList() throws DALException;
    void createRaavare(RaavareDTO raavare) throws DALException;
    void updateRaavare(RaavareDTO raavare) throws DALException;

    class DALException extends Exception {
        public DALException(String message) {
            super(message); }
    }
}
