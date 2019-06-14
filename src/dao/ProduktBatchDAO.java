package dao;

import dto.ProduktBatchDTO;

import java.sql.*;
import java.util.List;

public class ProduktBatchDAO implements IProduktBatchDAO {

    private Connection createConnection() throws IProduktBatchDAO.DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://anfran.dk/cdio?"
                    + "user=cdio&password=chokoladekage22");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException(e.getMessage());
        }
    }
    @Override

    public ProduktBatchDTO getProduktBatch(int pbId) throws Exception {
        ProduktBatchDTO pb = new ProduktBatchDTO();
        PreparedStatement ps = createConnection().prepareStatement("SELECT * FROM ProduktBatch WHERE pbId =?");
        ps.setInt(1,pbId);

        try {
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                pb.setPbId(rs.getInt("pbId"));
                pb.setReceptId(rs.getInt("receptId"));
                pb.setStatus(rs.getInt("status"));
                pb.setBrugerId(rs.getInt("brugerId"));
                pb.setRbId(rs.getInt("rbId"));
            } rs.close();
        } catch (SQLIntegrityConstraintViolationException ex){
            throw new IProduktBatchDAO.DALException("Fejl ved hentning af produktbatch" +" "+ ex.getMessage());
        }catch (SQLException ex){
            throw new IProduktBatchDAO.DALException(ex.getMessage());
        }
        return pb;
    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws Exception {
        return null;
    }

    @Override
    public void createProduktBatch(ProduktBatchDTO pb) throws DALException {
        try (Connection c = createConnection()){
            PreparedStatement ps = c.prepareStatement("insert into ProduktBatch values (?,?,?,?,?)");
            ps.setInt(1, pb.getPbId());
            ps.setInt(2, pb.getReceptId());
            ps.setInt(3, pb.getStatus());
            ps.setInt(4, pb.getRbId());
            ps.setInt(5, pb.getStatus());
            ps.execute();

            } catch (SQLIntegrityConstraintViolationException ex){
                throw new IProduktBatchDAO.DALException("Fejl ved oprettelse af produktbatch:" +" "+ ex.getMessage());
            }catch (SQLException ex){
                throw new IProduktBatchDAO.DALException(ex.getMessage());
            }

        }

    @Override
    public void updateProduktBatch(ProduktBatchDTO pb) throws Exception {
        try (Connection c = createConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE `Produktbatch` SET `pbId`= ?,`receptId`= ?,`status` = ?, `brugerId` =?, `rbId`=? WHERE `pbId` = ?;");
            ps.setInt(1, pb.getPbId());
            ps.setInt(2, pb.getReceptId());
            ps.setInt(3, pb.getStatus());
            ps.setInt(4, pb.getBrugerId());
            ps.setInt(5, pb.getRbId());
            ps.setInt(6, pb.getPbId());
            ps.executeUpdate();


        } catch (SQLException e) {
            throw new IProduktBatchDAO.DALException(e.getMessage());
        }

    }
}
