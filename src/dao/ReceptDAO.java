package dao;

import dto.ReceptDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceptDAO implements IReceptDAO {

    ConnectionManager connection = new ConnectionManager();

    @Override
    public ReceptDTO getRecept ( int receptId) throws DALException {
        try (Connection c = connection.createConnection()) {
            ReceptDTO recept = new ReceptDTO();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Recept WHERE receptId =?");
            ps.setInt(1,receptId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                recept.setReceptId(rs.getInt("receptId"));
                recept.setReceptNavn(rs.getString("receptNavn"));
            }
            rs.close();
            return recept;
        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public List<ReceptDTO> getReceptList () throws DALException {
        try (Connection c = connection.createConnection()) {
            List<ReceptDTO> receptList = new ArrayList<>();

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
    }

    @Override
    public void createRecept (ReceptDTO recept) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("insert into Recept values (?,?)");
            ps.setInt(1, recept.getReceptId());
            ps.setString(2, recept.getReceptNavn());
            ps.execute();
            c.close();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void updateRecept (ReceptDTO recept) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE `Recept` SET `receptId`= ?,`receptNavn`= ? WHERE `receptId` = ?;");
            ps.setInt(1, recept.getReceptId());
            ps.setString(2, recept.getReceptNavn());
            ps.setInt(3, recept.getReceptId());
            ps.executeUpdate();


        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }
}



