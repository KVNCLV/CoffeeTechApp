package com.mycompany.coffeetechapp;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class LecturaSensor implements Serializable {
    private static final long serialVersionUID = 1L; //Necesario para la serialización
    
    private double valor;
    private String unidad;
    private Timestamp fechaHora;

    public LecturaSensor(double valor, String unidad) {
        this.valor = valor;
        this.unidad = unidad;
        this.fechaHora = new Timestamp(System.currentTimeMillis());
    }

    @Override
    public String toString() {
        //Formateador para mostrar la fecha y hora de manera legible
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return String.format("%.2f %s (leído a las %s)", valor, unidad, sdf.format(fechaHora));
    }
}
