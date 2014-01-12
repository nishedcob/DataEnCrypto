/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.entrada;

import data_en_crypto.flujos.Flujo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Superclase para flujos de entrada (incluso los de tipo llave).
 * @author nyx
 */
public class Entrada extends Flujo{
    /**
     * Un Scanner para leer del flujo.
     */
    protected Scanner lector;
    /**
     * el archivo que se lee (si es un flujo de entrada de este tipo).
     */
    protected File f = null;

    /**
     * Constructor: toma la dirrecion del archivo y lo abre para lectura.
     * @param path dirreccion del archivo
     * @throws FileNotFoundException si no se encuentra el archivo
     */
    public Entrada(String path) throws FileNotFoundException {
        super();
        f = new File(path);
        lector = new Scanner(f);
    }
    
    /**
     * Constructor: toma nada como argumentos y construye un tipo de flujo
     * de entrada por teclado.
     */
    public Entrada(){
        super();
        lector = new Scanner(System.in);
    }
}
