/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ClaudissPerez
 */
public class Ejercicio10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre fichero:");
        File fileName = new File(sc.nextLine());
        
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(fileName));
            while (dis.available() > 0){
                System.out.println(dis.readInt());
            }
            
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Ejercicio10.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
         Logger.getLogger(Ejercicio10.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
}
