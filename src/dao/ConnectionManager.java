package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager {

    String database = "cdio";
    String user = "cdio";
    String password = "chokoladekage22";

    public Connection createConnection() throws DALException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            return  DriverManager.getConnection("jdbc:mysql://anfran.dk:3306/"
                    + database + "?UseSSL=true&user=" + user + "&password=" + password);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new DALException("Database connection error!");
        }
    }

    class DALException extends Exception {
        public DALException(String message) {
            super(message); }
    }
}
