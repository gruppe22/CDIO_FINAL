package dao;

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
    public RaavareDTO getRaavare(int raavareId) throws DALException {
        return null;
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
            throw new IRaavareDAO.DALException("Fejl ved oprettelse af raavare");
        }catch (SQLException ex){
            throw new IRaavareDAO.DALException(ex.getMessage());
        }

    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws DALException {

    }
}
