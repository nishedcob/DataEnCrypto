/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.cifras;

/**
 * AutoTexto Cifra, tambien conocido como la AutoLlave Cifra.
 * Usa una clave como un offset y para encriptar los primeros
 * datos. Despues usa la informacion para encriptar su mismo.
 * @author nyx
 */
public class AutoTexto implements Cifras{
    /**
     * La CLAVE inicial.
     */
    private final String CLAVE;
    /**
     * Una variable para almacenar las letras de la clave dinamica... la parte de la llave
     * que cambia con la fluja entre si.
     */
    private char[] letras;

    /**
     * Inicializa la constante y variable que necesitamos para los procesos ...
     * <br> -Una clave maestro (clave)
     * <br> -Un arreglo de caracteres para recordar nuestro llave/clave dinamica
     * @param CLAVE
     */
    public AutoTexto(String CLAVE) {
        this.CLAVE = CLAVE;
        letras = new char[CLAVE.length()];
    }

    /**
     * Usando el metodo de AutoTexto:
     * <br>Toma texto y con este texto, lo encripta
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma encriptada.
     */
    @Override
    public String encriptar(String texto) {
        String salida = "";
        int i;
        for (i = 0; i < CLAVE.length(); i++) {
            letras[i] = texto.charAt(i);
            salida += (char)(((int)texto.charAt(i)) + ((int)CLAVE.charAt(i)));
        }
        for (i = CLAVE.length(); i < texto.length(); i++) {
            char c = letras[0];
            char c2 = texto.charAt(i);
            for(int j = 1; j < letras.length; j++){
                letras[j-1] = letras[j];
            }
            letras[letras.length - 1] = c2;
            salida += (char)(((int)c) + ((int)c2));
        }
        return salida;
    }

    /**
     * Usando el metodo de AutoTexto:
     * <br>Toma texto y con este texto, lo decifra
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma decifrada.
     */
    @Override
    public String decifrar(String texto) {
        String salida = "";
        int i;
        for (i = 0; i < CLAVE.length(); i++) {
            salida += (char)((((int)texto.charAt(i)) - ((int)CLAVE.charAt(i))));
            letras[i] = salida.charAt(i);
        }
        for (i = CLAVE.length(); i < texto.length(); i++) {
            char c = letras[0];
            char c2 = texto.charAt(i);
            for(int j = 1; j < letras.length; j++){
                letras[j-1] = letras[j];
            }
            salida += (char)((((int)c2) - ((int)c)));
            letras[letras.length - 1] = salida.charAt(i);
        }
        return salida;
    }

}
