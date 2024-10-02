/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package evaluación;

/**
 *
 * @author Claudia
 */
public class Autor {
    private int idAutor;
    private String nombre;
    private String apellidos;

    public Autor(int idAutor, String nombre, String apellidos) {
        this.idAutor = idAutor;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }


    public int getId() {
        return idAutor;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }
    
    // Método toString que devuelve el nombre completo del autor
    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
}


