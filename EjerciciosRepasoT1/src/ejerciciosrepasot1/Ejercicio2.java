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
public class Ejercicio2 {
    public static void main(String[] args) {
        //Recibimos el directorio por argumento
        File dir = new File(args[0]);
        
        //Comprobamos que sea un directorio y que existe
        if(dir.exists() && dir.isDirectory()){
            //Se llama a la función para listar
            
        }else{
            System.out.println("No existe el directorio");
        }
    }
    
    public static void listarFiles (File dir){
        //Meter todos los archivos en un array
        File [] files = dir.listFiles();
        
        //Comprobamos si estaba vacío
        if (files != null){
            for (File f: files){
                //Debes mostrar la ruta completa de cada uno.
                System.out.println("Ruta absoluta: "+f.getAbsolutePath());
                
                //Si es un directorio sigo buscando dentro
                if (f.isDirectory()){
                    listarFiles(f);
                }
            }
        }
        
    }
}
