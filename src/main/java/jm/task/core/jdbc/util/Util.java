package jm.task.core.jdbc.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.exception.spi.Configurable;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
 Configuration configuration = new Configuration();
 Session session = sessionFactory.openSession();
 private static SessionFactory sessionFactory ;

    public  Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/maga",
                    "root", "shefatm12345");
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    public void closeConn(Connection conn) throws SQLException {
        conn.close();
    }
    public static SessionFactory getSessionFactory (){
        Configuration configuration  = new Configuration();
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/maga");
        properties.setProperty("hibernate.connection.username", "root");
        properties.setProperty("hibernate.connection.password", "shefatm12345");
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        configuration.addProperties(properties);
        configuration.addAnnotatedClass(jm.task.core.jdbc.model.User.class);

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

}




