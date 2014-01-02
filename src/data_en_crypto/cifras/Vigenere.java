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
public class Vigenere implements Cifras{
    private final String CLAVE;

    public Vigenere(String CLAVE) {
        this.CLAVE = CLAVE;
    }

    @Override
    public String encriptar(String texto) {
        String salida = "";
        int j = 0;
        for (int i = 0; i < texto.length(); i++) {
            if(j == CLAVE.length()){
                j = 0;
            }
            salida += (char)(((int) texto.charAt(i)) + ((int) CLAVE.charAt(j)));
            j++;
        }
        return salida;
    }

    @Override
    public String decifrar(String texto) {
        String salida = "";
        int j = 0;
        for (int i = 0; i < texto.length(); i++) {
            if(j == CLAVE.length()){
                j = 0;
            }
            salida += (char)(((int) texto.charAt(i)) - ((int) CLAVE.charAt(j)));
            j++;
        }
        return salida;
    }
    
}
