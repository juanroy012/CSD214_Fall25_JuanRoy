package Lab1.pojos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JConnection {

    public Connection connection;

    public void jdbcConnection() {
        String url = "jdbc:mysql://localhost:3333/csd214-lab1";
        String user = "csd214";
        String password = "itstudies12345";

        try {
            connection = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            System.out.println("JDBC connection failed!");
        }
    }
}
