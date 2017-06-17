package com.example.luys117.citnova;


public class Mensajes {
    String autor, contenido;
    public Mensajes(){

    }

    public Mensajes(String autor,String contenido) {
        this.autor = autor;
        this.contenido = contenido;
    }

    public String getAutor(){
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
