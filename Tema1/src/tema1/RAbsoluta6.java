/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Claudia
 */
public class RAbsoluta6 {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        // Crea un archivo temporal
        try {
            // Crea un programa donde creas un fichero de temporal 
            File fTemp = File.createTempFile("tempfile", "");
            // muestra la ruta absoluta al fichero
            System.out.println("Ruta absoluta donde se ha creado el fichero: " + fTemp.getAbsolutePath());
            //elimine el fichero temporal(lo va a eliminar cuando acabe)
            fTemp.deleteOnExit();
            // Almacena en el fichero nombres y edades de personas (No se especifica como se añaden)
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(fTemp))) {
                writer.write("Jose, 30");
                writer.newLine();
                writer.write("María, 25");
                writer.newLine();
                writer.write("Carla, 21");
                writer.newLine();
                writer.flush();
            }
            // Ahora lee el documento y muéstralo (try with resource hace el close solo)
            try (BufferedReader reader = new BufferedReader(new FileReader(fTemp))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
                
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }   
    }
    
}
