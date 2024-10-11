/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema1;

import java.io.File;
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
        // TODO code application logic here
        /*Crea un programa donde creas un fichero de temporal y 
        muestra la ruta absoluta al fichero. Almacena en el fichero 
        nombres y edades de personas. Ahora lee el documento y muéstralo 
        por la salida estándar. Al finalizar, elimine el fichero temporal.*/
        File fTemp = File.createTempFile("guardarAbosuteRute", "");
        fTemp.deleteOnExit();
        System.out.println("Ruta absoluta donde se ha creado el fichero: "+fTemp.getAbsolutePath());
        
    }
    
}
