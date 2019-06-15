package dao;

import dto.BrugerDTO;
import dto.ProduktBatchDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktBatchDAO implements IProduktBatchDAO {

    ConnectionManager connection = new ConnectionManager();

    @Override
    public ProduktBatchDTO getProduktBatch(int pbId) throws DALException {
        try (Connection c = connection.createConnection()) {
            ProduktBatchDTO pb = new ProduktBatchDTO();
            PreparedStatement ps = connection.createConnection().prepareStatement("SELECT * FROM ProduktBatch WHERE pbId =?");
            ps.setInt(1,pbId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                pb.setPbId(rs.getInt("pbId"));
                pb.setReceptId(rs.getInt("receptId"));
                pb.setStatus(rs.getInt("status"));
                pb.setBrugerId(rs.getInt("brugerId"));
                pb.setRbId(rs.getInt("rbId"));
            }
            rs.close();
            return pb;
        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws DALException {
        try (Connection c = connection.createConnection()) {
            List<ProduktBatchDTO> pbList = new ArrayList<>();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM `ProduktBatch`");

            while (rs.next()) {
                // Setting up a New User DTO
                ProduktBatchDTO pb = new ProduktBatchDTO();

                // All parameters
                pb.setPbId(rs.getInt("pbId"));
                pb.setReceptId(rs.getInt("receptId"));
                pb.setStatus(rs.getInt("status"));
                pb.setBrugerId(rs.getInt("brugerId"));
                pb.setRbId(rs.getInt("rbId"));



                // Add user to list
                pbList.add(pb);
            }

            return pbList;

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void createProduktBatch(ProduktBatchDTO pb) throws DALException {
        try (Connection c = connection.createConnection()){
            PreparedStatement ps = c.prepareStatement("insert into ProduktBatch values (?,?,?,?,?)");
            ps.setInt(1, pb.getPbId());
            ps.setInt(2, pb.getReceptId());
            ps.setInt(3, pb.getStatus());
            ps.setInt(4, pb.getRbId());
            ps.setInt(5, pb.getStatus());
            ps.execute();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
     }

    @Override
    public void updateProduktBatch(ProduktBatchDTO pb) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE `Produktbatch` SET `pbId`= ?,`receptId`= ?,`status` = ?, `brugerId` =?, `rbId`=? WHERE `pbId` = ?;");
            ps.setInt(1, pb.getPbId());
            ps.setInt(2, pb.getReceptId());
            ps.setInt(3, pb.getStatus());
            ps.setInt(4, pb.getBrugerId());
            ps.setInt(5, pb.getRbId());
            ps.setInt(6, pb.getPbId());
            ps.executeUpdate();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }
}
