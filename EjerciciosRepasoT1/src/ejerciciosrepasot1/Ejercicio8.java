/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Usuario
 */
public class Ejercicio8 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre fichero:");
        File fileName = new File(sc.nextLine());
        System.out.println("Introduce numeros hasta introducir 0");
        int num = sc.nextInt();
        DataOutputStream dos = null;
        while (num != 0){
            try {
                dos = new DataOutputStream(new FileOutputStream(fileName));
                dos.writeInt(num);
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Ejercicio8.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
             Logger.getLogger(Ejercicio8.class.getName()).log(Level.SEVERE, null, ex);
            }
            num = sc.nextInt();        
        }
        
        try {
            dos.flush();
            dos.close();
        } catch (IOException ex) {
            Logger.getLogger(Ejercicio8.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
}
