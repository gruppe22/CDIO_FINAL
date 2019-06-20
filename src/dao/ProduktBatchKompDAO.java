package dao;

import dto.ProduktBatchKompDTO;
import dto.ReceptKompDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProduktBatchKompDAO implements IProduktBatchKompDAO {

    ConnectionManager connection = new ConnectionManager();

    @Override
    public ProduktBatchKompDTO getProduktBatchKomp(int pbId, int rbId) throws DALException {
        return null;
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList(int pbId) throws DALException {
        try (Connection c = connection.createConnection()) {
            List<ProduktBatchKompDTO> pbkompList = new ArrayList<>();

            PreparedStatement ps = c.prepareStatement("SELECT * FROM ProduktBatch_has_RaavareBatch WHERE ProduktBatch_pbId = ?");
            ps.setInt(1, pbId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ProduktBatchKompDTO pbkomp = new ProduktBatchKompDTO();
                pbkomp.setRbId(rs.getInt("RaavareBatch_rbId"));
                pbkomp.setPbId(rs.getInt("ProduktBatch_pbId"));
                pbkomp.setTara(rs.getDouble("tara"));
                pbkomp.setNetto(rs.getDouble("netto"));
                pbkomp.setOprId(rs.getInt("opr.Id"));
                pbkompList.add(pbkomp);
            }
            return pbkompList;

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new IProduktBatchKompDAO.DALException("Database fejl");
        }
    }

    @Override
    public List<ProduktBatchKompDTO> getProduktBatchKompList() throws DALException {
        return null;
    }

    @Override
    public void createProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO ProduktBatch_has_RaavareBatch VALUES (?,?,?,?)");
            ps.setInt(1, produktbatchkomponent.getRbId());
            ps.setInt(2, produktbatchkomponent.getPbId());
            ps.setDouble(3, produktbatchkomponent.getTara());
            ps.setDouble(4, produktbatchkomponent.getNetto());
            ps.execute();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void updateProduktBatchKomp(ProduktBatchKompDTO produktbatchkomponent) throws DALException {

    }
}
