package com.mycompany.coffeetechapp;

import java.util.concurrent.ThreadLocalRandom;

public class SensorTemperatura extends Sensor {
    public SensorTemperatura(String idSensor) {
        super(idSensor, "Temperatura Ambiental");
    }

    @Override
    public LecturaSensor leerValor() {
        // Simula una lectura de temperatura realista para café (ej. entre 18 y 26 grados)
        double valor = ThreadLocalRandom.current().nextDouble(18.0, 26.0);
        LecturaSensor nuevaLectura = new LecturaSensor(valor, "°C");
        this.historialLecturas.add(nuevaLectura);
        return nuevaLectura;
    }
}
