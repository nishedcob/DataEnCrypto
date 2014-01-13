/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Paquete para flujos de entrada de tipo llave, su objeto principal, que es
 * Llave y una interfaz de constantes que es Llave_Tipos.
 */
package data_en_crypto.flujos.llave;

//import data_en_crypto.flujos.Flujo;

/**
 * Un objecto llave para almacenar algun tipo de llave.
 * @author nyx
 */
final public class Llave implements Llave_Tipos {
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
    
    /**
     * Constructor para Llave. Toma nada y crea un tipo de llave vacia.
     */
    public Llave(){
        this.TIPO = -1;
        this.LLAVE_NUMERICA = 0;
        this.LIBRERETA = null;
        this.LLAVE_MATRIZ = null;
        this.LLAVE_TABLA = null;
    }

    /**
     * Constructor para Llave. Toma el tipo de llave y una llave numerica.
     * @param tipo tipo de llave
     * @param llave_numerica valor para la llave numerica
     */
    public Llave(byte tipo, int llave_numerica) {
        this.TIPO = tipo;
        this.LLAVE_NUMERICA = llave_numerica;
        this.LIBRERETA = null;
        this.LLAVE_MATRIZ = null;
        this.LLAVE_TABLA = null;
    }

    /**
     * Constructor para Llave. Toma el tipo de llave y una cadena que puede
     * servir como clave o librereta
     * @param TIPO tipo de llave
     * @param LIBRERETA valor para la llave en forma de una cadena
     */
    public Llave(byte TIPO, String LIBRERETA) {
        this.TIPO = TIPO;
        this.LLAVE_NUMERICA = 0;
        this.LIBRERETA = LIBRERETA;
        this.LLAVE_MATRIZ = null;
        this.LLAVE_TABLA = null;
    }

    /**
     * Constructor para Llave. Toma el tipo de llave y una matriz que sirve
     * para multiplicacion de matrices.
     * @param TIPO tipo de llave
     * @param LLAVE_MATRIZ un matriz para almacenar en la matriz
     */
    public Llave(byte TIPO, int[][] LLAVE_MATRIZ) {
        this.TIPO = TIPO;
        this.LLAVE_NUMERICA = 0;
        this.LIBRERETA = null;
        this.LLAVE_MATRIZ = LLAVE_MATRIZ;
        this.LLAVE_TABLA = null;
    }

    /**
     * Constructor para Llave. Toma el tipo de llave y una tabla de caracteres
     * para uso con VICTOR.
     * @param TIPO tipo de la llave
     * @param LLAVE_TABLA una tabla de caracteres para almacenar
     */
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
