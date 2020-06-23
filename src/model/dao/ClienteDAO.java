
package model.dao;

import br.com.ap3.util.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.bean.Cliente;


public class ClienteDAO {
       public void create(Cliente c)  {
        
        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("INSERT INTO cliente (nome,sobrenome,cpf)VALUES(?,?,?)");          
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getSobrenome());
            stmt.setString(3, c.getCpf());
           

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }

    public List<Cliente> ler() {

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

    public void update(Cliente c) {

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("UPDATE cliente SET nome = ? ,sobrenome = ?,cpf = ? WHERE id = ?");
            stmt.setString(1, c.getNome());
            stmt.setString(2, c.getSobrenome());
            stmt.setString(3, c.getCpf());
           
            stmt.setInt(4, c.getId());

            stmt.executeUpdate();

            JOptionPane.showMessageDialog(null, "Atualizado!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro" + ex);
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }

    }
    public void delete(Cliente c){

        Connection con = ConnectionFactory.getConnection();
        
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM cliente WHERE id = ?");
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

