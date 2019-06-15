package dao;

import dto.RaavareBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaavareBatchDAO implements IRaavareBatchDAO {

    ConnectionManager connection = new ConnectionManager();

    @Override
    public RaavareBatchDTO getRaavareBatch ( int rbId) throws DALException {
        try (Connection c = connection.createConnection()) {
            RaavareBatchDTO rb = new RaavareBatchDTO();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM RaavareBatch WHERE rbId =?");
            ps.setInt(1,rbId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                rb.setRbId(rs.getInt("rbId"));
                rb.setRaavareId(rs.getInt("raavareId"));
                rb.setMaengde(rs.getDouble("maengde"));
            }
            rs.close();
            return rb;
        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public List<RaavareBatchDTO> getRaavareBatchList () throws DALException {
        try (Connection c = connection.createConnection()) {
            List<RaavareBatchDTO> rbList = new ArrayList<>();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `RaavareBatch`");

            while (rs.next()) {
                // Setting up a New User DTO
                RaavareBatchDTO rb = new RaavareBatchDTO();

                // All parameters
                rb.setRbId(rs.getInt("rbId"));
                rb.setRaavareId(rs.getInt("raavareId"));
                rb.setMaengde(rs.getDouble("maengde"));



                // Add user to list
                rbList.add(rb);
            }

            return rbList;

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public List<RaavareBatchDTO> getRaavareBatchList ( int raavareId) throws DALException {
        try (Connection c = connection.createConnection()) {
            List<RaavareBatchDTO> rbList = new ArrayList<>();

            PreparedStatement ps = c.prepareStatement("SELECT * FROM `RaavareBatch` WHERE `raavareId` =?");
            ps.setInt(1, raavareId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Setting up a New User DTO
                RaavareBatchDTO rb = new RaavareBatchDTO();

                // All parameters
                rb.setRbId(rs.getInt("rbId"));
                rb.setRaavareId(rs.getInt("raavareId"));
                rb.setMaengde(rs.getDouble("maengde"));

                // Add user to list
                rbList.add(rb);
            }

            return rbList;

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void createRaavareBatch (RaavareBatchDTO raavarebatch) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("insert into RaavareBatch values (?,?,?)");
            ps.setInt(1, raavarebatch.getRbId());
            ps.setInt(2, raavarebatch.getRaavareId());
            ps.setDouble(3, raavarebatch.getMaengde());
            ps.execute();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void updateRaavareBatch (RaavareBatchDTO raavarebatch) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE `RaavareBatch` SET `rbId`= ?,`RaavareId`= ?,`maengde` = ? WHERE `rbId` = ?;");
            ps.setInt(1, raavarebatch.getRbId());
            ps.setInt(2, raavarebatch.getRaavareId());
            ps.setDouble(3, raavarebatch.getMaengde());
            ps.setInt(4,raavarebatch.getRbId());
            ps.executeUpdate();


        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }
}

