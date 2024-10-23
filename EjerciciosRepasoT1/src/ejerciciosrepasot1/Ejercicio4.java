/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ejerciciosrepasot1;

import java.io.File;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class Ejercicio4 {
    public static void main(String[] args) {
        File dir = new File(args[0]);
        if (dir.exists() && dir.isDirectory()){
            //Crear 2 listas: una para las extensiones y otra para contarlas
            ArrayList<String> extensions = new ArrayList<>();
            ArrayList<Integer> cont = new ArrayList<>();
            
            contExtensions (dir, extensions, cont);
            
            for(int i=0; i<extensions.size();i++){
                System.out.println("Extension: "+extensions.get(i)+" Nºextensiones: "+cont.get(i));
            }
            
            
        }
    }
    
    public static void contExtensions (File dir, ArrayList<String> extensions, ArrayList<Integer> cont){
        //Recorremos el directorio y almacenamos el interior en un array
        File[] listFiles = dir.listFiles();
            if (listFiles.length !=0){
                for (File f: listFiles){
                    //Nos aseguramos que sea un fichero
                    if (f.isFile()){
                        String extension = getExtension(f);
                        //Buscamos la extension en la lista(Si no está indice = -1)
                        int indice = extensions.indexOf(extension);
                        if (indice == -1){
                            extensions.add(extension);
                            //  Si no esta en la lista se cuenta por primera vez
                            cont.add(1);
                        }else{
                            //cont +1 en la posicion en la que se ha encontrado la extension
                            cont.set(indice, cont.get(indice)+1);
                        }
                    }else if (f.isDirectory()){
                        contExtensions(f, extensions, cont);
                    }else{
                        System.out.println("Archivo no identificado: "+f);
                    }
                }
            }
    }
    
    public static String getExtension(File f){
        String[] puntos = f.getName().split(".");
        String extension = puntos[puntos.length-1];
        return extension;

    }
}
