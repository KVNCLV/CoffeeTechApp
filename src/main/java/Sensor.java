package com.mycompany.coffeetechapp;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Sensor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String idSensor;
    protected String tipo;
    protected ArrayList<LecturaSensor> historialLecturas;

    public Sensor(String idSensor, String tipo) {
        this.idSensor = idSensor;
        this.tipo = tipo;
        this.historialLecturas = new ArrayList<>();
    }

    //Método abstracto que obliga a las clases hijas a implementar su propia lógica de lectura
    public abstract LecturaSensor leerValor();

    public LecturaSensor getUltimaLectura() {
        if (historialLecturas.isEmpty()) {
            return null;
        }
        return historialLecturas.get(historialLecturas.size() - 1);
    }
    
    public String getTipo() {
        return tipo;
    }
}