/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.awt.Color;
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
public class Imagen9 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            //Pedir nombre de entrada de la imagen y salida
            BufferedImage imageI = ImageIO.read(new File(sc.nextLine()));
            File imageO;
            imageO= new File(sc.nextLine());
            
            if (imageO.exists()){
                System.out.println("¿Pulsa 1 para sobreescribir y 2 para cambiarlo?");
                int opc = sc.nextInt();
                if (opc == 2){
                    imageO= new File(sc.nextLine());
                    while(imageO.exists()){
                        imageO= new File(sc.nextLine());
                    }
                }
            }
            
            //Recorremos la imagen para cambiarle el color
            for (int x=0; x<imageI.getWidth(); x++){
                for (int y=0; y<imageI.getHeight();y++){
                    int rgb = imageI.getRGB(x, y);
                    Color color = new Color(rgb);
                    
                    //inicializamos y cogemos el rgb
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    
                    red = 255 - red;
                    green = 255 - green;
                    blue = 255 - blue;
                    Color invertedColor = new Color(red,green,blue);
                    //Ponemos el color
                    imageI.setRGB(x, y, invertedColor.getRGB());
                }
            }

            ImageIO.write(imageI, "png", imageO);
        } catch (IOException ex) {
            Logger.getLogger(Imagen9.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
