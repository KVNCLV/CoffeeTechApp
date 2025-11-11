package com.mycompany.coffeetechapp;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Finca finca = Finca.cargarEstado();
        
        //Si no hay un estado guardado, creamos una finca nueva con datos de ejemplo
        if (finca == null) {
            finca = new Finca("La Esperanza");
            finca.agregarUsuario(new Usuario("KCalvo", "1234"));

            // Lote 1 con dos tipos de sensores
            LoteDeCafe lote1 = new LoteDeCafe("El Mirador", "Caturra");
            lote1.agregarSensor(new SensorTemperatura("TEMP-01"));
            lote1.agregarSensor(new SensorHumedad("HUM-01"));
            finca.agregarLote(lote1);
            
            // Lote 2
            LoteDeCafe lote2 = new LoteDeCafe("La Candelaria", "Geisha");
            lote2.agregarSensor(new SensorTemperatura("TEMP-02"));
            finca.agregarLote(lote2);
        }

        //Se inicia el simulador de sensores en segundo plano
        SensorSimulator simulator = new SensorSimulator(finca);
        simulator.start();
        
        Scanner scanner = new Scanner(System.in);
        Usuario usuarioLogueado = null;

        //Bucle de Login (HU-01)
        while (usuarioLogueado == null) {
            System.out.println("\nBienvenido a CoffeeTech");
            System.out.println("---------------------------------");
            System.out.print("Usuario: ");
            String username = scanner.nextLine();
            System.out.print("Contraseña: ");
            String password = scanner.nextLine();

            usuarioLogueado = finca.validarUsuario(username, password);

            if (usuarioLogueado == null) {
                System.out.println("-> Usuario o contraseña incorrectos. Intente de nuevo.");
            } else {
                System.out.println("\n¡Login exitoso! Bienvenido, " + usuarioLogueado.getUsername() + ".");
            }
        }

        //Bucle del Menú Principal
        boolean salir = false;
        while (!salir) {
            System.out.println("\n--- Coffee Tech --- Finca \"" + finca.getNombreFinca() + "\" ---");
            System.out.println("Menú de Acciones:");
            System.out.println("1. Ver lista de Lotes (HU-02)");
            System.out.println("2. Ver Dashboard de un Lote (HU-03)");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");

            try {
                int opcion = scanner.nextInt();
                scanner.nextLine(); //Limpiar buffer

                switch (opcion) {
                    case 1: //HU-02: Visualización de Lotes
                        System.out.println("\nLotes Registrados:");
                        for (int i = 0; i < finca.getLotes().size(); i++) {
                            System.out.println((i + 1) + ". " + finca.getLotes().get(i).getNombreLote());
                        }
                        break;
                    case 2: // HU-03: Dashboard de Datos en Tiempo Real
                        System.out.print("Seleccione el número del lote para ver su reporte: ");
                        int numLote = scanner.nextInt() - 1;
                        scanner.nextLine();
                        if (numLote >= 0 && numLote < finca.getLotes().size()) {
                            System.out.println("\n" + finca.getLotes().get(numLote).getReporteActual());
                        } else {
                            System.out.println("-> Número de lote inválido.");
                        }
                        break;
                    case 3:
                        salir = true;
                        break;
                    default:
                        System.out.println("-> Opción no válida.");
                }
            } catch (InputMismatchException e) {
                System.out.println("-> Error: Por favor, ingrese un número.");
                scanner.nextLine();
            }
        }
        
        //Al salir, se guarda el estado y se detiene el simulador
        System.out.println("Cerrando aplicación...");
        simulator.stopSimulator();
        Finca.guardarEstado(finca);
        scanner.close();
        System.out.println("Aplicación cerrada.");
    }
}
