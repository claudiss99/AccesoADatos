/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author ClaudissPerez
 */
public class Imagen8 {
    public static void main(String[] args) {
        //Pido nombre de la imagen
        Scanner sc = new Scanner(System.in);
        try {
            BufferedImage image = ImageIO.read(new File(sc.nextLine()));
            //Modificamos la imagen con;
            System.out.println("Alto: "+image.getHeight());
            System.out.println("Ancho: "+image.getWidth());
            System.out.println("Numeros de pixeles: "+(image.getHeight()*image.getWidth()));
            //Modificar a jpg
            ImageIO.write(image, "jpeg", new File("modifica.png"));
        } catch (IOException ex) {
            System.err.println("Ha ocurrido un problema en la lectura de la imagen");
            System.exit(1);
        }
        
    }
}
