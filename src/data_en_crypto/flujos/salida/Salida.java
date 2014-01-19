/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Paquete para flujos de salida y su superclase Salida.
 */
package data_en_crypto.flujos.salida;

import data_en_crypto.flujos.Flujo;
import java.io.File;
import java.io.IOException;

/**
 * Super clase abstracta para flujos de salida
 * @author nyx
 */
abstract public class Salida extends Flujo{
    
    /**
     * Archivo de salida (si es este tipo de flujo).
     */
    protected File f = null;

    /**
     * Constuctor vacia. Hace nada.
     */
    public Salida() {
        super();
    }

    /**
     * Constructor que define un archivo para escribir la salida.
     * @param f archivo donde escribimos la salida
     */
    public Salida(File f) {
        this.f = f;
    }
    
    /**
     * Poner datos addicionales en este flujo.
     * @param s los datos addicionales.
     */
    public void anadirDatos(String s){
        this.data = this.data + s;
    }
    
    /**
     * Escribir literalmente al flujo de salida
     * @param s String para escribir
     * @throws IOException si no puede escribir al flujo de salida
     */
    public abstract void imprimir(String s) throws IOException;
    /**
     * Escribir al flujo de salida, poniendo un salto de linea al final
     * @param s String para escribir
     * @throws IOException si no puede escribir al flujo de salida
     */
    public abstract void imprimirln(String s) throws IOException;
    /**
     * Escribir al flujo de salida con todos los datos de este flujo
     * @throws IOException si no puede escribir al flujo de salida
     */
    public abstract void imprimirtodo() throws IOException;
}
