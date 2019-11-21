package util;

import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBHelper {
    public static Connection getConnection() throws RuntimeException {
        try {
            Class.forName(PropertyReader.getProperty("jdbc_driver_class"));
            return DriverManager.getConnection(PropertyReader.getProperty("jdbc_connection_url"),
                    PropertyReader.getProperty("databaseUsername"),
                    PropertyReader.getProperty("databasePassword"));
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    private static void setConfigurationPropertyForName(Configuration configuration, String name)  {
        configuration.setProperty(name, PropertyReader.getProperty(name));
    }

    @SuppressWarnings("UnusedDeclaration")
    public static Configuration getConfiguration(Class[] classes) {
        Configuration configuration = new Configuration();
        for (Class concreteClass : classes) {
            configuration.addAnnotatedClass(concreteClass);
        }

        setConfigurationPropertyForName(configuration, "hibernate.dialect");
        setConfigurationPropertyForName(configuration, "jdbc_driver_class");
        setConfigurationPropertyForName(configuration, "jdbc_connection_url");
        setConfigurationPropertyForName(configuration, "serverTimezone");
        setConfigurationPropertyForName(configuration, "databaseUsername");
        setConfigurationPropertyForName(configuration, "databasePassword");
        setConfigurationPropertyForName(configuration, "hibernate.show_sql");
        setConfigurationPropertyForName(configuration, "hibernate.hbm2ddl.auto");

        return configuration;
    }

}
