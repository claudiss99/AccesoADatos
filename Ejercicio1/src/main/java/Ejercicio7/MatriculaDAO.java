/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio7;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import static java.sql.Types.NULL;

/**
 *
 * @author ClaudissPerez
 */
public class MatriculaDAO {
    public static Connection conn;
    
    public MatriculaDAO(Connection conn) {
        this.conn = conn;
    }
   
    public void matricularEstudiante(Estudiante estudiante, Asignatura asignatura, int agno){
        String query= "INSERT INTO matricula (id_estudiante, id_asignatura, agno, estado) VALUES (?,?,?, ?)";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, estudiante.getId());
            stmt.setInt(2, asignatura.getId());
            stmt.setInt(3, agno);
            stmt.setString(4, "cursando");
            //A lo mejor hay que agregar calificacion si no se pone a null
            stmt.executeUpdate();
            System.out.println("Matricula agregada con exito");
        }catch(SQLException e){
            System.err.println("Error al matricular estudiante");
        }
    }
    
    public void addCalificacion(Estudiante estudiante, Asignatura asignatura, int agno, int calificacion){
        String query= "SELECT * FROM matricula WHERE id_estudiante = ? AND id_asignatura = ?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, estudiante.getId());
            stmt.setInt(2, asignatura.getId());
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                String estado;
                if(calificacion<5){
                    estado="no superada";
                }else{
                    estado ="superada";
                }
                String query2 = "UPDATE matricula SET agno =?, estado=?,calificacion=? WHERE id_estudiante = ? AND id_asignatura = ?";
                
                try{
                    PreparedStatement stmt2 = Conexion.getPreparedStatement(query2);
                    stmt2.setInt(1, agno);
                    stmt2.setString(2, estado);
                    stmt2.setInt(3, calificacion);
                    stmt2.setInt(4, estudiante.getId());
                    stmt2.setInt(5, asignatura.getId());
                    stmt2.executeUpdate();
                    System.out.println("Calificacion agregada con exito");
                }catch(SQLException e){
                    System.err.println("Error al añadir calificacion "+e.getLocalizedMessage());
                }
            }
            
        }catch(SQLException e){
            System.err.println("Error al encontrar estudiante");
        }    
    }
    
    public void listarHistorial(Estudiante estudiante){
        String query = "SELECT * FROM matricula WHERE id_estudiante =? ORDER BY agno DESC";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, estudiante.getId());
            
            ResultSet rs = stmt.executeQuery();
            System.out.println("Historial de matrículas de "+estudiante.getNombre());
            System.out.println("Año, nombre asignatura, estado, calificacion");
            while (rs.next()){
                int agnoMatricula = rs.getInt("agno");
                int idAsignatura = rs.getInt("id_asignatura");
                String estadoMatricula = rs.getString("estado");
                double calificacionAsignatura = rs.getDouble("calificacion");
                
                //Cogemos el nombre de la asignatura
                String query2 = "SELECT nombre FROM asignatura WHERE id=?";
                
                try{
                    PreparedStatement stmt2 = Conexion.getPreparedStatement(query2);
                    stmt2.setInt(1, idAsignatura);
                    
                    ResultSet rs2= stmt2.executeQuery();
                    String nombreAsignatura = "";
                    if(rs2.next()){
                        nombreAsignatura = rs2.getString("nombre");
                    }
                    
                    String calificacion;
                    if(calificacionAsignatura == NULL){
                        calificacion = "-";
                    }else{
                        calificacion = String.valueOf(calificacionAsignatura);
                    }
                    System.out.println(agnoMatricula+", "+nombreAsignatura+", "+estadoMatricula+", "+calificacion);
                }catch(SQLException e){
                    System.err.println("Error al coger asignatura");
                }
            }
        }catch(SQLException e){
            System.err.println("Error al listar historial");
        }
    }
    
    public void cancelarMatricula(Estudiante estudiante, Asignatura asignatura, int agno){
        //Comprobar que existe esa matricula
        String query= "SELECT * FROM matricula WHERE id_estudiante = ? AND id_asignatura = ? AND agno=?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, estudiante.getId());
            stmt.setInt(2, asignatura.getId());
            stmt.setInt(3, agno);
            
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                String query2 = "DELETE FROM matricula WHERE id_estudiante =? AND id_asignatura=? AND agno=? AND estado LIKE 'cursando'";
                
                try{
                    PreparedStatement stmt2 = Conexion.getPreparedStatement(query2);
                    stmt2.setInt(1, estudiante.getId());
                    stmt2.setInt(2, asignatura.getId());
                    stmt2.setInt(3, agno);
                    
                    stmt2.executeUpdate();
                    System.out.println("Matricula cancelada con exito");
                }catch(SQLException e){
                    System.err.println("Error al cancelar matricula");
                }
            }
        }catch(SQLException e){
            System.err.println("Error al encontrar la matricula");
        }
    }

}
