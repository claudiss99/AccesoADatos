/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.File;

/**
 *
 * @author Claudia
 */
public class Ejercicio1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // Escribe un programa que reciba la ruta de un fichero o directorio
        String fileDir = args[0];
        File file = new File(fileDir);
        //verifique si existe
        if(file.exists()){
            //Si existe, el programa debe mostrar si es un fichero o un directorio
            if(file.isFile()){
                System.out.println("Es un fichero");
            }else if(file.isDirectory()){
                System.out.println("Es un directorio");
            }
            // indica si el usuario tiene permisos de lectura, escritura y ejecución sobre dicho fichero o directorio
            if (file.canRead()){
                System.out.println("Tienes permiso de lectura");
            }else{
                System.out.println("No tienes permiso de lectura");
            }
            
            if (file.canWrite()){
                System.out.println("Tienes permiso de escritura");
            }else{
                System.out.println("Tienes permiso de escritura");
            }
            
            if (file.canExecute()){
                System.out.println("Tienes permiso de ejecución");
            }else {
                System.out.println("No tienes permiso de ejecución");
            }
        }else{
            System.out.println("El fichero o directorio No existe");
        }
    }
    
}
