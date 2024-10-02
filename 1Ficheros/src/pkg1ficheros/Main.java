/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pkg1ficheros;

import java.io.File;
import java.util.Arrays;

/**
 *
 * @author Claudia
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        File ruta = new File("C:\\Users\\Claudia\\Downloads");
        fichero(ruta);
    }

    private static void fichero(File ruta) {
        //Comprobamos si la ruta existe, sino existe se crea
        while (!ruta.exists()){ 
            ruta = new File("datos.txt");
        }
        if (ruta.isDirectory()){
            System.out.println(ruta+" es un directorio.");
            File[] lista = ruta.listFiles();
            for (File lista1 : lista) {
                if (lista1.isDirectory()) {
                    System.out.println("d - " + lista1.getName());
                } else if (lista1.isFile()) {
                    System.out.println("_ - " + lista1.getName());
                } else {
                    System.out.println("? - " + lista1.getName());
                }
            }
        }else if (ruta.isFile()){
            System.out.println(ruta+" es un fichero"); 
        }
    }
   
}
