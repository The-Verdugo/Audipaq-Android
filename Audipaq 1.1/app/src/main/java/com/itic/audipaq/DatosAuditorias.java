package com.itic.audipaq;

import java.io.Serializable;

public class DatosAuditorias implements Serializable {
    private int id;
    private String id_persona;
    private String fechainicio;
    private String estatus;
    private String auditado;
    private String departamento;
    private String Area;

    public DatosAuditorias(int id, String id_persona, String fechainicio, String estatus, String auditado, String departamento, String area) {
        this.id = id;
        this.id_persona = id_persona;
        this.fechainicio = fechainicio;
        this.estatus = estatus;
        this.auditado = auditado;
        this.departamento = departamento;
        Area = area;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_persona() {
        return id_persona;
    }

    public void setId_persona(String id_persona) {
        this.id_persona = id_persona;
    }

    public String getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(String fechainicio) {
        this.fechainicio = fechainicio;
    }

    public String getEstatus() {
        return estatus;
    }

    public void setEstatus(String estatus) {
        this.estatus = estatus;
    }

    public String getAuditado() {
        return auditado;
    }

    public void setAuditado(String auditado) {
        this.auditado = auditado;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String area) {
        Area = area;
    }
}
