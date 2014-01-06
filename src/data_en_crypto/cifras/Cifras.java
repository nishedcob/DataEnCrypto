/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.cifras;

/**
 * Interfaz de cifras, define dos metodos abstractos que todas las cifras deben 
 * tener:
 * <br>-Encriptar
 * <br>-Decifrar
 * @author nyx
 */
public interface Cifras{
    /**
     * Toma texto y con este texto, lo encripta
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma encriptada.
     */
    public String encriptar(String texto);
    /**
     * Toma texto y con este texto, lo decifra
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma decifrada.
     */
    public String decifrar(String texto);
}
