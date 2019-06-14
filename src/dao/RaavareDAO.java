package dao;

import dto.BrugerDTO;
import dto.RaavareDTO;

import java.sql.*;
import java.util.List;

public class RaavareDAO implements IRaavareDAO {
    private Connection createConnection() throws IRaavareDAO.DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://anfran.dk/cdio?"
                    + "user=cdio&password=chokoladekage22");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException(e.getMessage());
        }
    }

    @Override
    public RaavareDTO getRaavare(int raavareId) throws Exception {
        RaavareDTO raavare = new RaavareDTO();
        PreparedStatement ps = createConnection().prepareStatement("SELECT * FROM Raavare WHERE raavareId =?");
        ps.setInt(1,raavareId);

        try {
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                raavare.setRaavareId(rs.getInt("raavareId"));
                raavare.setRaavareNavn(rs.getString("raavareNavn"));
            } rs.close();
        } catch (SQLIntegrityConstraintViolationException ex){
            throw new IBrugerDAO.DALException("Fejl ved hentning af raavare" +" "+ ex.getMessage());
        }catch (SQLException ex){
            throw new IBrugerDAO.DALException(ex.getMessage());
        }
        return raavare;
    }

    @Override
    public List<RaavareDTO> getRaavareList() throws DALException {
        return null;
    }

    @Override
    public void createRaavare(RaavareDTO raavare) throws DALException {
        Connection c = createConnection();
        try {
            PreparedStatement ps = c.prepareStatement("insert into Raavare values (?,?)");
            ps.setInt(1, raavare.getRaavareId());
            ps.setString(2, raavare.getRaavareNavn());
            ps.execute();
            c.close();

        } catch (SQLIntegrityConstraintViolationException ex){
            throw new IRaavareDAO.DALException("Fejl ved oprettelse af raavare:" + " " + ex.getMessage());
        }catch (SQLException ex){
            throw new IRaavareDAO.DALException(ex.getMessage());
        }

    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws DALException {
        try (Connection c = createConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE `Raavare` SET `raavareId`= ?,`raavareNavn`= ? WHERE `raavareId` = ?;");
            ps.setInt(1, raavare.getRaavareId());
            ps.setString(2, raavare.getRaavareNavn());
            ps.setInt(3, raavare.getRaavareId());
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new IRaavareDAO.DALException(e.getMessage());
        }

    }
}
