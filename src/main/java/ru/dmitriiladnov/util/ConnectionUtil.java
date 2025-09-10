package ru.dmitriiladnov.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionUtil {
    private static final Properties PROPERTIES = new Properties();

    static {
        try(InputStream inputStream = ConnectionUtil.class
                .getClassLoader()
                .getResourceAsStream("connectDB.properties"))
        {
            PROPERTIES.load(inputStream);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private ConnectionUtil() {
    }

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    PROPERTIES.getProperty("db.url"),
                    PROPERTIES.getProperty("db.username"),
                    PROPERTIES.getProperty("db.password")
            );
        } catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
