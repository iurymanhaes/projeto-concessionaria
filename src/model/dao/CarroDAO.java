
package model.dao;

import br.com.ap3.util.ConnectionFactory;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Carro;


public class CarroDAO {

    public void create(Carro c)  {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO carro (modelo,ano,placa,valor)VALUES(?,?,?,?)");
            stmt.setString(1, c.getModelo());
            stmt.setInt(2, c.getAno());
            stmt.setString(3, c.getPlaca());
            stmt.setFloat(4, c.getValor());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Carro> ler() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet resultSet= null;

        List<Carro> carros = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM carro");
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                Carro carro = new Carro();

                carro.setId(resultSet.getInt("id"));
                carro.setModelo(resultSet.getString("modelo"));
                carro.setAno(resultSet.getInt("ano"));
                carro.setPlaca(resultSet.getString("placa"));
                carro.setValor(resultSet.getFloat("valor"));
                carros.add(carro);
            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro" + ex);
        } finally {
         
                ConnectionFactory.closeConnection(con, stmt, resultSet);
            } 

        return  carros;

    }
    public List<Carro> pesquisaModelo(String m) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        List<Carro> carros = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM carro WHERE modelo LIKE ?");
            stmt.setString(1, m+"%");
            
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                Carro carro = new Carro();

                carro.setId(resultSet.getInt("id"));
                carro.setModelo(resultSet.getString("modelo"));
                carro.setAno(resultSet.getInt("ano"));
                carro.setPlaca(resultSet.getString("placa"));
                carro.setValor(resultSet.getFloat("valor"));
                carros.add(carro);
            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, resultSet);
        }

        return carros;

    }

    public void update(Carro c) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE carro SET modelo = ? ,ano = ?,placa = ?,valor = ? WHERE id = ?");
            stmt.setString(1, c.getModelo());
            stmt.setInt(2, c.getAno());
            stmt.setString(3, c.getPlaca());
            stmt.setFloat(4, c.getValor());
            stmt.setInt(5, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Carro c){

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM carro WHERE id = ?");
            stmt.setInt(1, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Excluido!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

}
