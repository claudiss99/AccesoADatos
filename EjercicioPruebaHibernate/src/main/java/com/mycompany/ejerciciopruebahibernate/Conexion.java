/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejerciciopruebahibernate;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Claudia
 */
public class Conexion {
    public static void main(String[] args) {
        //Configuración de Hibernate
        Configuration configuration = new Configuration().configure(new File("hibernate.cfg.xml"));
    
        //Se mapean las clases
        configuration.addAnnotatedClass(Cliente.class);
        
        //Crear una sesión para interactuar con la base de datos
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        
        Transaction transaction = null;
        
        try{
            //Iniciar una transaccion
            transaction = session.beginTransaction();
            
            Cliente cliente = new Cliente(null, "Carlos Martínez", "carlos@email.com", "2024-12-26", null);
            //Guarrda el objeto Cliente en la base de datos
            session.persist(cliente);
            System.out.println("Cliente guardado correctamente");
            //Confirmar la transacción
            transaction.commit();
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback(); //Si hay error, deshacer la transacción
            }
            e.printStackTrace();
        }finally{
            session.close(); //Cerrar la sesión
            sessionFactory.close();//Cerrar la fábrica de sesiones
        }
    }
}
