package dao;

import dto.ReceptKompDTO;

import java.sql.*;
import java.util.List;

public class ReceptKompDAO implements IReceptKompDAO {

    private Connection createConnection() throws IReceptKompDAO.DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://anfran.dk/cdio?"
                    + "user=cdio&password=chokoladekage22");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IReceptKompDAO.DALException(e.getMessage());
        }
    }

        @Override
        public ReceptKompDTO getReceptKomp ( int receptId, int raavareId) throws DALException {
            return null;
        }

        @Override
        public List<ReceptKompDTO> getReceptKompList ( int receptId) throws DALException {
            return null;
        }

        @Override
        public List<ReceptKompDTO> getReceptKompList () throws DALException {
            return null;
        }

        @Override
        public void createReceptKomp (ReceptKompDTO receptkomponent) throws DALException {
        }

        @Override
        public void updateReceptKomp (ReceptKompDTO receptkomponent) throws DALException {

        }
    }
