/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author ClaudissPerez
 */
public class Ejercicio11 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nombre fichero:");
        File fileRead = new File(sc.nextLine());
        
        
        try {
            //Crear archivos temporales
            File filePos = File.createTempFile("posNum", "");
            filePos.deleteOnExit();
            File fileNeg = File.createTempFile("negNum", "");
            fileNeg.deleteOnExit();
            DataInputStream dis = new DataInputStream(new FileInputStream(fileRead));
            BufferedWriter wNeg = new BufferedWriter(new FileWriter(fileNeg));
            BufferedWriter wPos = new BufferedWriter(new FileWriter(filePos));
            int cNeg=0;
            int cPos=0;
            while (dis.available() > 0){
                int num = dis.readInt();
                if (num < 0){
                    wNeg.write(String.valueOf(num));
                    wNeg.newLine();
                    cNeg++;
                }else{
                    wPos.write(String.valueOf(num));
                    wPos.newLine();
                    cPos++;
                }
            }
            wNeg.flush();
            wPos.flush();
            wNeg.close();
            wPos.close();
            //Mostrar al usuario cuantos hay de cada tipo
            System.out.println("Hay "+cPos+" numeros positivos y "+cNeg+" numeros negativos");
            
            //Pregunta que quiere guardar
            System.out.println("1: Si quieres guardar numeros posivitos\n2.: Si quieres guardar numeros negativos\n3: Si quieres guardar ambos");
            String opc = sc.nextLine();
            File fSave;
             switch (opc) {
                 
                case "1":
                    System.out.println("Nombre fichero positivo");
                    fSave = new File(sc.nextLine());
                    guardarPos(filePos, fSave);
                    break;
                case "2":
                    System.out.println("Nombre fichero negativo");
                    fSave = new File(sc.nextLine());
                    guardarNeg(fileNeg, fSave);
                    
                    break;
                case "3":
                    System.out.println("Nombre fichero positivo");
                    File fSaveP = new File(sc.nextLine());
                    System.out.println("Nombre fichero negativo");
                    fSave = new File(sc.nextLine());
                    guardarPos(filePos, fSaveP);
                    guardarNeg(fileNeg, fSave);
                    
                    break;
                default:
                    throw new AssertionError();
            }
            
            
        } catch (FileNotFoundException ex) {
            System.out.println("Archivo no encontrado");
        } catch (IOException ex) {
            System.out.println("Ha ocurrido un error en los parámetros de entrada y salida");
        }

    }
    
    public static void guardarPos(File filePos, File fSave){
        filePos.renameTo(fSave);
    }
    
    public static void guardarNeg(File fileNeg, File fSave){
        fileNeg.renameTo(fSave);
    }
}
