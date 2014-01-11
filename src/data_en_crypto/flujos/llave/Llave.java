/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.llave;

//import data_en_crypto.flujos.Flujo;

/**
 * Un objecto llave para almacenar algun tipo de llave.
 * @author nyx
 */
final public class Llave{
    /**
     * Tipo de la llave.
     */
    private final byte TIPO;
    //private final String CLAVE;
    /**
     * Una llave que es un solo numero (para Cesar).
     */
    private final int LLAVE_NUMERICA;
    /**
     * Una llave que es una cadena de caracteres. 
     * Puede ser un clave o mas larga (para AutoTexto, 
     * Librereta de un Solo Uso y Vigenere).
     */
    private final String LIBRERETA;
    /**
     * Una llave que es un matriz de numeros (para 
     * Multiplicacion de Matrices).
     */
    private final int[][] LLAVE_MATRIZ;
    /**
     * Una llave que es un matriz/tabla de caracteres
     * (para VICTOR).
     */
    private final char[][] LLAVE_TABLA;

    public Llave(byte TIPO, int LLAVE_NUMERICA) {
        this.TIPO = TIPO;
        this.LLAVE_NUMERICA = LLAVE_NUMERICA;
        this.LIBRERETA = null;
        this.LLAVE_MATRIZ = null;
        this.LLAVE_TABLA = null;
    }

    public Llave(byte TIPO, String LIBRERETA) {
        this.TIPO = TIPO;
        this.LLAVE_NUMERICA = 0;
        this.LIBRERETA = LIBRERETA;
        this.LLAVE_MATRIZ = null;
        this.LLAVE_TABLA = null;
    }

    public Llave(byte TIPO, int[][] LLAVE_MATRIZ) {
        this.TIPO = TIPO;
        this.LLAVE_NUMERICA = 0;
        this.LIBRERETA = null;
        this.LLAVE_MATRIZ = LLAVE_MATRIZ;
        this.LLAVE_TABLA = null;
    }

    public Llave(byte TIPO, char[][] LLAVE_TABLA) {
        this.TIPO = TIPO;
        this.LLAVE_NUMERICA = 0;
        this.LIBRERETA = null;
        this.LLAVE_MATRIZ = null;
        this.LLAVE_TABLA = LLAVE_TABLA;
    }

    /**
     * Tipo de la llave.
     * @return TIPO
     */
    public byte getTIPO() {
        return TIPO;
    }

    /**
     * Una llave que es un solo numero (para Cesar).
     * @return LLAVE_NUMERICA
     */
    public int getLLAVE_NUMERICA() {
        return LLAVE_NUMERICA;
    }

    /**
     * Una llave que es una cadena de caracteres.
     * Puede ser un clave o mas larga (para AutoTexto,
     * Librereta de un Solo Uso y Vigenere).
     * @return LIBRERETA
     */
    public String getLIBRERETA() {
        return LIBRERETA;
    }

    /**
     * Una llave que es un matriz de numeros (para
     * Multiplicacion de Matrices).
     * @return LLAVE_MATRIZ
     */
    public int[][] getLLAVE_MATRIZ() {
        return LLAVE_MATRIZ;
    }

    /**
     * Una llave que es un matriz/tabla de caracteres
     * (para VICTOR).
     * @return LLAVE_TABLA
     */
    public char[][] getLLAVE_TABLA() {
        return LLAVE_TABLA;
    }
    
}
