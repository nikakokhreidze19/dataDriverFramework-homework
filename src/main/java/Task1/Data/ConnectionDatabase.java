package Task1.Data;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionDatabase {
    public static Connection getConnection()  {
        Connection connection = null;

        try (FileInputStream propFile = new FileInputStream("src/main/resources/db.properties")) {
            Properties properties = new Properties();
            properties.load(propFile);

            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");

            connection = DriverManager.getConnection(url, user, password);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }

}
