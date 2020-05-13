package org.fasttrackit.persistence;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConfiguration {

    public static Connection getConnection () throws SQLException, IOException {

        InputStream inputstream = DatabaseConfiguration.class.getClassLoader().getResourceAsStream("db.properties");

        if (inputstream == null) {
            throw new RuntimeException("Failed to read db.properties file.");
        }

        try {
            Properties properties = new Properties();
            properties.load(inputstream);

            return DriverManager.getConnection
                    (properties.getProperty("url"),
                            properties.getProperty("username"),
                            properties.getProperty("password"));
        } finally {
            inputstream.close();
        }
    }

}
