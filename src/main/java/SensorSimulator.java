package com.mycompany.coffeetechapp;

import java.util.ArrayList;
import java.util.Random;

/**
 * Hilo que se ejecuta en segundo plano para simular la generación de nuevas
 * lecturas de sensores en tiempo real.
 *
 * @author Kevin Calvo
 */
public class SensorSimulator extends Thread {
    private Finca finca;
    private boolean running = true;
    private final Random random = new Random();

    public SensorSimulator(Finca finca) {
        this.finca = finca;
    }

    @Override
    public void run() {
        while (running) {
            try {
                // El simulador se detiene por 5 segundos entre cada lectura
                Thread.sleep(5000);
                
                if (finca == null || finca.getLotes().isEmpty()) {
                    continue; // Si no hay lotes, no hace nada y vuelve a esperar
                }
                
                ArrayList<LoteDeCafe> lotes = finca.getLotes();
                
                // Selecciona un lote al azar
                LoteDeCafe loteAleatorio = lotes.get(random.nextInt(lotes.size()));
                
                if (loteAleatorio.getListaSensores().isEmpty()) {
                    continue; // Si el lote no tiene sensores, no hace nada
                }
                
                ArrayList<Sensor> sensores = loteAleatorio.getListaSensores();
                
                // Selecciona un sensor al azar de ese lote
                Sensor sensorAleatorio = sensores.get(random.nextInt(sensores.size()));
                
                // Genera una nueva lectura para ese sensor
                sensorAleatorio.leerValor();
                
                // System.out.println("[Simulador] Nueva lectura generada para " + sensorAleatorio.getTipo());
                
            } catch (InterruptedException e) {
                // Si el hilo es interrumpido, se detiene de forma segura.
                running = false;
            }
        }
    }
    
    /**
     * Método para detener el bucle del simulador de forma segura.
     */
    public void stopSimulator() {
        this.running = false;
        this.interrupt();
    }
}
