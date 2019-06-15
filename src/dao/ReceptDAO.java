package dao;

import dto.ReceptDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReceptDAO implements IReceptDAO {

    private Connection createConnection() throws IReceptDAO.DALException {
        try {
            return DriverManager.getConnection("jdbc:mysql://anfran.dk/cdio?"
                    + "user=cdio&password=chokoladekage22");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IReceptDAO.DALException(e.getMessage());
        }
    }

        @Override
        public ReceptDTO getRecept ( int receptId) throws Exception {
            ReceptDTO recept = new ReceptDTO();
            PreparedStatement ps = createConnection().prepareStatement("SELECT * FROM Recept WHERE receptId =?");
            ps.setInt(1,receptId);

            try {
                ResultSet rs = ps.executeQuery();

                while(rs.next()) {
                    recept.setReceptId(rs.getInt("receptId"));
                    recept.setReceptNavn(rs.getString("receptNavn"));
                    recept.setRaavareId(rs.getInt("raavareId"));
                } rs.close();
            } catch (SQLIntegrityConstraintViolationException ex){
                throw new IReceptDAO.DALException("Fejl ved hentning af recept" +" "+ ex.getMessage());
            }catch (SQLException ex){
                throw new IReceptDAO.DALException(ex.getMessage());
            }
            return recept;
        }

        @Override
        public List<ReceptDTO> getReceptList () throws DALException {
            try (Connection c = createConnection()) {
                List<ReceptDTO> receptList = new ArrayList<>();

                Statement statement = c.createStatement();
                ResultSet rs = statement.executeQuery("SELECT * FROM Recept");

                while (rs.next()) {
                    // Setting up a New User DTO
                    ReceptDTO recept = new ReceptDTO();

                    // All parameters
                    recept.setReceptId(rs.getInt("receptId"));
                    recept.setReceptNavn(rs.getString("receptNavn"));
                    recept.setRaavareId(rs.getInt("raavareId"));

                    // Add user to list
                    receptList.add(recept);
                }

                return receptList;

            } catch (SQLException ex) {
                throw new IReceptDAO.DALException(ex.getMessage());
            }
        }

        @Override
        public void createRecept (ReceptDTO recept) throws DALException {
            try(Connection c = createConnection()) {
                PreparedStatement ps = c.prepareStatement("insert into Recept values (?,?,?)");
                ps.setInt(1, recept.getReceptId());
                ps.setString(2, recept.getReceptNavn());
                ps.setInt(3, recept.getRaavareId());
                ps.execute();
                c.close();

            } catch (SQLIntegrityConstraintViolationException ex){
                throw new IReceptDAO.DALException("Fejl ved oprettelse af recept:" +" "+ ex.getMessage());
            }catch (SQLException ex){
                throw new IReceptDAO.DALException(ex.getMessage());
            }
        }

        @Override
        public void updateRecept (ReceptDTO recept) throws DALException {
            try (Connection c = createConnection()) {
                PreparedStatement ps = c.prepareStatement("UPDATE `Recept` SET `receptId`= ?,`receptNavn`= ?,`raavareId` = ? WHERE `receptId` = ?;");
                ps.setInt(1, recept.getReceptId());
                ps.setString(2, recept.getReceptNavn());
                ps.setInt(3, recept.getRaavareId());
                ps.setInt(4, recept.getReceptId());
                ps.executeUpdate();


            } catch (SQLException e) {
                throw new IReceptDAO.DALException(e.getMessage());
            }
        }
    }



