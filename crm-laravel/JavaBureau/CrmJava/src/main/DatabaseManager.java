package main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {

    public static Connection getConnection() {
        try {
            // Charger le driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");

            // URL de connexion, remplace "localhost", "database_name" et "password" par les valeurs correctes
            String url = "jdbc:mysql://localhost:3306/crm_laravel";
            String user = "root";
            String password = "";

            // Établir la connexion
            Connection conn = DriverManager.getConnection(url, user, password);
            System.out.println("Connexion réussie !");
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
