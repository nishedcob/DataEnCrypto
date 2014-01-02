/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.cifras;

/**
 *
 * @author nyx
 */
public class Cesar implements Cifras{
    private final int LLAVE;

    public Cesar(int LLAVE) {
        this.LLAVE = LLAVE;
    }

    public Cesar(byte LLAVE) {
        this.LLAVE = LLAVE;
    }

    public Cesar(char LLAVE) {
        this.LLAVE = LLAVE;
    }

    @Override
    public String encriptar(String texto) {
        String salida = "";
        for (int i = 0; i < texto.length(); i++) {
            salida += (char)(texto.charAt(i) + LLAVE);
        }
        return salida;
    }

    @Override
    public String decifrar(String texto) {
        String salida = "";
        for (int i = 0; i < texto.length(); i++) {
            salida += (char)(texto.charAt(i) - LLAVE);
        }
        return salida;
    }
    
}
