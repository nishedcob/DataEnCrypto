/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Superpaquete para todos los paquetes de flujo. Solo tiene un clase, flujo
 * que es el superclase para todos los flujos.
 */
package data_en_crypto.flujos;

//import data_en_crypto.Proceso;

/**
 * Superclase de todos los flujos (de entrada y de salida). Por lo tanto lo 
 * unico que tiene, mas un constuctor vacia, es un campo para datos y los 
 * metodos necesarios para cambiar y obtener estos datos.
 * @author nyx
 */
public class Flujo {//extends Proceso{
    /**
     * los datos associados con este flujo.
     */
    protected String data;

    /**
     * Un constructor vacio.
     */
    public Flujo() {
    }

    /**
     * Un metodo para leer los datos contenido aqui.
     * @return datos del flujo
     */
    public String getData() {
        return data;
    }

    /**
     * Un metodo para poner datos en el flujo.
     * @param data los datos que queremos poner en el flujo.
     */
    public void setData(String data) {
        this.data = data;
    }
    
    /**
     * un metodo que limpia todo el contenido del variable datos.
     */
    public void limpiar() {
        this.data = "";
    }
}
