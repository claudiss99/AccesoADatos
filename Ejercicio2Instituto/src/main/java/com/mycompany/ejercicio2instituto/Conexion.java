/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio2instituto;

import java.io.File;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Claudia
 */
public class Conexion {
    private static Configuration configuration = null;
    private static SessionFactory sessionFactory = null;
    private static Session session = null;
    
    private static void init(){
        if(configuration == null){
            configuration = new Configuration().configure(new File("hibernate.cfg.xml"));
            configuration.addAnnotatedClass(Profesor.class);
            configuration.addAnnotatedClass(Asignatura.class);
        }
        
        if(sessionFactory == null || sessionFactory.isClosed()){
            sessionFactory = configuration.buildSessionFactory();
        }
        
    }
    
    public static Session getSession(){
        if(session == null || !session.isOpen()){
            init();
            session= sessionFactory.openSession();
        }
        
        return session;
               
    }
    
    public static void close(){
        session.close();
        //sessionFactory.close();
    }
}
