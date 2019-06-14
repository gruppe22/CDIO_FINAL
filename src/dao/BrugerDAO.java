package dao;

import dto.BrugerDTO;

import java.sql.*;
import java.util.ArrayList;
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
        PreparedStatement ps = createConnection().prepareStatement("SELECT * FROM Bruger WHERE brugerId =?");
        ps.setInt(1,oprId);


        try {


            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                user.setIni(rs.getString("ini"));
                user.setOprId(rs.getInt("brugerId"));
                user.setOprNavn(rs.getString("brugerNavn"));
                user.setCpr(rs.getString("cpr"));
                user.setRolle(rs.getString("rolle"));
            } rs.close();
             } catch (SQLIntegrityConstraintViolationException ex){
            throw new DALException("Fejl ved oprettelse af bruger" +" "+ ex.getMessage());
            }catch (SQLException ex){
            throw new DALException(ex.getMessage());
            }
            return user;
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws DALException {
        try (Connection connection = createConnection()) {
            List<BrugerDTO> userList = new ArrayList<>();

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM `Bruger`");

            while (resultSet.next()) {
                // Setting up a New User DTO
                BrugerDTO bruger = new BrugerDTO();

                // All parameters
                bruger.setOprId(resultSet.getInt("brugerId"));
                bruger.setOprNavn(resultSet.getString("brugerNavn"));
                bruger.setIni(resultSet.getString("ini"));
                bruger.setCpr(resultSet.getString("cpr"));
                bruger.setRolle(resultSet.getString("rolle"));



                // Add user to list
                userList.add(bruger);
            }

            return userList;

        } catch (SQLException ex) {
            throw new DALException(ex.getMessage());
        }
    }






    @Override
    public void createBruger(BrugerDTO opr) throws DALException {
        
        try(Connection c = createConnection();) {
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
        try (Connection connection = createConnection()) {

            PreparedStatement statement = connection.prepareStatement("UPDATE `Bruger` SET `brugerId`= ?,`brugerNavn`= ?,`ini` = ?, `cpr` =? , `rolle` =? WHERE `brugerId` = ?;");
            statement.setInt(1, opr.getOprId());
            statement.setString(2, opr.getOprNavn());
            statement.setString(3, opr.getIni());
            statement.setString(4,opr.getCpr());
            statement.setString(5, opr.getRolle());
            statement.setInt(6,opr.getOprId());
            statement.executeUpdate();


        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }


    @Override
    public void deleteBruger(BrugerDTO opr) throws DALException {
        try (Connection connection = createConnection()) {

            PreparedStatement statement = connection.prepareStatement("DELETE FROM `Bruger` WHERE `brugerId` = ?");
            statement.setInt(1, opr.getOprId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DALException(e.getMessage());
        }
    }
}
