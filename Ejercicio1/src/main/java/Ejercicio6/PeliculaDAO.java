/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ClaudissPerez
 */
public class PeliculaDAO {
    public static Connection conn;
    
    public PeliculaDAO(Connection conn){
        this.conn = conn;
    }

    public ArrayList<Pelicula> listarPeliculas(){
        ArrayList<Pelicula> pelis= new ArrayList<>();
        String query = "SELECT * FROM pelicula";
        PreparedStatement stmt = null;
        try {
            stmt = Conexion.getPreparedStatement(query);
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        pelis = cogerPelis(stmt);
        return  pelis;
    }
    
    public ArrayList<Pelicula> buscarPeliXFragmento(String fragmento){
        ArrayList<Pelicula> pelis = new ArrayList<>();
        String query = "SELECT * FROM pelicula WHERE titulo LIKE ?";
        PreparedStatement stmt = null;
        try {
            stmt = Conexion.getPreparedStatement(query);
            stmt.setString(1, "%"+fragmento+"%");
            
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        pelis = cogerPelis(stmt);
        return pelis;
    }
    
    public ArrayList<Pelicula> buscarPeliXAgno(int agno){
        ArrayList<Pelicula> pelis = new ArrayList<>();
        String query = "SELECT * FROM pelicula WHERE ano = ?";
        PreparedStatement stmt = null;
        try {
            stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, agno);
        } catch (SQLException ex) {
            Logger.getLogger(PeliculaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        pelis = cogerPelis(stmt);
        return pelis;
    }
    
    public ArrayList<Pelicula> cogerPelis (PreparedStatement stmt){
        ArrayList<Pelicula> pelis = new ArrayList<>();
        try{
            
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                int idPeli = rs.getInt("id");
                String tituloPeli = rs.getString("titulo");
                String directorPeli = rs.getString("director");
                int agnoPeli = rs.getInt("ano");
                String generoPeli = rs.getString("genero");
                
                Pelicula pelicula = new Pelicula(idPeli, tituloPeli, directorPeli, agnoPeli, generoPeli);
                pelis.add(pelicula);
            }
        }catch (SQLException e){
            System.err.println("Error al coger peliculas");
        }
        return pelis;
    }
    
    public void addPelicula(Pelicula pelicula){
        String query = "INSERT INTO pelicula (id, titulo, director, ano, genero) VALUES (?,?,?,?,?)";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, pelicula.getId());
            stmt.setString(2, pelicula.getTitulo());
            stmt.setString(3, pelicula.getDirector());
            stmt.setInt(4, pelicula.getAgno());
            stmt.setString(5, pelicula.getGenero());
            stmt.executeUpdate();
            System.out.println("Pelicula agregada con éxito");
        }catch (SQLException e){
            System.err.println("Error al añadir pelicula");
        }
    }
    
    public void updatePelicula(Pelicula pelicula){
        String query = "UPDATE pelicula SET titulo = ?, director = ?, ano=?, genero=? WHERE id=?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setString(1, pelicula.getTitulo());
            stmt.setString(2, pelicula.getDirector());
            stmt.setInt(3, pelicula.getAgno());
            stmt.setString(4, pelicula.getGenero());
            stmt.setInt(5, pelicula.getId());
            stmt.executeUpdate();
            System.out.println("Pelicula actualizada con exito");
        }catch(SQLException e){
            System.err.println("Error al actualizar pelicula"+e.getLocalizedMessage());
        }
    }
    
    public void deletePelicula(Pelicula pelicula){
        String query = "DELETE FROM pelicula WHERE id =?";
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, pelicula.getId());
            stmt.executeUpdate();
        }catch(SQLException e){
            System.err.println("Error al borrar pelicula");
        }
    }
    
    public Pelicula findPeliculaByID (int id){
        String query = "SELECT * FROM pelicula WHERE id=?";
        Pelicula pelicula = null;
        
        try{
            PreparedStatement stmt = Conexion.getPreparedStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                int idPelicula = rs.getInt("id");
                String tituloPelicula = rs.getString("titulo");
                String directorPelicula = rs.getString("director");
                int agnoPelicula = rs.getInt("ano");
                String generoPelicula = rs.getString("genero");
                pelicula = new Pelicula(idPelicula, tituloPelicula, directorPelicula, agnoPelicula, generoPelicula);
                
            }
        }catch(SQLException e){
            System.err.println("Error al encontrar pelicula");
        }
        
        return pelicula;
    }
    
}
