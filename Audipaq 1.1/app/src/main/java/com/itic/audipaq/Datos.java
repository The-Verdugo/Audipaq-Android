package com.itic.audipaq;

import java.io.Serializable;

public class Datos implements Serializable {
    private int id;
    private String Nombre;
    private int Imagen;
    private String Empresa;

    public Datos(int id, String nombre, int imagen, String Empresa) {
        this.id = id;
        Nombre = nombre;
        Imagen = imagen;
        this.Empresa = Empresa;
    }

    public String getEmpresa() {
        return Empresa;
    }

    public void setEmpresa(String empresa) {
        Empresa = empresa;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public int getImagen() {
        return Imagen;
    }

    public void setImagen(int imagen) {
        Imagen = imagen;
    }
}
