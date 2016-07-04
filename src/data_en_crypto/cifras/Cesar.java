/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.cifras;

/**
 * La cifra Cesar es una de las cifras mas viejas y mas conocidas. Toma cada
 * letra y suma o resta un valor constante a ello. Por lo tanto es facil de
 * romper y nunca debe estar usado con datos que de verdad uno necesita
 * proteger. Esta implimentado aqui para que uno puede estudiar como funciona.
 * @author nyx
 */
public class Cesar implements Cifras{
    /**
     * Nuestra llave constante.
     */
    private final int LLAVE;

    /**
     * Un constructor que da valor a nuestra llave constante
     * @param LLAVE la llave constante que queremos usar en forma de un numero entero.
     */
    public Cesar(int LLAVE) {
        this.LLAVE = LLAVE;
    }

    /**
     * Un constructor que da valor a nuestra llave constante
     * @param LLAVE la llave constante que queremos usar en forma de un numero byte.
     */
    public Cesar(byte LLAVE) {
        this.LLAVE = LLAVE;
    }

    /**
     * Un constructor que da valor a nuestra llave constante
     * @param LLAVE la llave constante que queremos usar en forma de un caracter.
     */
    public Cesar(char LLAVE) {
        this.LLAVE = LLAVE;
    }

    /**
     * Usando el metodo de Cesar:
     * <br>Toma texto y con este texto, lo encripta
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma encriptada.
     */
    @Override
    public String encriptar(String texto) {
        String salida = "";
        for (int i = 0; i < texto.length(); i++) {
            salida += (char)(texto.charAt(i) + LLAVE);
        }
        return salida;
    }

    /**
     * Usando el metodo de Cesar:
     * <br>Toma texto y con este texto, lo decifra
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma decifrada.
     */
    @Override
    public String decifrar(String texto) {
        String salida = "";
        for (int i = 0; i < texto.length(); i++) {
            salida += (char)(texto.charAt(i) - LLAVE);
        }
        return salida;
    }

}
