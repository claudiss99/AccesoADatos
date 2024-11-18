package Ejercicio2;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ClaudissPerez
 */
import java.sql.*;
import java.util.Scanner;

public class GestionCliente {
    private static final String URL = "jdbc:mysql://localhost:3306/ad_ej1"; // URL de tu base de datos
    private static final String USER = "2dam";
    private static final String PASSWORD = "2dam";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD)) {
            Scanner scanner = new Scanner(System.in);
            int option;

            do {
                mostrarMenu();
                option = scanner.nextInt();
                scanner.nextLine(); // Consumir el salto de línea

                switch (option) {
                    case 1 -> listarClientes(conn);
                    case 2 -> añadirCliente(conn, scanner);
                    case 3 -> buscarClientePorEmail(conn, scanner);
                    case 4 -> modificarCliente(conn, scanner);
                    case 5 -> borrarCliente(conn, scanner);
                    case 6 -> rankingClientes(conn);
                    case 7 -> añadirPedido(conn, scanner);
                    case 8 -> actualizarPedido(conn, scanner);
                    case 9 -> borrarPedido(conn, scanner);
                    case 10 -> System.out.println("Saliendo del programa...");
                    default -> System.out.println("Opción no válida. Intente de nuevo.");
                }
            } while (option != 10);
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    private static void mostrarMenu() {
        System.out.println("\n--- Menú de Gestión de Clientes ---");
        System.out.println("1. Listar todos los clientes");
        System.out.println("2. Añadir un cliente");
        System.out.println("3. Buscar un cliente por un fragmento del email");
        System.out.println("4. Modificar datos de un cliente");
        System.out.println("5. Borrar un cliente por email");
        System.out.println("6. Ranking de clientes");
        System.out.println("7. Añadir un pedido");
        System.out.println("8. Actualizar datos de un pedido");
        System.out.println("9. Borrar un pedido");
        System.out.println("10. Salir");
        System.out.print("Seleccione una opción: ");
    }

    private static void listarClientes(Connection conn) throws SQLException {
        String sql = "SELECT * FROM cliente";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nClientes:");
            System.out.println("ID\tNombre\tEmail\tCiudad\tTeléfono");
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t%s\t%s%n",
                        rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("email"), rs.getString("ciudad"),
                        rs.getString("telefono"));
            }
        }
    }

    private static void añadirCliente(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();

        String emailCheckQuery = "SELECT COUNT(*) FROM cliente WHERE email = ?";
        try (PreparedStatement emailCheckStmt = conn.prepareStatement(emailCheckQuery)) {
            emailCheckStmt.setString(1, email);
            ResultSet rs = emailCheckStmt.executeQuery();
            rs.next();
            if (rs.getInt(1) > 0) {
                System.out.println("El email ya está registrado.");
                return;
            }
        }

        System.out.print("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese la ciudad del cliente: ");
        String ciudad = scanner.nextLine();
        System.out.print("Ingrese el teléfono del cliente: ");
        String telefono = scanner.nextLine();

        String sql = "INSERT INTO cliente (nombre, email, ciudad, telefono) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.setString(3, ciudad);
            stmt.setString(4, telefono);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Cliente añadido correctamente." : "Error al añadir el cliente.");
        }
    }

    private static void buscarClientePorEmail(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el fragmento del email a buscar: ");
        String fragment = scanner.nextLine();
        String sql = "SELECT * FROM cliente WHERE email LIKE ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + fragment + "%");
            ResultSet rs = stmt.executeQuery();
            System.out.println("\nClientes encontrados:");
            System.out.println("ID\tNombre\tEmail\tCiudad\tTeléfono");
            while (rs.next()) {
                System.out.printf("%d\t%s\t%s\t%s\t%s%n",
                        rs.getInt("id"), rs.getString("nombre"),
                        rs.getString("email"), rs.getString("ciudad"),
                        rs.getString("telefono"));
            }
        }
    }

    private static void modificarCliente(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el email del cliente a modificar: ");
        String email = scanner.nextLine();

        String sql = "SELECT * FROM cliente WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (!rs.next()) {
                System.out.println("El cliente no existe.");
                return;
            }
        }

        System.out.print("Ingrese el nuevo nombre (o ENTER para no cambiar): ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo teléfono (o ENTER para no cambiar): ");
        String telefono = scanner.nextLine();
        System.out.print("Ingrese la nueva ciudad (o ENTER para no cambiar): ");
        String ciudad = scanner.nextLine();

        String updateSql = "UPDATE cliente SET nombre = COALESCE(NULLIF(?, ''), nombre), " +
                "telefono = COALESCE(NULLIF(?, ''), telefono), " +
                "ciudad = COALESCE(NULLIF(?, ''), ciudad) WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(updateSql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, telefono);
            stmt.setString(3, ciudad);
            stmt.setString(4, email);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Cliente modificado correctamente." : "Error al modificar el cliente.");
        }
    }

    private static void borrarCliente(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el email del cliente a borrar: ");
        String email = scanner.nextLine();
        String sql = "DELETE FROM cliente WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Cliente borrado correctamente." : "El cliente no existe.");
        }
    }

    private static void rankingClientes(Connection conn) throws SQLException {
        String sql = """
                SELECT c.nombre, c.email, COUNT(p.id) AS num_pedidos, SUM(p.gasto) AS gasto_total
                FROM cliente c
                LEFT JOIN pedido p ON c.id = p.cliente_id
                GROUP BY c.id
                ORDER BY gasto_total DESC""";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            System.out.println("\nRanking de Clientes:");
            System.out.println("Nombre\tEmail\tN° Pedidos\tGasto Total");
            while (rs.next()) {
                System.out.printf("%s\t%s\t%d\t%.2f%n",
                        rs.getString("nombre"), rs.getString("email"),
                        rs.getInt("num_pedidos"), rs.getDouble("gasto_total"));
            }
        }
    }

    private static void añadirPedido(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el email del cliente: ");
        String email = scanner.nextLine();

        int clienteId = obtenerClienteId(conn, email, scanner);
        if (clienteId == -1) return;

        System.out.print("Ingrese la fecha del pedido (YYYY-MM-DD): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese el gasto del pedido: ");
        double gasto = scanner.nextDouble();
        scanner.nextLine(); // Consumir salto de línea

        String sql = "INSERT INTO pedido (cliente_id, fecha, gasto) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, clienteId);
            stmt.setString(2, fecha);
            stmt.setDouble(3, gasto);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Pedido añadido correctamente." : "Error al añadir el pedido.");
        }
    }

    private static void actualizarPedido(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el ID del pedido a actualizar: ");
        int pedidoId = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea

        System.out.print("Ingrese la nueva fecha (YYYY-MM-DD, o ENTER para no cambiar): ");
        String fecha = scanner.nextLine();
        System.out.print("Ingrese el nuevo gasto (o ENTER para no cambiar): ");
        String gastoStr = scanner.nextLine();

        Double gasto = gastoStr.isEmpty() ? null : Double.parseDouble(gastoStr);

        String sql = "UPDATE pedido SET fecha = COALESCE(NULLIF(?, ''), fecha), " +
                "gasto = COALESCE(?, gasto) WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, fecha.isEmpty() ? null : fecha);
            if (gasto == null) {
                stmt.setNull(2, Types.DOUBLE);
            } else {
                stmt.setDouble(2, gasto);
            }
            stmt.setInt(3, pedidoId);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Pedido actualizado correctamente." : "Error al actualizar el pedido.");
        }
    }

    private static void borrarPedido(Connection conn, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el ID del pedido a borrar: ");
        int pedidoId = scanner.nextInt();
        scanner.nextLine(); // Consumir salto de línea
        String sql = "DELETE FROM pedido WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, pedidoId);
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected > 0 ? "Pedido borrado correctamente." : "El pedido no existe.");
        }
    }

    private static int obtenerClienteId(Connection conn, String email, Scanner scanner) throws SQLException {
        String sql = "SELECT id FROM cliente WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            } else {
                System.out.println("El cliente no existe. Ingrese los datos para crearlo:");
                System.out.print("Nombre: ");
                String nombre = scanner.nextLine();
                System.out.print("Ciudad: ");
                String ciudad = scanner.nextLine();
                System.out.print("Teléfono: ");
                String telefono = scanner.nextLine();

                String insertSql = "INSERT INTO cliente (nombre, email, ciudad, telefono) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStmt = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS)) {
                    insertStmt.setString(1, nombre);
                    insertStmt.setString(2, email);
                    insertStmt.setString(3, ciudad);
                    insertStmt.setString(4, telefono);
                    int rowsAffected = insertStmt.executeUpdate();
                    if (rowsAffected > 0) {
                        ResultSet generatedKeys = insertStmt.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            return generatedKeys.getInt(1);
                        }
                    }
                }
            }
        }
        return -1;
    }
}
