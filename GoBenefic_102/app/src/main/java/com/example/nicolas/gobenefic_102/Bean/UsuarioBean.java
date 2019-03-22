package com.example.nicolas.gobenefic_102.Bean;

import java.io.Serializable;
import java.util.Date;

public class UsuarioBean implements Serializable{

    private int TUSU_CODUSUR;
    private String TUSU_CODIDENT;
    private String TUSU_NOMBRE;
    private String TUSU_APELLIDO;
    private String TUSU_CORREO;
    private String TUSU_CLAVIDENT;
    private Date TUSU_FECHNACI;
    private int TUSU_CELULAR;
    private String TUSU_DIRECCION;
    private String TUSU_CODUBIGE;
    private String TUSU_PRESECRE;
    private String TUSU_RESSECRE;
    private String TUSU_ESTADO;
    private String TUSU_TARJETA;
    private String TUSU_TARJCLAV;
    private Date TUSU_FECHALTA;
    private Date TUSU_FECHMODI;
    private String TUSU_USUAMODI;
    private int IMAGEN;

    public int getIMAGEN() {
        return IMAGEN;
    }

    public void setIMAGEN(int IMAGEN) {
        this.IMAGEN = IMAGEN;
    }

    public UsuarioBean(String TUSU_NOMBRE) {
        this.TUSU_NOMBRE = TUSU_NOMBRE;
    }

    public UsuarioBean(String TUSU_NOMBRE, int IMAGEN) {
        this.TUSU_NOMBRE = TUSU_NOMBRE;
        this.IMAGEN = IMAGEN;
    }
    public UsuarioBean(String TUSU_NOMBRE,String TUSU_DIRECCION, int IMAGEN) {
        this.TUSU_NOMBRE = TUSU_NOMBRE;
        this.TUSU_DIRECCION = TUSU_DIRECCION;
        this.IMAGEN = IMAGEN;
    }

    public UsuarioBean() {
    }

    public int getTUSU_CODUSUR() {
        return TUSU_CODUSUR;
    }

    public void setTUSU_CODUSUR(int TUSU_CODUSUR) {
        this.TUSU_CODUSUR = TUSU_CODUSUR;
    }

    public String getTUSU_CODIDENT() {
        return TUSU_CODIDENT;
    }

    public void setTUSU_CODIDENT(String TUSU_CODIDENT) {
        this.TUSU_CODIDENT = TUSU_CODIDENT;
    }

    public String getTUSU_NOMBRE() {
        return TUSU_NOMBRE;
    }

    public void setTUSU_NOMBRE(String TUSU_NOMBRE) {
        this.TUSU_NOMBRE = TUSU_NOMBRE;
    }

    public String getTUSU_APELLIDO() {
        return TUSU_APELLIDO;
    }

    public void setTUSU_APELLIDO(String TUSU_APELLIDO) {
        this.TUSU_APELLIDO = TUSU_APELLIDO;
    }

    public String getTUSU_CORREO() {
        return TUSU_CORREO;
    }

    public void setTUSU_CORREO(String TUSU_CORREO) {
        this.TUSU_CORREO = TUSU_CORREO;
    }

    public String getTUSU_CLAVIDENT() {
        return TUSU_CLAVIDENT;
    }

    public void setTUSU_CLAVIDENT(String TUSU_CLAVIDENT) {
        this.TUSU_CLAVIDENT = TUSU_CLAVIDENT;
    }

    public Date getTUSU_FECHNACI() {
        return TUSU_FECHNACI;
    }

    public void setTUSU_FECHNACI(Date TUSU_FECHNACI) {
        this.TUSU_FECHNACI = TUSU_FECHNACI;
    }

    public int getTUSU_CELULAR() {
        return TUSU_CELULAR;
    }

    public void setTUSU_CELULAR(int TUSU_CELULAR) {
        this.TUSU_CELULAR = TUSU_CELULAR;
    }

    public String getTUSU_DIRECCION() {
        return TUSU_DIRECCION;
    }

    public void setTUSU_DIRECCION(String TUSU_DIRECCION) {
        this.TUSU_DIRECCION = TUSU_DIRECCION;
    }

    public String getTUSU_CODUBIGE() {
        return TUSU_CODUBIGE;
    }

    public void setTUSU_CODUBIGE(String TUSU_CODUBIGE) {
        this.TUSU_CODUBIGE = TUSU_CODUBIGE;
    }

    public String getTUSU_PRESECRE() {
        return TUSU_PRESECRE;
    }

    public void setTUSU_PRESECRE(String TUSU_PRESECRE) {
        this.TUSU_PRESECRE = TUSU_PRESECRE;
    }

    public String getTUSU_RESSECRE() {
        return TUSU_RESSECRE;
    }

    public void setTUSU_RESSECRE(String TUSU_RESSECRE) {
        this.TUSU_RESSECRE = TUSU_RESSECRE;
    }

    public String getTUSU_ESTADO() {
        return TUSU_ESTADO;
    }

    public void setTUSU_ESTADO(String TUSU_ESTADO) {
        this.TUSU_ESTADO = TUSU_ESTADO;
    }

    public String getTUSU_TARJETA() {
        return TUSU_TARJETA;
    }

    public void setTUSU_TARJETA(String TUSU_TARJETA) {
        this.TUSU_TARJETA = TUSU_TARJETA;
    }

    public String getTUSU_TARJCLAV() {
        return TUSU_TARJCLAV;
    }

    public void setTUSU_TARJCLAV(String TUSU_TARJCLAV) {
        this.TUSU_TARJCLAV = TUSU_TARJCLAV;
    }

    public Date getTUSU_FECHALTA() {
        return TUSU_FECHALTA;
    }

    public void setTUSU_FECHALTA(Date TUSU_FECHALTA) {
        this.TUSU_FECHALTA = TUSU_FECHALTA;
    }

    public Date getTUSU_FECHMODI() {
        return TUSU_FECHMODI;
    }

    public void setTUSU_FECHMODI(Date TUSU_FECHMODI) {
        this.TUSU_FECHMODI = TUSU_FECHMODI;
    }

    public String getTUSU_USUAMODI() {
        return TUSU_USUAMODI;
    }

    public void setTUSU_USUAMODI(String TUSU_USUAMODI) {
        this.TUSU_USUAMODI = TUSU_USUAMODI;
    }
}
