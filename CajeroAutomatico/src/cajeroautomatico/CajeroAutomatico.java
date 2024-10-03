/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package cajeroautomatico;

import java.util.Scanner;

/**
 *
 * @author Claudia
 */
public class CajeroAutomatico {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         Scanner scanner = new Scanner(System.in);
        
        // Inicializar una cuenta bancaria con datos ficticios
        CuentaBancaria cuenta = new CuentaBancaria("1234567890", "Paco Pérez", 1000.0);
        
        boolean salir = false;

        while (!salir) {
            System.out.println("\n--- Menú Cajero Automático ---");
            System.out.println("1. Consultar saldo");
            System.out.println("2. Depositar dinero");
            System.out.println("3. Retirar dinero");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            
            int opcion = scanner.nextInt();

            try {
                switch (opcion) {
                    case 1:
                        // Consultar saldo
                        System.out.println("Saldo actual: " + cuenta.consultarSaldo());
                        break;
                    case 2:
                        // Depositar dinero
                        System.out.print("Ingrese la cantidad a depositar: ");
                        double deposito = scanner.nextDouble();
                        cuenta.depositar(deposito);
                        break;
                    case 3:
                        // Retirar dinero
                        System.out.print("Ingrese la cantidad a retirar: ");
                        double retiro = scanner.nextDouble();
                        cuenta.retirar(retiro);
                        break;
                    case 4:
                        // Salir del programa
                        salir = true;
                        System.out.println("Gracias por usar el cajero automático. ¡Hasta luego!");
                        break;
                    default:
                        System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (SaldoInsuficienteException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Ha ocurrido un error inesperado: " + e.getMessage());
            }
        }

        scanner.close();
    
    }
    
}
