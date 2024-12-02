/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Ejercicio2;

/**
 *
 * @author ClaudissPerez
 */
public class Cliente {
    private  int id;
    private String nombre;
    private String email;
    private String ciudad;
    private String telefono;
    private String password;

    public Cliente(int id, String nombre, String email, String ciudad, String telefono, String password) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre=" + nombre + ", email=" + email + ", ciudad=" + ciudad + ", telefono=" + telefono + ", password=" + password + '}';
    }
    
    
}
