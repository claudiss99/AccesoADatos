/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 *
 * @author ClaudissPerez
 */


public class ClienteDAO {
    private final Conexion conexion;

    public ClienteDAO() throws SQLException {
        this.conexion = new Conexion();
    }

    public List<Cliente> listarClientes() throws SQLException {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM clientes";
        PreparedStatement ps = conexion.getPreparedStament(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            clientes.add(new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("ciudad"),
                rs.getString("telefono"),
                rs.getString("password")
            ));
        }
        return clientes;
    }

    public boolean agregarCliente(Cliente cliente) throws SQLException {
        String checkEmail = "SELECT COUNT(*) FROM clientes WHERE email = ?";
        PreparedStatement ps = conexion.getPreparedStament(checkEmail);
        ps.setString(1, cliente.getEmail());
        ResultSet rs = ps.executeQuery();
        rs.next();

        if (rs.getInt(1) > 0) {
            return false; // Email ya registrado
        }

        String sql = "INSERT INTO clientes (nombre, email, ciudad, telefono, password) VALUES (?, ?, ?, ?, ?)";
        ps = conexion.getPreparedStament(sql);
        ps.setString(1, cliente.getNombre());
        ps.setString(2, cliente.getEmail());
        ps.setString(3, cliente.getCiudad());
        ps.setString(4, cliente.getTelefono());
        ps.setString(5, cliente.getPassword());

        return ps.executeUpdate() > 0;
    }

    public Cliente buscarPorEmail(String fragmentoEmail) throws SQLException {
        String sql = "SELECT * FROM clientes WHERE email LIKE ?";
        PreparedStatement ps = conexion.getPreparedStament(sql);
        ps.setString(1, "%" + fragmentoEmail + "%");
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Cliente(
                rs.getInt("id"),
                rs.getString("nombre"),
                rs.getString("email"),
                rs.getString("ciudad"),
                rs.getString("telefono"),
                rs.getString("password")
            );
        }
        return null;
    }

    public boolean modificarCliente(String email, String nombre, String telefono, String ciudad) throws SQLException {
        String sql = "UPDATE clientes SET nombre = ?, telefono = ?, ciudad = ? WHERE email = ?";
        PreparedStatement ps = conexion.getPreparedStament(sql);
        ps.setString(1, nombre);
        ps.setString(2, telefono);
        ps.setString(3, ciudad);
        ps.setString(4, email);

        return ps.executeUpdate() > 0;
    }

    public boolean borrarCliente(String email) throws SQLException {
        String sql = "DELETE FROM clientes WHERE email = ?";
        PreparedStatement ps = conexion.getPreparedStament(sql);
        ps.setString(1, email);

        return ps.executeUpdate() > 0;
    }
    
    public List<Map<String, Object>> obtenerRankingClientes() throws SQLException {
        List<Map<String, Object>> ranking = new ArrayList<>();

        String sql = "SELECT c.nombre, c.email, COUNT(p.id) AS num_pedidos, SUM(p.precio_total) AS gasto_total " +
                     "FROM clientes c " +
                     "LEFT JOIN pedidos p ON c.id = p.id_cliente " +
                     "GROUP BY c.id " +
                     "ORDER BY gasto_total DESC";

        PreparedStatement ps = conexion.getPreparedStament(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Map<String, Object> clienteData = new HashMap<>();
            clienteData.put("nombre", rs.getString("nombre"));
            clienteData.put("email", rs.getString("email"));
            clienteData.put("num_pedidos", rs.getInt("num_pedidos"));
            clienteData.put("gasto_total", rs.getDouble("gasto_total"));
            ranking.add(clienteData);
        }

        return ranking;
    }

}
