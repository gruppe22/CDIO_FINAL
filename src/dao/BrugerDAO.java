package dao;

import dto.BrugerDTO;

import java.sql.*;
import java.util.List;

public class BrugerDAO implements IBrugerDAO {


    private Connection createConnection() throws DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://anfran.dk"
                    + "user=cdio&password=chokoladekage22");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException(e.getMessage());
        }
    }
    @Override
    public BrugerDTO getBruger(int oprId) throws DALException {
        return null;
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws DALException {
        return null;
    }

    @Override
    public void createBruger(BrugerDTO opr) throws DALException {
        Connection c = createConnection();
        try {
            PreparedStatement ps = c.prepareStatement("insert into Bruger values (?,?,?,?,?,?)");
            ps.setInt(1, opr.getOprId());
            ps.setString(2, opr.getOprNavn());
            ps.setString(3, opr.getIni());
            ps.setString(4, opr.getCpr());
            ps.setString(5, opr.getRolle());
            ps.execute();
            c.close();

        } catch (SQLIntegrityConstraintViolationException ex){
            throw new DALException("Fejl ved oprettelse af bruger");
        }catch (SQLException ex){
            throw new DALException(ex.getMessage());
        }

    }

    @Override
    public void updateBruger(BrugerDTO opr) throws DALException {

    }
}
