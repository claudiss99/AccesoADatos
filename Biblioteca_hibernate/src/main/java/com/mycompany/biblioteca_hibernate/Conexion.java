
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.biblioteca_hibernate;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author usuario
 */
public class Conexion {
    private static Configuration configuration = null;
    private static SessionFactory sessionFactory = null;
    private static Session session = null;
    
    private static void init(){
        if(configuration == null){
            // Configuración de Hibernate
            configuration = new Configuration().configure(new File("hibernate.cfg.xml"));

            //Se mapean las clases
            //configuration.addAnnotatedClass(Libro.class);
            configuration.addAnnotatedClass(Autor.class);

            // Crear una sesión para interactuar con la base de datos
            sessionFactory = configuration.buildSessionFactory();
        }
    }
    
    public static Session getSession(){
        if(session == null || !session.isOpen()){
            init();
            session = sessionFactory.openSession();
        }
        return session;
    }
    
    public static void close(){
        session.close();
        sessionFactory.close();
    }
}
