/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.practicahibernate;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.dialect.function.TransactSQLStrFunction;

/**
 *
 * @author Claudia
 */
public class CompraDAO {

    public static void buyActivity(int idActivity, int idClient) {
        Session session = Conexion.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            //Comprobar que actividad y cliente existe
            Cliente cliente = ClienteDAO.getById(idClient);
            Actividad actividad = ActividadDAO.getById(idActivity);
            LocalDate hoy = LocalDate.now();
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
            String fechaFormateada = hoy.format(formato);
            if (cliente != null && actividad != null) {
                //Ver si la actividad tiene plazas disponibles y es futura
                List<Actividad> actividades = session.createQuery("FROM Actividad WHERE id=:idActivity AND plazasDisponibles>0 AND fecha>:fechaFormateada", Actividad.class)
                        .setParameter("idActivity", idActivity)
                        .setParameter("fechaFormateada", fechaFormateada)
                        .getResultList();
                //Si hay actividades optimas --> Compramos
                if (!actividades.isEmpty()) {
                    Compra compra = new Compra(null, fechaFormateada, cliente, actividad);
                    session.persist(compra);
                    //Reducimos el numero de plazas en actividad 
                    actividad.setPlazasDisponibles(actividad.getPlazasDisponibles() - 1);
                    session.merge(actividad);
                    transaction.commit();
                    System.out.println("Se ha realizado la compra correctamente");
                } else {
                    System.out.println("No ha sido posible realizar la compra, estas intentando comprar un actividad no disponible");
                }
            }

        } catch (Exception e) {
            System.err.println("Error al comprar actividad: " + e.getLocalizedMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }

    /*
    Sólo se podrá cancelar una compra de una actividad 
    que aún no se haya realizado (con fecha futura). 
    Cuando se cancela una actividad se debe aumentar el número de plazas disponibles.
     */
    public static void cancelBuys(int idActivity, int idClient) {
        Session session = Conexion.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Actividad act = ActividadDAO.getById(idActivity);
            Cliente cli = ClienteDAO.getById(idClient);
            //Obtengo las compras
            List<Compra> compras = session.createQuery("FROM Compra WHERE idActividad=:act AND idCliente=:cli", Compra.class)
                    .setParameter("act", act)
                    .setParameter("cli", cli)
                    .list();
            //Recorremos las compras y obetenemos la actividad
            for (Compra c : compras) {
                Actividad actividad = c.getIdActividad();
                //Vemos si es en el futuro
                LocalDate hoy = LocalDate.now();
                DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy/MM/dd");
                String fechaFormateada = hoy.format(formato);
                List<Actividad> ac = session.createQuery("FROM Actividad WHERE id=:id AND fecha>:f", Actividad.class)
                        .setParameter("id", actividad.getId())
                        .setParameter("f", fechaFormateada)
                        .list();
                //Si hay actividades futuras --> se borra la plaza
                if (!ac.isEmpty()) {
                    for (Actividad a : ac) {
                        a.setPlazasDisponibles(a.getPlazasDisponibles() + 1);
                        session.merge(a);
                        System.out.println("Se ha cancelado la compra correctamente");
                    }
                    session.remove(c);
                } else {
                    System.out.println("No se puede cancelar la compra, la actividad ya ha pasado");
                }
                
            }
            transaction.commit();

        } catch (Exception e) {
            System.err.println("Error al cancelar la compra: " + e.getLocalizedMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            session.close();
        }
    }
}
