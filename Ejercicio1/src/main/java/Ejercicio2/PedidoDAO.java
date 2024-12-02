/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ClaudissPerez
 */

public class PedidoDAO {
    private final Conexion conexion;

    public PedidoDAO() throws SQLException {
        this.conexion = new Conexion();
    }

    public List<Pedido> listarPedidos() throws SQLException {
        List<Pedido> pedidos = new ArrayList<>();
        String sql = "SELECT * FROM pedidos";
        PreparedStatement ps = conexion.getPreparedStament(sql);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            pedidos.add(new Pedido(
                rs.getInt("id"),
                rs.getInt("id_cliente"),
                rs.getString("fecha"), (int) rs.getDouble("precio_total")));
        }
        return pedidos;
    }

    public boolean agregarPedido(int idCliente, String fecha, double precioTotal) throws SQLException {
        String sql = "INSERT INTO pedidos (id_cliente, fecha, precio_total) VALUES (?, ?, ?)";
        PreparedStatement ps = conexion.getPreparedStament(sql);
        ps.setInt(1, idCliente);
        ps.setString(2, fecha);
        ps.setDouble(3, precioTotal);

        return ps.executeUpdate() > 0;
    }

    public boolean actualizarPedido(int idPedido, String fecha, double precioTotal) throws SQLException {
        String sql = "UPDATE pedidos SET fecha = ?, precio_total = ? WHERE id = ?";
        PreparedStatement ps = conexion.getPreparedStament(sql);
        ps.setString(1, fecha);
        ps.setDouble(2, precioTotal);
        ps.setInt(3, idPedido);

        return ps.executeUpdate() > 0;
    }

    public boolean borrarPedido(int idPedido) throws SQLException {
        String sql = "DELETE FROM pedidos WHERE id = ?";
        PreparedStatement ps = conexion.getPreparedStament(sql);
        ps.setInt(1, idPedido);

        return ps.executeUpdate() > 0;
    }

    public Pedido buscarPedidoPorId(int idPedido) throws SQLException {
        String sql = "SELECT * FROM pedidos WHERE id = ?";
        PreparedStatement ps = conexion.getPreparedStament(sql);
        ps.setInt(1, idPedido);
        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return new Pedido(
                rs.getInt("id"),
                rs.getInt("id_cliente"),
                rs.getString("fecha"), (int) rs.getDouble("precio_total"));
        }
        return null;
    }
}
