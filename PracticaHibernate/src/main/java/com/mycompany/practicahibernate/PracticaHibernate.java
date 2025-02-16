/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.practicahibernate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Claudia
 */
public class PracticaHibernate {
    private static final Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        String opc;
        do {
            opc = getMenuOption();
            switch (opc) {
                case "1":
                    addProveedor();
                    break;
                case "2":
                    updateProveedor();
                    break;
                case "3":
                    deleteProveedor();
                    break;
                case "4":
                    addActivity();
                    break;
                case "5":
                    deleteActivity();
                    break;
                case "6":
                    addClient();
                    break;
                case "7":
                    updateClient();
                    break;
                case "8":
                    deleteClient();
                    break;
                case "9":
                    buyActivity();
                    break;
                case "10":
                    cancelBuys();
                    break;
                case "11":
                    ListAllClients();
                    break;
                case "12":
                    listFututeActivities();
                    break;
                case "13":
                    listDetailsClient();
                    break;
                case "14":
                    listDetailsProveedor();
                    break;
                case "15":
                    listDetalleActivity();
                    break;

            }
        } while (!opc.equals("16"));
    }
    
    private static String getMenuOption() {
        System.out.println("\n Introduce una opción:");
        System.out.println("1. Añadir proveedor");
        System.out.println("2. Modificar proveedor");
        System.out.println("3. Borrar proveedor");
        System.out.println("4. Añadir actividad");
        System.out.println("5. Borrar actividad");
        System.out.println("6. Añadir un cliente");
        System.out.println("7. Modificar cliente");
        System.out.println("8. Borrar cliente");
        System.out.println("9. Comprar actividad");
        System.out.println("10. Cancelar compra");
        System.out.println("11. Listar todos los clientes");
        System.out.println("12. Listar todas las actividades futuras");
        System.out.println("13. Listar detalles de un cliente");
        System.out.println("14. Listar detalles de un proveedor");
        System.out.println("15. Listar detalles de una actividad");
        System.out.println("16. Salir");
        
        return sc.nextLine();
    }
    
    private static void addProveedor(){
        
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe el EMAIL: ");
        String email = sc.nextLine();
        System.out.println("Escribe el CIF: ");
        String cif = sc.nextLine();
        
        Proveedor proveedor = new Proveedor(null, nombre, email, cif);
        ProveedorDAO.addProveedor(proveedor);
    }
    
    private static void updateProveedor(){
        System.out.println("Escribe el id: ");
        int id = Integer.valueOf(sc.nextLine());
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe el email: ");
        String email = sc.nextLine();
        System.out.println("Escribe el cif: ");
        String cif = sc.nextLine();
        
        ProveedorDAO.updateProveedor(id, nombre, email, cif);
    }
    
    
    private static void deleteProveedor(){
     
        System.out.println("Escribe el id: ");
        int id = Integer.valueOf(sc.nextLine());
        
        
        ProveedorDAO.deleteProveedor(id);
    }
   
    private static void addActivity(){
        try {
            System.out.println("Dame el nombre");
            String nombre = sc.nextLine();
            System.out.println("Dame la fecha");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(sc.nextLine());
            System.out.println("Dame la ubicación");
            String ubicacion = sc.nextLine();
            System.out.println("Dame las plazas");
            int plazas = Integer.valueOf(sc.nextLine());
            System.out.println("Dame el cif del proveedor");
            String cif = sc.nextLine();
            
            Actividad actividad = new Actividad(nombre, fecha, ubicacion, plazas);
            ActividadDAO.addActivity(actividad, cif);
        } catch (ParseException ex) {
            Logger.getLogger(PracticaHibernate.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   
    
    private static void deleteActivity(){
        System.out.println("Introduce el ID de la actividad: ");
        int id = Integer.valueOf(sc.nextLine());
        
        ActividadDAO.deleteActivity(id);
        
    }

    private static void addClient(){
        
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe el email: ");
        String email = sc.nextLine();
        
        Cliente cliente = new Cliente(null, nombre, email);
        ClienteDAO.addClient(cliente);
        
    }
    
    private static void updateClient(){
        
        System.out.println("Escribe el id: ");
        int id = Integer.valueOf(sc.nextLine());
        System.out.println("Escribe el nombre: ");
        String nombre = sc.nextLine();
        System.out.println("Escribe el email: ");
        String email = sc.nextLine();
        
        ClienteDAO.updateClient(id, nombre, email);
        
    }
    
    
    private static void deleteClient(){
        
        System.out.println("Escribe el id: ");
        int id = Integer.valueOf(sc.nextLine());
        
        ClienteDAO.deleteClient(id);
    }
    
    
    private static void buyActivity(){
        
        System.out.println("Escribe el id de la actividad: ");
        int idActivity = Integer.valueOf(sc.nextLine());
        System.out.println("Escribe el id del cliente: ");
        int idClient = Integer.valueOf(sc.nextLine());
        
        CompraDAO.buyActivity(idActivity, idClient);
        
    
    }
    
    
    private static void cancelBuys(){
        System.out.println("Escribe el id de la actividad: ");
        int idActivity = Integer.valueOf(sc.nextLine());
        System.out.println("Escribe el id del cliente: ");
        int idClient = Integer.valueOf(sc.nextLine());
        
        CompraDAO.cancelBuys(idActivity, idClient);
    }

    private static void ListAllClients(){
        List<Cliente> clientes = ClienteDAO.listAllClients();
        System.out.println("ID    |  Nombre  | Email");
        for(Cliente c:clientes){
            System.out.println(c.getId()+"           "+c.getNombre()+"       "+c.getEmail());
        }
        
    }

    private static void listFututeActivities(){
        List<Actividad> actividades = ActividadDAO.listFutureActivities();
        System.out.println("ID    |  Nombre              |  Fecha            |  Plazas  |  Ubicacion                     |  Nombre proveedor");
        for(Actividad a:actividades){
            System.out.println(a.getId()+"           "+a.getNombre()+"       "+a.getFecha()+"         "+a.getPlazasDisponibles()+"      "+a.getUbicacion()+"    "+a.getIdProveedor().getId());
        }
        
    }
    
    private static void listDetailsClient(){
        System.out.println("Escribe el id del cliente: ");
        int id = Integer.valueOf(sc.nextLine());
        
        List<Cliente> clientes =ClienteDAO.listDetailsClient(id);
        for(Cliente c: clientes){
            System.out.println("ID: "+c.getId());
            System.out.println("Nombre: "+c.getNombre());
            System.out.println("Email: "+c.getEmail());
            System.out.println("Compras realizadas");
            System.out.println("ID Actividad | Nombre actividad | Ubicación | Nombre proveedor | Fecha actividad | Fecha compra");
            List<Compra> compras = c.getCompraList();
            
            for(Compra com:compras){
                Actividad a = com.getIdActividad();
                Proveedor p = a.getIdProveedor();
                System.out.println(a.getId()+"         "+a.getNombre()+"     "+a.getUbicacion()+"         "+p.getId()+"        "+a.getFecha()+"         "+com.getFechaCompra());
            }
        }
        
    }

    private static void listDetailsProveedor(){
        System.out.println("Escribe el id del proveedor: ");
        int id = Integer.valueOf(sc.nextLine());
        
        List<Proveedor> proveedores = ProveedorDAO.listDetailsProveedor(id);
        //Listar proveedor
        for(Proveedor p:proveedores){
            System.out.println("ID: "+p.getId());
            System.out.println("Nombre: "+p.getNombre());
            System.out.println("Email: "+p.getEmail());
            System.out.println("CIF: "+p.getEmail());
            List<Actividad> actividades = p.getActividadList();
            System.out.println("ID | Nombre | Fecha | Ubicación | Plazas disponibles");
            for(Actividad a:actividades){
                System.out.println(a.getId()+"|"+a.getNombre()+"|"+a.getFecha()+"|"+a.getUbicacion()+"|"+a.getPlazasDisponibles());
            }
        }
    }
    
    /*
    Listar detalles de una actividad: 
    Se pedirá el ID de la actividad a mostrar 
    y se mostrarán los siguientes datos:
ID: X
Nombre: XXXX
Fecha: XX/XX/XXXX
Ubicación: XXXXXXXX
Plazas disponibles: X
ID Proveedor: X
Nombre proveedor: XXXX

Clientes:
ID | Nombre | Email | Fecha compra
... | ... | ... | ... 

    */
    private static void listDetalleActivity(){

        System.out.println("Escribe el id de la actividad: ");
        int id = Integer.valueOf(sc.nextLine());
        
        List<Actividad> actividades = ActividadDAO.listDetalleActivity(id);
        for(Actividad a: actividades){
            System.out.println("ID: "+a.getId());
            System.out.println("Nombre: "+a.getNombre());
            System.out.println("Fecha: "+a.getFecha());
            System.out.println("Ubicacion: "+a.getUbicacion());
            System.out.println("Plazas Disponibles: "+a.getPlazasDisponibles());
            System.out.println("ID Proveedor: "+a.getIdProveedor());
            System.out.println("Nombre Proveedor: "+a.getIdProveedor().getNombre());
            
            System.out.println("ID | Nombre | Email | Fecha compra");
            List<Compra> compras = a.getCompraList();
            for(Compra c:compras){
                System.out.println(c.getIdCliente()+"|"+c.getIdCliente().getNombre()+"|"+c.getIdCliente().getEmail()+"|"+c.getFechaCompra());
            }
            
        }
    }
}
