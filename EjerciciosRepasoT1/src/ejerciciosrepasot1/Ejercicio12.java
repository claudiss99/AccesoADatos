/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author ClaudissPerez
 */
public class Ejercicio12 {
    public static void main(String[] args) throws FileNotFoundException, IOException {
        Scanner sc = new Scanner(System.in);
        
        //Pedimos nombres de ficheros hasta que los meta vacio
        String fileName = sc.nextLine();
        File file;
        ArrayList<File> files = new ArrayList<>();
        while (!fileName.isEmpty()){
            file = new File(fileName);
            //Comprobamos que sea fichero
            if(file.isFile() && file.exists()){
                //Guardamos todos los ficheros en un array
                files.add(file);
            }
            fileName = sc.nextLine();
        }
        File fileNew = new File("fileNew.txt");
        BufferedReader reader = null;
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileNew, true));
        for(File f:files){
            writer.write(f.getName());
            writer.newLine();
            reader = new BufferedReader(new FileReader(f));
            String linea= "";
            while((linea = reader.readLine()) != null){
                writer.write(linea);
                writer.newLine();
            }    
        }
        writer.flush();
        writer.close();
        reader.close();
        
    }
}
