package dao;

import dto.ReceptKompDTO;
import java.sql.*;
import java.util.List;

public class ReceptKompDAO implements IReceptKompDAO {

    ConnectionManager connection = new ConnectionManager();

    @Override
    public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
        return null;
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
        /*
        try (Connection c = connection.createConnection()) {
            List<ReceptKompDTO> receptList = new ArrayList<>();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Recept");

            while (rs.next()) {
                // Setting up a New User DTO
                ReceptDTO recept = new ReceptDTO();

                // All parameters
                recept.setReceptId(rs.getInt("receptId"));
                recept.setReceptNavn(rs.getString("receptNavn"));

                // Add user to list
                receptList.add(recept);
            }

            return receptList;
        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
        */
        return null;
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList() throws DALException {
        return null;
    }

    @Override
    public void createReceptKomp(ReceptKompDTO receptkomponent) throws DALException {

    }

    @Override
    public void updateReceptKomp(ReceptKompDTO receptkomponent) throws DALException {

    }
}
