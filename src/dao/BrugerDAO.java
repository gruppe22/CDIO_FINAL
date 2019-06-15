package dao;

import dto.BrugerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BrugerDAO implements IBrugerDAO {

    ConnectionManager connection = new ConnectionManager();

    @Override
    public BrugerDTO getBruger(int oprId) throws DALException {
        try (Connection c = connection.createConnection()) {
            BrugerDTO user = new BrugerDTO();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Bruger WHERE brugerId =?");
            ps.setInt(1,oprId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                user.setIni(rs.getString("ini"));
                user.setOprId(rs.getInt("brugerId"));
                user.setOprNavn(rs.getString("brugerNavn"));
                user.setCpr(rs.getString("cpr"));
                user.setRolle(rs.getString("rolle"));
            }
            rs.close();
            return user;
        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public List<BrugerDTO> getBrugerList() throws DALException {
        try (Connection c = connection.createConnection()) {
            List<BrugerDTO> userList = new ArrayList<>();

            Statement statement = c.createStatement();
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

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void createBruger(BrugerDTO opr) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("insert into Bruger values (?,?,?,?,?)");
            ps.setInt(1, opr.getOprId());
            ps.setString(2, opr.getOprNavn());
            ps.setString(3, opr.getIni());
            ps.setString(4, opr.getCpr());
            ps.setString(5, opr.getRolle());
            ps.execute();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void updateBruger(BrugerDTO opr) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE `Bruger` SET `brugerId`= ?,`brugerNavn`= ?,`ini` = ?, `cpr` =? , `rolle` =? WHERE `brugerId` = ?;");
            ps.setInt(1, opr.getOprId());
            ps.setString(2, opr.getOprNavn());
            ps.setString(3, opr.getIni());
            ps.setString(4,opr.getCpr());
            ps.setString(5, opr.getRolle());
            ps.setInt(6,opr.getOprId());
            ps.executeUpdate();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }


    @Override
    public void deleteBruger(BrugerDTO opr) throws DALException {
        try (Connection c = connection.createConnection()) {

            PreparedStatement ps = c.prepareStatement("DELETE FROM `Bruger` WHERE `brugerId` = ?");
            ps.setInt(1, opr.getOprId());
            ps.executeUpdate();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }
}
