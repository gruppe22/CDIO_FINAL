package dao;

import dto.BrugerDTO;
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
        return null;
    }

    @Override
    public List<ProduktBatchDTO> getProduktBatchList() throws Exception {
        return null;
    }

    @Override
    public void createProduktBatch(ProduktBatchDTO pb) throws DALException {
        Connection c = createConnection();
        try {
            PreparedStatement ps = c.prepareStatement("insert into ProduktBatch values (?,?,?,?,?,?,?,?)");
            ps.setInt(1, pb.getPbId());
            ps.setInt(2, pb.getReceptId());
            ps.setInt(3, pb.getStatus());
            ps.setInt(4, pb.getReceptId());
            ps.setInt(5, pb.getStatus());
            ps.execute();
            c.close();

            } catch (SQLIntegrityConstraintViolationException ex){
                throw new IProduktBatchDAO.DALException("Fejl ved oprettelse af produktbatch");
            }catch (SQLException ex){
                throw new IProduktBatchDAO.DALException(ex.getMessage());
            }

        }

    @Override
    public void updateProduktBatch(ProduktBatchDTO produktbatch) throws Exception {

    }
}
