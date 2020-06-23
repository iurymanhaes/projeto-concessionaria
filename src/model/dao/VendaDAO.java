
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
import model.bean.Cliente;
import model.bean.Venda;


public class VendaDAO {
      public void create(Venda v)  {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO venda (vendedor,idcliente,idcarro)VALUES(?,?,?)");          
            stmt.setString(1, v.getVendedor());
            stmt.setInt(2, v.getIdcliente());
            stmt.setInt(3, v.getIdcarro());
           
           

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
      
      
      public List<Carro> lercarro() {

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
      public List<Carro> pesquisaModeloCarro(String m) {

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
    
    
    public List<Cliente> lercliente() {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet resultSet= null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente");
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(resultSet.getInt("id"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setSobrenome(resultSet.getString("sobrenome"));
                cliente.setCpf(resultSet.getString("cpf"));
               
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro" + ex);
        } finally {
         
                ConnectionFactory.closeConnection(con, stmt, resultSet);
            } 

        return  clientes;

    }
    public List<Cliente> pesquisaCliente(String m) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet resultSet = null;

        List<Cliente> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM cliente WHERE nome LIKE ?");
            stmt.setString(1, m+"%");
            
            resultSet = stmt.executeQuery();

            while (resultSet.next()) {

                Cliente cliente = new Cliente();

                cliente.setId(resultSet.getInt("id"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setSobrenome(resultSet.getString("sobrenome"));
                cliente.setCpf(resultSet.getString("cpf"));
               
                clientes.add(cliente);
            }

        } catch (SQLException ex) {
           JOptionPane.showMessageDialog(null, "Erro" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt, resultSet);
        }

        return clientes;

    }
      
}
