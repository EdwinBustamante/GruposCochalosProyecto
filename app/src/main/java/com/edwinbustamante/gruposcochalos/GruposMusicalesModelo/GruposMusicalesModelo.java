package com.edwinbustamante.gruposcochalos.GruposMusicalesModelo;

import java.util.Map;

/**
 * Created by EDWIN on 25/09/2017.
 */

public class GruposMusicalesModelo {
    private String nombreGrupo;
    private int perfil;
    private Map hora;
    private int portada;
    private String latitud, longitud;
    private int numeroCel;
    private String informacion;


    public Long getHora() {
        return new Long("1506903372807");
    }

    public void setHora(Map hora) {
        this.hora = hora;
    }

    public GruposMusicalesModelo() {

    }

    public GruposMusicalesModelo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public int getPerfil() {
        return perfil;
    }

    public void setPerfil(int perfil) {
        this.perfil = perfil;
    }

    public GruposMusicalesModelo(String nombreGrupo, int perfil, Map hora, int portada, String latitud, String longitud, int numeroCel, String informacion) {
        this.nombreGrupo = nombreGrupo;
        this.perfil = perfil;
        this.hora = hora;
        this.portada = portada;
        this.latitud = latitud;
        this.longitud = longitud;
        this.numeroCel = numeroCel;

        this.informacion = informacion;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public int getImgPortada() {
        return portada;
    }

    public void setImgPortada(int imgGrupo) {
        this.portada = imgGrupo;
    }

    public String getLatitud() {
        return latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }

    public int getNumeroCel() {
        return numeroCel;
    }

    public void setNumeroCel(int numeroCel) {
        this.numeroCel = numeroCel;
    }

    public String getInformacion() {
        return informacion;
    }

    public void setInformacion(String informacion) {
        this.informacion = informacion;
    }
}
