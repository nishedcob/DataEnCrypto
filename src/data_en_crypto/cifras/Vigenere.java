/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.cifras;

/**
 * La cifra vigenere es una implimentacion del cesar que lo hace mas dificil de 
 * crackear. En lugar de tener una solo constante o valor (llave) como el cesar,
 * tiene un conjunto de llaves (valores, constantes, etc). Con cada nueva 
 * caracter en los datos, usa otro constante o llave en el conjunto de llaves 
 * hasta que no hay mas llaves y vuelve al inicio. De esta forma, con el mismo
 * clave hasta la longtitud de la clave, el texto encifrado por AutoTexto sera 
 * igual que texto encifrado por este metodo. Desde alli es donde se usan 
 * metodos diferentes... El autotexto empieza a usar los datos, y este metodo, 
 * el vigenere solo repita la clave. Mientras que es mas dificil de crackear que
 * el cesar, esto tambien no es una cifra recomendado para datos importantes.
 * @author nyx
 */
public class Vigenere implements Cifras{
    /**
     * La clave usada por este metodo... En otras palabras, el conjunto de llaves.
     */
    private final String CLAVE;

    /**
     * Constructor de un objeto de este metodo. Inicializa el conjunto de llaves
     * en una variable, CLAVE.
     * @param CLAVE el conjunto de llaves en la forma de un String.
     */
    public Vigenere(String CLAVE) {
        this.CLAVE = CLAVE;
    }

    /**
     * Usando el metodo de Vigenere:
     * <br>Toma texto y con este texto, lo encripta
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma encriptada.
     */
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

    /**
     * Usando el metodo de Vigenere:
     * <br>Toma texto y con este texto, lo descifra
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma descifrada.
     */
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
