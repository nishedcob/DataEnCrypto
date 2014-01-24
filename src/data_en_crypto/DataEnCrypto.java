/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Paquete principal. Contiene el nucleo del programa y interfaces graficas.
 */
package data_en_crypto;

/*
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 */
import data_en_crypto.cifras.*;
import data_en_crypto.flujos.entrada.*;
import data_en_crypto.flujos.llave.*;
import data_en_crypto.flujos.salida.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 * Nucleo para el programa para cuando corre sin interfaz grafica.
 * @author nyx
 */
public class DataEnCrypto {
    //<editor-fold defaultstate="collapsed" desc=" Nucleo del Programa ">
    /**
     * @deprecated
     * Nucleo de este clase. Corre todo el programa sin interfaz grafica.
     * @param args los argumentos bajo que fue ejecutado el programa.
     */
    public static void main(String[] args) {

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Prueba de cada Cifra ">
    /**
     * Una pequeña programa en la forma de un metodo. Toma argumentos de
     * ejecutacion como parametro y despues muestra como es cada cifra.
     *
     * @param args argumentos de ejecutacion
     */
    public static void prueba(String[] args) {

        System.out.println("---Estado Inicial---");
        String texto = "Hola Mundo!";
        String salida;
        System.out.println("Texto de Entrada: " + texto);
        String clave = "clave";
        System.out.println("Clave: " + clave);
        byte llave = 1;
        System.out.println("Llave: " + llave);
        String librereta = "!odnuM aloH";
        System.out.println("Librereta: " + librereta);
        int[][] matriz = {
            {4, 3, 3},
            {3, 2, 1},
            {1, 1, 3}
        };
        imprimir_matriz("Matriz:\n", matriz);
        char[][] tabla_llave = {
            //0    1    2   2/3  3/4  4/5  5/6  6/7  6/8  7/9   8    9
            {'E', 'T', '\u0000', 'A', 'O', 'N', '\u0000', 'R', 'I', 'S'}, //0
            {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M'}, //1 (2#)
            {'P', 'Q', ' ', 'U', 'V', 'W', 'X', 'Y', 'Z', '.'} //2 (6#)
        };
        imprimir_matriz("Tabla:\n", tabla_llave);
        byte[] clave2 = {'c', 'l', 'a', 'v', 'e'};
        imprimir_arreglo("Segundo Clave: ", clave2);
        System.out.println();

        //<editor-fold defaultstate="collapsed" desc=" AutoTexto -- funciona ">  
        System.out.println("---AutoTexto---");
        System.out.println("Usando clave!");
        AutoTexto at = new AutoTexto(clave);
        salida = at.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = at.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Cesar -- funciona">
        System.out.println("---Cesar---");
        System.out.println("Usando llave!");
        Cesar ces = new Cesar(llave);
        salida = ces.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = ces.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Librereta de Un Solo Uso -- funciona ">
        /**/
        System.out.println("---Librereta de un Solo Uso---");
        System.out.println("Usando Librereta!");
        Librereta_de_un_Solo_Uso lib = new Librereta_de_un_Solo_Uso(librereta);
        salida = lib.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = lib.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        /**/
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Matrices -- funciona ">
        //*
        System.out.println("---Matrices---");
        System.out.println("Usando matriz!");
        Matrices mat = new Matrices(matriz);
        //System.out.println("DET: " + mat.getDET());
        if (mat.getLLAVE() != null) {
            salida = mat.encriptar(texto);
            System.out.println("Encryptado: " + salida);
            salida = mat.decifrar(salida);
            System.out.println("Decifrado:  " + salida);
        }
        //*/
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Vigenere -- funciona ">
        System.out.println("---Vigenere---");
        System.out.println("Usando clave!");
        Vigenere vig = new Vigenere(clave);
        salida = vig.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = vig.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        //</editor-fold>

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Metodos Auxilliares ">
    /**
     * Metodo para imprimir una matriz formado por numeros de tipo int.
     *
     * @param titulo lo que debemos imprimir antes de la matriz
     * @param matriz la matriz para imprimir
     */
    private static void imprimir_matriz(String titulo, int[][] matriz) {
        System.out.print(titulo);
        for (int[] fila : matriz) {
            System.out.print("[\t");
            for (int num : fila) {
                System.out.print(num + "\t");
            }
            System.out.print("]\n");
        }
    }

    /**
     * Metodo para imprimir una tabla (un tipo de matriz especial -- usa
     * caracteres en lugar de numeros de tipo int).
     *
     * @param titulo Lo que queremos imprimir antes de la tabla
     * @param tabla la tabla que queremos imprimir
     */
    private static void imprimir_matriz(String titulo, char[][] tabla) {
        System.out.print(titulo);
        for (char[] fila : tabla) {
            System.out.print("[\t");
            for (char c : fila) {
                System.out.print(c + "\t");
            }
            System.out.print("]\n");
        }
    }

    /**
     * Un metodo para imprimir un arreglo con datos de tipo byte.
     *
     * @param titulo Lo que queremos imprimir antes de imprimir el arreglo.
     * @param clave el arreglo que queremos imprimir.
     */
    private static void imprimir_arreglo(String titulo, byte[] clave) {
        System.out.print(titulo);
        System.out.print('[');
        for (byte b : clave) {
            System.out.print(" " + b);
        }
        System.out.print(" ]");
    }
    //</editor-fold>
}
