package dao;

import dto.BrugerDTO;

import java.sql.*;
import java.util.List;

public class BrugerDAO implements IBrugerDAO {

    private Connection createConnection() throws DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://anfran.dk/cdio?"
                    + "user=cdio&password=chokoladekage22");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DALException(e.getMessage());
        }
    }
    @Override
    public BrugerDTO getBruger(int oprId) throws Exception {
        BrugerDTO user = new BrugerDTO();
        String sql = "SELECT * FROM Bruger WHERE brugerId =";

        try {
            Connection c = createConnection();
            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery(sql+oprId);

            while(rs.next()) {
                user.setIni(rs.getString("ini"));
                user.setOprId(rs.getInt("brugerId"));
                user.setOprNavn(rs.getString("brugerNavn"));
                user.setCpr(rs.getString("cpr"));
                user.setRolle(rs.getString("rolle"));
            } rs.close(); c.close();
             } catch (SQLIntegrityConstraintViolationException ex){
            throw new DALException("Fejl ved oprettelse af bruger" +" "+ ex.getMessage());
            }catch (SQLException ex){
            throw new DALException(ex.getMessage());
            }
            return user;
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws DALException {
        return null;
    }

    @Override
    public void createBruger(BrugerDTO opr) throws DALException {
        Connection c = createConnection();
        try {
            PreparedStatement ps = c.prepareStatement("insert into Bruger values (?,?,?,?,?)");
            ps.setInt(1, opr.getOprId());
            ps.setString(2, opr.getOprNavn());
            ps.setString(3, opr.getIni());
            ps.setString(4, opr.getCpr());
            ps.setString(5, opr.getRolle());
            ps.execute();
            c.close();

        } catch (SQLIntegrityConstraintViolationException ex){
            throw new DALException("Fejl ved oprettelse af bruger:" +" "+ ex.getMessage());
        }catch (SQLException ex){
            throw new DALException(ex.getMessage());
        }

    }

    @Override
    public void updateBruger(BrugerDTO opr) throws DALException {

    }
}
