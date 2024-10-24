/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Ejercicio9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre fichero:");
        File fileName = new File(sc.nextLine());
        System.out.println("Introduce numeros hasta introducir 0");
        int num = sc.nextInt();
        DataOutputStream dos = null;
        try {
            dos = new DataOutputStream(new FileOutputStream(fileName));
            while (num != 0){                
                dos.writeInt(num);
                num = sc.nextInt();        
            }
            dos.flush();
            dos.close();
            System.out.println("Se ha guardado correctamente");
        } catch (IOException ex) {
            System.out.println("No se ha podido guardar correctamente");
        }
       
    }
}
