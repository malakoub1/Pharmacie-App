package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class DBConnection {

    private static DBConnection instance = null;
    private Connection cn = null;

    private final String url = "jdbc:mysql://localhost:3306/pharmacie_db?useSSL=false&serverTimezone=UTC";
    private final String login = "root";
    private final String password = "Malakoub1@";

    private DBConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            cn = DriverManager.getConnection(url, login, password);
            System.out.println("✅ Connexion MySQL OK");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver MySQL introuvable (mysql-connector-j manquant)");
        } catch (SQLException e) {
            System.out.println("❌ Erreur connexion MySQL: " + e.getMessage());
        }
    }

    public static synchronized DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getCn() {
        try{
            return DriverManager.getConnection(url,login,password);
        }catch(SQLException e){
            throw new RuntimeException(e);
        }
        
    }
}
