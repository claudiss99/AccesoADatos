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
import ejerciciosrepasot1.FotoInventada;

/**
 *
 * @author ClaudissPerez
 */
public class GirarImage {
    public static void main(String[] args) throws FotoInventada {
       Scanner sc = new Scanner(System.in);

        try {
            // Leer la imagen de entrada
            System.out.println("Introduce la ruta de la imagen de entrada:");
            BufferedImage imageI = ImageIO.read(new File(sc.nextLine()));

            // Pedir el ángulo de rotación (90, 180, o 270)
            System.out.println("Introduce el ángulo de rotación (90, 180, 270):");
            int angle = sc.nextInt();

            BufferedImage rotatedImage = null;
            int width = imageI.getWidth();
            int height = imageI.getHeight();

            switch (angle) {
                case 50:
                    throw new FotoInventada();
                case 90:
                    rotatedImage = new BufferedImage(height, width, imageI.getType());
                    for (int x = 0; x < width; x++) {
                        for (int j = 0; j < height; j++) {
                            rotatedImage.setRGB(j, width - 1 - x, imageI.getRGB(x, j));
                        }
                    }
                    break;

                case 180:
                    rotatedImage = new BufferedImage(width, height, imageI.getType());
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            rotatedImage.setRGB(width - 1 - i, height - 1 - j, imageI.getRGB(i, j));
                        }
                    }
                    break;

                case 270:
                    rotatedImage = new BufferedImage(height, width, imageI.getType());
                    for (int i = 0; i < width; i++) {
                        for (int j = 0; j < height; j++) {
                            rotatedImage.setRGB(height - 1 - j, i, imageI.getRGB(i, j));
                        }
                    }
                    break;

                default:
                    System.out.println("Ángulo no soportado. Solo se admite 90, 180 o 270 grados.");
                    return;
            }

            // Guardar la imagen de salida
            System.out.println("Introduce la ruta para guardar la imagen rotada:");
            File imageO = new File(sc.next());
            ImageIO.write(rotatedImage, "jpg", imageO);

            System.out.println("Imagen rotada guardada exitosamente.");
        } catch (IOException ex) {
            Logger.getLogger(GirarImage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
