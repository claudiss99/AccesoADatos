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
import javax.imageio.stream.ImageOutputStream;

/**
 *
 * @author ClaudissPerez
 */
public class Imagen10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            //Pedir nombre de entrada de la imagen y salida
            String imageName = sc.nextLine();
            BufferedImage imageI = ImageIO.read(new File(imageName));
            String imageN = sc.nextLine();
            
            BufferedImage brighter = ImageIO.read(new File(imageName));
            BufferedImage darker = ImageIO.read(new File(imageName));
            BufferedImage gray = ImageIO.read(new File(imageName));
            
            //Recorremos la imagen de entrada
            for (int x=0; x<imageI.getWidth();x++){
                for (int y=0 ;x<imageI.getHeight(); y++){
                    int rgb= imageI.getRGB(x, y);
                    Color color = new Color(rgb);
                    
                    int red = color.getRed();
                    int green = color.getGreen();
                    int blue = color.getBlue();
                    
                    int grayi = (int)((0.3*red) + (0.5*green) + (0.2*blue));
                    Color grayC = new Color(grayi, grayi, grayi);
                    Color brighterC = new Color(red, green, blue).brighter();
                    Color darkerC = new Color(red, green, blue).darker();
                    
                    brighter.setRGB(x, y, brighterC.getRGB());
                    darker.setRGB(x, y, darkerC.getRGB());
                    gray.setRGB(x, y, grayC.getRGB());
                    
                }
                ImageIO.write(brighter, "png", new File(imageN+"_brighter.png"));
                ImageIO.write(darker, "png", new File(imageN+"_darker.png"));
                ImageIO.write(gray, "png", new File(imageN+"_gray.png"));
            }
        } catch (IOException ex) {
            System.err.println("Error al leer la imagen");
            System.exit(1);
        }    
    }
}
