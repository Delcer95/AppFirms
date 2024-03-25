package com.example.appfirms.Config;

public class Firmas {
    private byte[] signature;
    private String id, nombre;

    public Firmas(byte[] signature, String id, String nombre) {
        this.signature = signature;
        this.id = id;
        this.nombre = nombre;
    }

    public byte[] getSignature() {
        return signature;
    }

    public void setSignature(byte[] signature) {
        this.signature = signature;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
