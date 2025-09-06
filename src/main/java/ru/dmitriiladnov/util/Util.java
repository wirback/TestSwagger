package ru.dmitriiladnov.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.InputMismatchException;
import java.util.Properties;

public final class Util {
    private static final Properties PROPERTIES = new Properties();

    static {
        try(InputStream inputStream = Util.class
                .getClassLoader()
                .getResourceAsStream("connectDB.properties"))
        {
            PROPERTIES.load(inputStream);

        } catch (IOException e) {
            e.printStackTrace();
//            throw new InputMismatchException(e.getMessage());
        }
    }

    private Util() {}

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(
                    PROPERTIES.getProperty("db.url"),
                    PROPERTIES.getProperty("db.username"),
                    PROPERTIES.getProperty("db.password")
            );
        } catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }
}
