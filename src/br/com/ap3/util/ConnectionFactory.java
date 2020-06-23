
package br.com.ap3.util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class ConnectionFactory {

    private static final String DRIVER = "com.mysql.jdbc.Driver";
    private static final String URL_CONEXAO = "jdbc:mysql://localhost:3306/bdvendas";
    private static final String USUARIO = "root";
    private static final String SENHA = "123456789";

    public static Connection getConnection() {
        try {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL_CONEXAO, USUARIO, SENHA);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão ", ex);
        }
    }

    public static void closeConnection(Connection con)  {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {
            System.err.println("Erro");
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt)  {

        closeConnection(con);
        try {
            
            if (stmt != null) {
                stmt.close();
            }
            
        } catch (SQLException ex) {
            System.err.println("Erro");
        }
    }

    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet resultSet){

        closeConnection(con, stmt);

        try {

            if (resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException ex) {
            System.err.println("Erro");
        }
    }

}
