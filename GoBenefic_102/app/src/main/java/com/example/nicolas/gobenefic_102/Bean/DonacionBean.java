package com.example.nicolas.gobenefic_102.Bean;

import java.io.Serializable;

public class DonacionBean implements Serializable{

    private int TUSU_CODDON;
    private String TDON_TIPODONAC;
    private String TDON_DESCRIPC;
    private String TDON_TELEFONO;
    private String TDON_ESTADO;
    private int TDON_CODUBIGE;
    private String TDON_DEPARTAMENTO;
    private String TDON_PROVINCIA;
    private String TDON_DISTRITO;
    private String TDON_FECHAINICIO;
    private String TDON_FECHAFIN;
    private int TDON_IMAGEN;
    private String TUSU_CELULAR;

    public String getTUSU_CELULAR() {
        return TUSU_CELULAR;
    }

    public void setTUSU_CELULAR(String TUSU_CELULAR) {
        this.TUSU_CELULAR = TUSU_CELULAR;
    }

    public DonacionBean() {
    }

    public DonacionBean(int TUSU_CODDON, String TDON_TIPODONAC, String TDON_DESCRIPC, String TDON_TELEFONO, String TDON_ESTADO, String TDON_DEPARTAMENTO, String TDON_PROVINCIA, String TDON_DISTRITO, String TDON_FECHAINICIO, String TDON_FECHAFIN, int TDON_IMAGEN, String TUSU_CELULAR) {
        this.TUSU_CODDON = TUSU_CODDON;
        this.TDON_TIPODONAC = TDON_TIPODONAC;
        this.TDON_DESCRIPC = TDON_DESCRIPC;
        this.TDON_TELEFONO = TDON_TELEFONO;
        this.TDON_ESTADO = TDON_ESTADO;
        this.TDON_DEPARTAMENTO = TDON_DEPARTAMENTO;
        this.TDON_PROVINCIA = TDON_PROVINCIA;
        this.TDON_DISTRITO = TDON_DISTRITO;
        this.TDON_FECHAINICIO = TDON_FECHAINICIO;
        this.TDON_FECHAFIN = TDON_FECHAFIN;
        this.TDON_IMAGEN = TDON_IMAGEN;
        this.TUSU_CELULAR= TUSU_CELULAR;
    }

    public int getTUSU_CODDON() {
        return TUSU_CODDON;
    }

    public void setTUSU_CODDON(int TUSU_CODDON) {
        this.TUSU_CODDON = TUSU_CODDON;
    }

    public String getTDON_TIPODONAC() {
        return TDON_TIPODONAC;
    }

    public void setTDON_TIPODONAC(String TDON_TIPODONAC) {
        this.TDON_TIPODONAC = TDON_TIPODONAC;
    }

    public String getTDON_DESCRIPC() {
        return TDON_DESCRIPC;
    }

    public void setTDON_DESCRIPC(String TDON_DESCRIPC) {
        this.TDON_DESCRIPC = TDON_DESCRIPC;
    }

    public String getTDON_TELEFONO() {
        return TDON_TELEFONO;
    }

    public void setTDON_TELEFONO(String TDON_TELEFONO) {
        this.TDON_TELEFONO = TDON_TELEFONO;
    }

    public String getTDON_ESTADO() {
        return TDON_ESTADO;
    }

    public void setTDON_ESTADO(String TDON_ESTADO) {
        this.TDON_ESTADO = TDON_ESTADO;
    }

    public int getTDON_CODUBIGE() {
        return TDON_CODUBIGE;
    }

    public void setTDON_CODUBIGE(int TDON_CODUBIGE) {
        this.TDON_CODUBIGE = TDON_CODUBIGE;
    }

    public String getTDON_DEPARTAMENTO() {
        return TDON_DEPARTAMENTO;
    }

    public void setTDON_DEPARTAMENTO(String TDON_DEPARTAMENTO) {
        this.TDON_DEPARTAMENTO = TDON_DEPARTAMENTO;
    }

    public String getTDON_PROVINCIA() {
        return TDON_PROVINCIA;
    }

    public void setTDON_PROVINCIA(String TDON_PROVINCIA) {
        this.TDON_PROVINCIA = TDON_PROVINCIA;
    }

    public String getTDON_DISTRITO() {
        return TDON_DISTRITO;
    }

    public void setTDON_DISTRITO(String TDON_DISTRITO) {
        this.TDON_DISTRITO = TDON_DISTRITO;
    }

    public String getTDON_FECHAINICIO() {
        return TDON_FECHAINICIO;
    }

    public void setTDON_FECHAINICIO(String TDON_FECHAINICIO) {
        this.TDON_FECHAINICIO = TDON_FECHAINICIO;
    }

    public String getTDON_FECHAFIN() {
        return TDON_FECHAFIN;
    }

    public void setTDON_FECHAFIN(String TDON_FECHAFIN) {
        this.TDON_FECHAFIN = TDON_FECHAFIN;
    }

    public int getTDON_IMAGEN() {
        return TDON_IMAGEN;
    }

    public void setTDON_IMAGEN(int TDON_IMAGEN) {
        this.TDON_IMAGEN = TDON_IMAGEN;
    }
}
