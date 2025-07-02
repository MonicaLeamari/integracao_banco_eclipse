package br.com.teste;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/Lista", 
                "postgres", 
                "Monica28"
            );
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do PostgreSQL não encontrado.", e);
        }
    }
}
