package dao;

import dto.ProduktBatchKompDTO;

import java.sql.*;
import java.util.List;

public class ProduktBatchKompDAO implements IProduktBatchKompDAO {
    private Connection createConnection() throws IProduktBatchKompDAO.DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://anfran.dk/cdio?"
                    + "user=cdio&password=chokoladekage22");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IProduktBatchKompDAO.DALException(e.getMessage());
        }
    }

    @Override
    public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
        return null;
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
        return null;
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
        return null;
    }

    @Override
    public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {

    }

    @Override
    public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {

    }
}
