/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.cifras;

/**
 * El uso de la Librereta de un Solo Uso tiene reglas muy estrictas:
 * <br>1. La llave (libreta) tiene que ser del mismo longitud o mas larga que 
 * los datos
 * <br>2. La llave (libreta) tiene que ser totalmente aletorio. Si hay como 
 * adivinar la llave, no es seguro.
 * <br>3. La ley universal de la encriptacion: los que tienen tu llave, puden 
 * leer lo que encriptas, obviamente.
 * <br>Pero, si estas condiciones se cumplen, esta forma de encriptacion es 
 * considerado como los professionales como lo absolutamente mas seguro. 
 * Nunca ha sido crackeado, porque es matematicamente impossible de crackear 
 * si estas tres condiciones se cumplen. Funciona en tomar cada caracter y 
 * sumarlo con un caracter de la llave. Ningun caracter de la llave se repita en
 * ser usado en esta operacion (si no, seria la cifra de Vigenere) y normalmente
 * se toma los datos y la llave en orden para que cuando llega a su destino, no 
 * hay que decirles un protocol del orden en que tengan que tomar el llave.
 * @author nyx
 */
public class Librereta_de_un_Solo_Uso implements Cifras{
    /**
     * La LIBRERETA es la llave de este metodo y tiene que ser alatorio. Si hay 
     * como usar una librereta que no es alatoria, pero uno pierde seguridad 
     * porque hay como analizar la frequencia de las letras, una de las 
     * debilidades grandes de la cifra cesar.
     */
    private final String LIBRERETA;
    /**
     * i y j son contadores para correr la librereta dado durante operaciones de
     * encriptacion y decifracion respetivamente.
     */
    private int i = 0, j = 0;

    /**
     * Constructor de la Librereta de un solo uso. Inicializa el constante 
     * Librereta.
     * @param LIBRERETA la librereta que queremos pasar al nuevo objeto para 
     * este tipo de encriptacion.
     */
    public Librereta_de_un_Solo_Uso(String LIBRERETA) {
        this.LIBRERETA = LIBRERETA;
    }

    /**
     * Usando el metodo de la Librereta de un Solo Uso:
     * <br>Toma texto y con este texto, lo encripta
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma encriptada.
     */
    @Override
    public String encriptar(String texto) {
        String salida = "";
        int p = 0;
        while(i < LIBRERETA.length() && p < texto.length()){
            salida += (char)((((int)texto.charAt(p)) + ((int)LIBRERETA.charAt(i))) % 65536);
            i++; p++;
        }
        return salida;
    }

    /**
     * Usando el metodo de la Librereta de un Solo Uso:
     * <br>Toma texto y con este texto, lo decifra
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma decifrada.
     */
    @Override
    public String decifrar(String texto) {
        String salida = "";
        int p = 0;
        while(j < LIBRERETA.length() && p < texto.length()){
            salida += (char)((((int)texto.charAt(p)) - ((int)LIBRERETA.charAt(j))) % 65536);
            j++; p++;
        }
        return salida;
    }

    /**
     * Para poner un indice en i que no es el default, en caso de que uno ha 
     * usado este Librereta en el pasado y necesita iniciar en otro lugar o 
     * algun otro caso parecido. i es el indice usado para ENCRIPTAR.
     * @param i proximo indice de leer del librereta para encriptar
     */
    public void setI(int i) {
        this.i = i;
    }

    /**
     * Para poner un indice en j que no es el default, en caso de que uno ha
     * usado este Librereta en el pasado y necesita iniciar en otro lugar o
     * algun otro caso parecido. j es el indice usado para DECIFRAR.
     *
     * @param j proximo indice de leer del librereta para decifrar
     */
    public void setJ(int j) {
        this.j = j;
    }
    
}
