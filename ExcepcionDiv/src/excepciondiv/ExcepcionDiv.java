/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package excepciondiv;

/**
 *
 * @author Claudia
 */
public class ExcepcionDiv {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int a, b;
        a=5; b=2;
        try{
            System.out.println(a+"/"+b+"="+a/b);
        }catch (ArithmeticException e){
            System.err.println("No se ha podido realizar la operación");
        }
        
        b=0;
        try{
            System.out.println(a+"/"+b+"="+a/b);
        }catch (ArithmeticException e){
            System.err.println("No se ha podido realizar la operación");
        }
        
        b=3;
        try{
            System.out.println(a+"/"+b+"="+a/b);
        }catch (ArithmeticException e){
            System.err.println("No se ha podido realizar la operación");
        }

    }
}

    
    
    

