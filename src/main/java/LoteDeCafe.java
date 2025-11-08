package com.mycompany.coffeetechapp;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class LoteDeCafe implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombreLote;
    private String variedad;
    private ArrayList<Sensor> listaSensores;

    public LoteDeCafe(String nombreLote, String variedad) {
        this.nombreLote = nombreLote;
        this.variedad = variedad;
        this.listaSensores = new ArrayList<>();
    }
    
    public void agregarSensor(Sensor sensor) {
        this.listaSensores.add(sensor);
    }
    
    public String getNombreLote() {
        return nombreLote;
    }

    public String getReporteActual() {
        StringBuilder reporte = new StringBuilder();
        reporte.append("Reporte para Lote '").append(nombreLote).append("':\n");
        
        // DEMOSTRACIÓN DE POLIMORFISMO:
        // Se itera sobre la lista de Sensores. El programa no sabe ni le importa
        // si son de Temperatura o Humedad, simplemente llama a sus métodos.
        for (Sensor sensor : listaSensores) {
            LecturaSensor ultimaLectura = sensor.getUltimaLectura();
            if (ultimaLectura != null) {
                reporte.append("> ").append(sensor.getTipo()).append(": ").append(ultimaLectura.toString()).append("\n");
            } else {
                reporte.append("> ").append(sensor.getTipo()).append(": Sin lecturas aún.\n");
            }
        }
        return reporte.toString();
    }
    
    public ArrayList<Sensor> getListaSensores() {
        return listaSensores;
    }
}