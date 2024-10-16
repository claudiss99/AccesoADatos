/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tema1;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

/**
 *
 * @author Claudia
 */
public class pixel8 {
    public static void main(String[] args) throws IOException {
        System.out.println("Escribe el nombre de la imagen");
        Scanner sc = new Scanner(System.in);
        String imageName = sc.nextLine();
        
        BufferedImage image = ImageIO.read(new File(imageName));
        double anchura = image.getWidth();
        double altura = image.getHeight();
        System.out.println("Ancho: "+anchura);
        System.out.println("Alto: "+altura);
        System.out.println("Numero de pixeles: "+(anchura*altura));
        //Modificacion de la imagen
        ImageIO.write(image, "jpg", new File("modifica.png"));
    }
}
