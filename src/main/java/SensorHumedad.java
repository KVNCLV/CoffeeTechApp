package com.mycompany.coffeetechapp;

import java.util.concurrent.ThreadLocalRandom;

public class SensorHumedad extends Sensor {
    public SensorHumedad(String idSensor) {
        super(idSensor, "Humedad del Suelo");
    }

    @Override
    public LecturaSensor leerValor() {
        // Simula una lectura de humedad del suelo (ej. entre 60% y 80%)
        double valor = ThreadLocalRandom.current().nextDouble(60.0, 80.0);
        LecturaSensor nuevaLectura = new LecturaSensor(valor, "%");
        this.historialLecturas.add(nuevaLectura);
        return nuevaLectura;
    }
}
