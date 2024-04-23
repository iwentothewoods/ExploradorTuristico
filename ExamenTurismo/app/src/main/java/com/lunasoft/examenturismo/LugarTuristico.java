package com.lunasoft.examenturismo;

import java.io.Serializable;

public class LugarTuristico implements Serializable {

    private String nombre;
    private Double lat;
    private Double lon;
    private String descripcion;
    private String horarios;
    private String direccion;
    private int foto;
    private RubroLugar rubro;

    public enum RubroLugar {
        COMIDA,
        ALOJAMIENTO,
        ENTRETENIMIENTO
    }

    public LugarTuristico(String nombre, String direccion, Double lat, Double lon, String descripcion, int foto, String horarios, RubroLugar rubro) {
        this.nombre = nombre;
        this.lat = lat;
        this.lon = lon;
        this.descripcion = descripcion;
        this.foto = foto;
        this.horarios = horarios;
        this.direccion = direccion;
        this.rubro = rubro;
    }

    public RubroLugar getRubro() {
        return rubro;
    }

    public void setRubro(RubroLugar rubro) {
        this.rubro = rubro;
    }

    public String getHorarios() {
        return horarios;
    }

    public void setHorarios(String horarios) {
        this.horarios = horarios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    public Double getLon() {
        return lon;
    }

    public void setLon(Double lon) {
        this.lon = lon;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getUbicacion() {
        return "Latitud " + this.lat + " - Longitud: " + this.lon;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
}
