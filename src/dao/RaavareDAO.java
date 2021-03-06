package dao;

import dto.RaavareDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RaavareDAO implements IRaavareDAO {

    ConnectionManager connection = new ConnectionManager();

    @Override
    public RaavareDTO getRaavare(int raavareId) throws DALException {
        try (Connection c = connection.createConnection()) {
            RaavareDTO raavare = new RaavareDTO();
            PreparedStatement ps = c.prepareStatement("SELECT * FROM Raavare WHERE raavareId = ?");
            ps.setInt(1,raavareId);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                raavare.setRaavareId(rs.getInt("raavareId"));
                raavare.setRaavareNavn(rs.getString("raavareNavn"));
                raavare.setLeverandoer(rs.getString("leverandoer"));
            }
            rs.close();
            return raavare;
        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public List<RaavareDTO> getRaavareList() throws DALException {
        try (Connection c = connection.createConnection()) {
            List<RaavareDTO> raavareList = new ArrayList<>();

            Statement statement = c.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM Raavare");

            while (rs.next()) {
                // Setting up a New User DTO
                RaavareDTO raavare = new RaavareDTO();

                // All parameters
                raavare.setRaavareId(rs.getInt("raavareId"));
                raavare.setRaavareNavn(rs.getString("raavareNavn"));
                raavare.setLeverandoer(rs.getString("leverandoer"));

                // Add user to list
                raavareList.add(raavare);
            }

            return raavareList;

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void createRaavare(RaavareDTO raavare) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("INSERT INTO Raavare VALUES (?,?,?)");
            ps.setInt(1, raavare.getRaavareId());
            ps.setString(2, raavare.getRaavareNavn());
            ps.setString(3, raavare.getLeverandoer());
            ps.execute();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }

    @Override
    public void updateRaavare(RaavareDTO raavare) throws DALException {
        try (Connection c = connection.createConnection()) {
            PreparedStatement ps = c.prepareStatement("UPDATE Raavare SET raavareId = ?, raavareNavn = ?, leverandoer = ? WHERE raavareId = ?;");
            ps.setInt(1, raavare.getRaavareId());
            ps.setString(2, raavare.getRaavareNavn());
            ps.setString(3,raavare.getLeverandoer());
            ps.setInt(4, raavare.getRaavareId());
            ps.executeUpdate();

        } catch (SQLException | ConnectionManager.DALException ex){
            throw new DALException("Database fejl");
        }
    }
}
