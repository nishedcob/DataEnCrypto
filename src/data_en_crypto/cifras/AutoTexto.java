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
public class AutoTexto implements Cifras{
    private final String CLAVE;
    private char[] letras;

    public AutoTexto(String CLAVE) {
        this.CLAVE = CLAVE;
        letras = new char[CLAVE.length()];
    }

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
