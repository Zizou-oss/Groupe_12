package main;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try {
            Connection conn = DatabaseManager.getConnection();
            if (conn != null) {
                System.out.println("✅ Connexion réussie à la base de données.");
                conn.close();
            } else {
                System.out.println("❌ Échec de la connexion.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
