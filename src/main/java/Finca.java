package com.mycompany.coffeetechapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Clase que representa el estado completo de la aplicación.
 * Contiene todos los usuarios y lotes, y maneja la persistencia de datos.
 *
 * @author Kevin Calvo
 */
public class Finca implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombreFinca;
    private ArrayList<Usuario> usuarios;
    private ArrayList<LoteDeCafe> lotes;

    public Finca(String nombreFinca) {
        this.nombreFinca = nombreFinca;
        this.usuarios = new ArrayList<>();
        this.lotes = new ArrayList<>();
    }
    
    //Métodos para gestionar los datos de la finca
    public void agregarUsuario(Usuario user) { 
        this.usuarios.add(user); 
    }
    
    public void agregarLote(LoteDeCafe lote) { 
        this.lotes.add(lote); 
    }
    
    public ArrayList<LoteDeCafe> getLotes() { 
        return this.lotes; 
    }
    
    public String getNombreFinca() { 
        return nombreFinca; 
    }

    public Usuario validarUsuario(String username, String password) {
        for (Usuario user : usuarios) {
            if (user.getUsername().equalsIgnoreCase(username) && user.checkPassword(password)) {
                return user; // Devuelve el objeto Usuario si la validación es exitosa
            }
        }
        return null; //Devuelve null si el usuario no se encuentra o la contraseña es incorrecta
    }
    
     /**
     * Guarda el objeto Finca completo en un archivo binario.
     * @param finca El objeto Finca que contiene todo el estado de la aplicación.
     */
    public static void guardarEstado(Finca finca) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("finca.dat"))) {
            oos.writeObject(finca);
            System.out.println("-> Estado de la finca guardado exitosamente.");
        } catch (IOException e) {
            System.err.println("Error al guardar el estado: " + e.getMessage());
        }
    }
    
    /**
     * Carga el objeto Finca desde un archivo. Si el archivo no existe, devuelve null.
     * @return El objeto Finca restaurado, o null si no se encontró un estado previo.
     */
    public static Finca cargarEstado() {
        File archivo = new File("finca.dat");
        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                System.out.println("-> Cargando estado previo de la finca...");
                return (Finca) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error al cargar el estado, se creará uno nuevo: " + e.getMessage());
                return null;
            }
        }
        System.out.println("-> No se encontró estado previo. Creando nueva finca...");
        return null;
    }
}