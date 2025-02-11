/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.ejercicio5empleadoshibernate;

import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Claudia
 */
public class ProyectoEmpleadoDAO {
    
    public static void addproyectEmple(Proyecto proyecto, String[] empleados){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        
        try{
            transaction = session.beginTransaction();
            session.persist(proyecto);
            
            ArrayList<Empleado> listEmpleados = new ArrayList<>();
            //Vincular empleados
            for(String e: empleados){
                Empleado empleado = EmpleadoDAO.getByID(Integer.valueOf(e));
                //Arraylist de empleados 
                listEmpleados.add(empleado);
            }
            //Metemos los empleados al proyecto
            proyecto.setEmpleado(listEmpleados);
            session.merge(proyecto);
            transaction.commit();
            //Si todo correcto --> quiero ID
            int id = proyecto.getId();
            System.out.println("Proyecto añadido correctamente. Tiene el id: "+id);
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.err.println("Error al añadir proyecto: "+e.getLocalizedMessage());
        }finally{
            Conexion.close();
        }
    }

    public static void addEmpleToProyect(int idProyect, int idEmpleado){
        //Se da por hecho que existe el empleado y el proyecto
        
        //Buscamos por id para obtener el proyecto
        Proyecto proyecto = ProyectoDAO.getByID(idProyect);
        //Buscamos por id para obtener el empleado
        Empleado empleado = EmpleadoDAO.getByID(idEmpleado);
        ArrayList<Empleado> empleados = new ArrayList<>();
        empleados.add(empleado);
        
        ProyectoEmpleadoDAO.addEmpleados(empleados, proyecto);
        
    }
    
    public static void addEmplesToProyect(int idProyect, String[] empleados){
        //Se da por hecho que existe el empleado y el proyecto
        //Buscamos por id para obtener el proyecto
        Proyecto proyecto = ProyectoDAO.getByID(idProyect);
        
        ArrayList<Empleado> listEmpleados = new ArrayList<>();
        //Vincular empleados
        for(String e: empleados){
            //Buscamos por id para obtener el empleado
            Empleado empleado = EmpleadoDAO.getByID(Integer.valueOf(e));
            //Arraylist de empleados 
            listEmpleados.add(empleado);
        }
        
        ProyectoEmpleadoDAO.addEmpleados(listEmpleados, proyecto);
    }
    
    public static void addEmpleados(ArrayList<Empleado> empleados, Proyecto proyecto){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        try{
            transaction = session.beginTransaction();
            
            proyecto.setEmpleado(empleados);
            transaction.commit();
            System.out.println("Proyecto modificado con éxito");
        }catch(Exception e){
            if(transaction != null){
                transaction.rollback();
            }
            System.err.println("Error al añadir proyecto: "+e.getLocalizedMessage());
        }finally{
            Conexion.close();
        }
    }
    
        /*
        Eliminar un empleado de un proyecto: Se borrará un empleado de un proyecto. Se pedirá el ID del proyecto y el ID del empleado.

        */
    public static void deleteEmpleFromProyect(int idProyect, int idEmpleado){
        Session session = Conexion.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            //Obtenemos proyecto
            Proyecto proyecto = session.createQuery("FROM Proyecto p WHERE p.id = :idProyect", Proyecto.class)
                    .setParameter("idProyect", idProyect)
                    .getSingleResult();

            //Obtenemos empleado
            Empleado empleado = session.createQuery("FROM Empleado e WHERE e.id = :idEmpleado", Empleado.class)
                    .setParameter("idEmpleado", idEmpleado)
                    .getSingleResult();

            //Verificar si el empleado esta asociado al proyecto
            if (!proyecto.getEmpleado().contains(empleado)) {
                throw new Exception("El empleado no está asociado al proyecto");
            }

            empleado.getProyecto().remove(proyecto);
            proyecto.getEmpleado().remove(empleado);

            session.merge(empleado);
            session.merge(proyecto);
            transaction.commit();
            System.out.println("Empleado " + idEmpleado + " eliminado del proyecto " + idProyect);

        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            Conexion.close();
        }
    }
        
}
