package com.itic.audipaq;

import java.io.Serializable;

public class DatosEventos implements Serializable {
    private int id;
    private String id_detalle;
    private String fecha;
    private String area;
    private String prioridad;
    private String auditor;

    public DatosEventos(int id, String id_detalle, String fecha, String area, String prioridad, String auditor) {
        this.id = id;
        this.id_detalle = id_detalle;
        this.fecha = fecha;
        this.area = area;
        this.prioridad = prioridad;
        this.auditor = auditor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(String id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(String prioridad) {
        this.prioridad = prioridad;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }
}
