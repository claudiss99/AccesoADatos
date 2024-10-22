/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.File;

/**
 *
 * @author Usuario
 */
public class Ejercicio3 {
    public static void main(String[] args) {
        File file = new File(args[0]);
        
        if (file.exists() && file.isFile()){
            System.out.println("Nombre: "+file.getName());
            System.out.println("Tamaño: "+file.length());
            System.out.println("Fecha de la última modificación: "+file.lastModified());
            System.out.println("Permiso de lectura "+file.canRead());
            System.out.println("Permiso de escritura "+file.canWrite());
            System.out.println("Permiso de ejecución "+file.canExecute());
            System.out.println("Es oculto "+file.isHidden());
        }
        
    }
}
