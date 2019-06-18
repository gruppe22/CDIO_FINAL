package dao;

import dto.ReceptKompDTO;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceptKompDAO implements IReceptKompDAO {

    ConnectionManager connection = new ConnectionManager();

    @Override
    public ReceptKompDTO getReceptKomp(int receptId, int raavareId) throws DALException {
        return null;
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList(int receptId) throws DALException {
        try (Connection c = connection.createConnection()) {
            List<ReceptKompDTO> receptList = new ArrayList<>();

            PreparedStatement ps = c.prepareStatement("SELECT * FROM Recept_has_Raavare WHERE Recept_receptId = ?");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                ReceptKompDTO komp = new ReceptKompDTO();
                komp.setReceptId(rs.getInt("Recept_receptId"));
                komp.setRaavareId(rs.getInt("Raavare_raavareId"));
                komp.setNomNetto(rs.getDouble("nomNetto"));
                komp.setTolerance(rs.getDouble("tolerance"));
                receptList.add(komp);
            }
            return receptList;
        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public List<ReceptKompDTO> getReceptKompList() throws DALException {
        return null;
    }

    @Override
    public void createReceptKomp(ReceptKompDTO komponent) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("insert into Recept_has_Raavare values (?,?,?,?)");
            ps.setInt(1, komponent.getReceptId());
            ps.setInt(2, komponent.getRaavareId());
            ps.setDouble(3, komponent.getNomNetto());
            ps.setDouble(4, komponent.getTolerance());
            ps.execute();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void updateReceptKomp(ReceptKompDTO komponent) throws DALException {

    }
}
