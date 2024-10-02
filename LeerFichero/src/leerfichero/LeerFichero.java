/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package leerfichero;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 *
 * @author Claudia
 */
public class LeerFichero {

    /**
     * @param args the command line arguments
     * @throws java.io.FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        // TODO code application logic here
        try (FileReader fr = new FileReader("C:\\Users\\Claudia\\Documents\\NetBeansProjects\\Acceso a datos\\LeerFichero\\src\\leerfichero\\leer.txt")) {
         BufferedReader br = new BufferedReader(fr);
         // Lectura del fichero
         String linea;
         while((linea=br.readLine())!=null){
             System.out.println(linea);
         }
            
        }catch(Exception e){
      }
    }
    
}
