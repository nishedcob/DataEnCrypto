/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.cifras;

/**
 * La cifra VIC, tambien conocido como la cifra VICTOR (por el criptonimo del 
 * agente de espianaje de la Union Sovetica que deserto su pais y mostro a la
 * NSA como funciona) es un metodo bien seguro. En las años de que la NSA sabia 
 * de su existencia en 1953 hasta la defectacion de agente VICTOR en 1957, la 
 * NSA no pudo crackear mensajes protegidos con este metodo y por lo tanto, no
 * importa que es bastante simple que se puede hacerlo con lapiz y papel, hasta 
 * ahora es considerado ser muy seguro. El primer paso es codificar los datos 
 * como un serie de digitos. Durrante este proceso, las letras, los que el 
 * usuario elije con la primera fila de su tabla (que normalmente son la letras 
 * mas communes de un idioma, por ejemplo en ingles, se puede recordarlos con la
 * frase: "A SIN TO ERR") son comprimidas en la forma de un solo digito. Las 
 * demas caracteres se codifican con un digito de fila (que no puede tener un 
 * caracter en su columna en la primera fila) y un digito de columna. Este 
 * primer paso es muy importante porque, en comprimir algunas letras, se hace el
 * criptoanalisis de todo el mensaje mucho mas dificil porque no hay como ver 
 * cuales digitos se pertenecen a cada letra (como algunas letras son de un 
 * digito y otros de dos digitos). El segundo paso suma una llave a todos estes 
 * digitos para esconder su contenido y despues en el terecer paso, se convirte 
 * este conjunto de digitos en texto de cifra.
 * @author nyx
 */
public class VICTOR implements Cifras{
    /**
     * La tabla llave es la tabla que nos permite convertir entre caracteres y
     * digitos y de la misma forma, comprimir caracteres que existen con 
     * frequencia, dando mejor defensa contra criptoanalisis.
     */
    private final char[][] TABLA_LLAVE;
    /**
     * La llave es para el segundo paso de la cifra VICTOR en donde convertimos
     * nuestros digitos de letras comprimidas en otras letras, la base para el
     * texto de salida.
     */
    private final byte[] LLAVE;
    /**
     * este constante es para el indice del caracter especial de nuestra tabla.
     * Como no hay espacio en una tabla para todos los caracteres possibles,
     * hay que tener un caracter especial que se va en lugar de estos otros 
     * caracteres.
     */
    private final int[] CHAR_ESPECIAL;
    /**
     * este constante muestra donde se encuentra los caracteres nulos en la 
     * primera fila. Esta es importante para entender como estan codificadas las
     * demas filas.
     */
    private final int[] CHAR_NULOS;

    /**
     * Constructor de la clase Victor. Toma una tabla de caracteres que enseña
     * al programa como debe codificar texto como un conjunto de digitos y visa
     * versa. La llave es para codificacion de un conjunto de digitos.
     * @param tabla_llave una tabla de caracteres que muestra los indices de 
     * caracteres importantes al objeto VICTOR
     * @param llave un conjunto/arreglo de numeros para codificar y descodificar
     * los conjuntos de numeros que esta clase misma se crea y usa.
     */
    public VICTOR(char[][] tabla_llave, byte[] llave) {
        this.TABLA_LLAVE = tabla_llave;
        this.LLAVE = limpiar(llave);
//        this.imprimir_arreglo("LLAVE:  ", LLAVE);
        this.CHAR_ESPECIAL = this.encontrar_en_tabla('.');
        this.CHAR_NULOS = this.encontrar_nulos();
    }

    /**
     * Usando el metodo de VICTOR:
     * <br>Toma texto y con este texto, lo encripta
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma encriptada.
     */
    @Override
    public String encriptar(String texto) {
        texto = texto.toUpperCase();
        int[] temp = this.texto_a_numeros(texto);
//        this.imprimir_arreglo("Datos:  ", temp);
        temp = this.juntar(temp, LLAVE);
//        this.imprimir_arreglo("Numeros:", temp);
        temp = this.limpiar(temp);
//        this.imprimir_arreglo("Numeros:", temp);
        return this.numeros_a_texto(temp);
    }

    /**
     * Usando el metodo de VICTOR:
     * <br>Toma texto y con este texto, lo decifra
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma decifrada.
     */
    @Override
    public String decifrar(String texto) {
        texto = texto.toUpperCase();
        int[] temp = this.texto_a_numeros(texto);
        temp = this.disjuntar(temp, LLAVE);
        temp = this.limpiar(temp);
//        this.imprimir_arreglo("Numeros:", temp);
        return this.numeros_a_texto(temp);
    }

    /**
     * Toma texto y devuelve su representacion de digitos.
     * @param texto texto de entrada
     * @return un conjunto de numeros que representa el texto de entrada de
     * acuerdo con la tabla en uso.
     */
    private int[] texto_a_numeros(String texto) {
        int[] temp = new int[texto.length() * 2];
        for (int i = 0; i < temp.length; i++) {
            temp[i] = -1;
        }
        char[] texto_arreglo = texto.toCharArray();
        int i = 0;
        for (char c : texto_arreglo) {
            int[] pos = encontrar_en_tabla(c);
            if(pos[0] == 0){
                temp[i] = pos[1];
            } else {
                int a = 0, b = 0;
                for(char n : TABLA_LLAVE[0]){
                    if(n == '\u0000'){
                        a++;
                        if(a == pos[0]){
                            temp[i] = (b*10) + pos[1];
                            break;
                        }
                    }
                    b++;
                }
            }
            i++;
        }
        byte num = 0;
        for(int j : temp){
            if(j >= 10){
                num++;
            }
        }
        int[] salida = new int[texto.length() + num];
        i = 0;
        int c = 0;
        while(c < salida.length && c != -1 && i < salida.length){
            int j = temp[c];
            if(j < 10){
                salida[i] = j;
            } else {
                salida[i] = j / 10;
                i++;
                salida[i] = j % 10;
            }
            i++;
            c++;
        }
        return salida;
    }

    /**
     * Toma numeros y devuelve su representacion en letras.
     * @param temp conjunto de numeros como entrada
     * @return texto que representa los numeros de entrada de acuerdo con la
     * tabla en uso.
     */
    private String numeros_a_texto(int[] temp) {
        String salida = "";
        for (int i = 0; i < temp.length; i++) {
            int c = temp[i];
            if(TABLA_LLAVE[0][c] == '\u0000'){
                int f = 1;
                for(int j = (c - 1); j >= 0; j--){
                    if(TABLA_LLAVE[0][j] == '\u0000'){
                        f++;
                    }
                }
                i++;
                if(i < temp.length){
                    c = temp[i];
                } else {
                    c = 0;
                }
                salida += TABLA_LLAVE[f][c];
            } else {
                salida += TABLA_LLAVE[0][c];
            }
        }
        return salida;
    }

    /**
     * Juntar un conjunto de numeros, que representan texto con una llave que 
     * les protegera de un criptoanalisis que sea facil
     * @param temp el conjunto de numeros tomado como una entrada
     * @param llave la llave numerica para juntar con los numeros, tambien dado 
     * como una entrada
     * @return Devuelve el conjunto numerico modificado por la llave
     */
    private int[] juntar(int[] temp, byte[] llave) {
        int j = 0;
        for (int i = 0; i < temp.length; i++) {
            int c = temp[i];
            short k = llave[j];
            j++;
            if(j == llave.length){
                j = 0;
            }
            temp[i] = c + k;
        }
        return temp;
    }
    
    /**
     * Disjuntar un conjunto de numeros, que representan texto de una llave que 
     * les protegio de un criptoanalisis que sea facil
     * @param temp el conjunto de numeros tomado como una entrada
     * @param llave la llave numerica para juntar con los numeros, tambien dado 
     * como una entrada
     * @return Devuelve el conjunto numerico modificado por la llave
     */
    private int[] disjuntar(int[] datos, byte[] llave) {
        int j = 0;
        int t = 0;
        int[] temp = new int[datos.length];
        for(int a = 0; a < temp.length; a++){
            temp[a] = -1;
        }
//        this.imprimir_arreglo("Datos:  ", datos);
//        this.imprimir_arreglo("Llave:  ", llave);
        for (int i = 0; i < datos.length; i++) {
            int c = datos[i];
            short k = llave[j];
            j++;
            if(j == llave.length){
                j = 0;
            }
            if(c < k){
                i++;
                c *= 10;
                c += datos[i];
            }
            temp[t] = c - k;
            t++;
        }
        int[] salida = new int[t];
        for (int i = 0; i < t; i++) {
            salida[i] = temp[i];
        }
        return salida;
    }

    /**
     * buscar un caracter querido en la tabla...
     * @param c caracter que queremos buscar
     * @return la indice del caracter, o si no encontro, el indice de nuestro
     * caracter especial.
     */
    private int[] encontrar_en_tabla(char c) {
        int[] pos = new int[2];
        for (int i = 0; i < TABLA_LLAVE.length; i++) {
            for (int j = 0; j < TABLA_LLAVE[i].length; j++) {
                if(c == TABLA_LLAVE[i][j]){
                    pos[0] = i;
                    pos[1] = j;
                    return pos;
                }
            }
        }
        return CHAR_ESPECIAL;
    }

    /**
     * Encontrar las caracteres nulos en la primera fila
     * @return un arreglo de las indices, o sea columnas, de las caracteres 
     * nulas en la primera fila
     */
    private int[] encontrar_nulos() {
        byte nulos = 0;
        for(char c : TABLA_LLAVE[0]){
            if(c == '\u0000'){
                nulos++;
            }
        }
        int[] char_nulos = new int[nulos];
        int j = 0;
        for (int i = 0; i < TABLA_LLAVE[0].length; i++) {
            char c = TABLA_LLAVE[0][i];
            if(c == '\u0000'){
                char_nulos[j] = i;
                j++;
            }
        }
        return char_nulos;
    }

    /**
     * prueba que si un valor existe dentro de un arreglo
     * @param b valor buscado
     * @param arreglo conjunto de datos en cual buscaremos
     * @return verdad si el valor buscado existe dentro del arreglo
     * <br> falso si el valor buscado no existe dentro del arreglo
     */
    private boolean existe(int b, int[] arreglo) {
        for(int i : arreglo){
            if(b == i){
                return true;
            }
        }
        return false;
    }

    private byte[] limpiar(byte[] datos) {
        byte[] temp = new byte[datos.length * 3];
        int i = 0;
        for(byte d : datos){
            if(d >= 100){
                temp[i] = (byte) (d / 100);
                d %= 100;
                i++;
                temp[i] = (byte) (d / 10);
                d %= 10;
                i++;
                temp[i] = (byte) d;
            } else if (d >= 10) {
                temp[i] = (byte) (d / 10);
                d %= 10;
                i++;
                temp[i] = (byte) d;
            } else {
                temp[i] = (byte) d;
            }
            i++;
        }
        byte[] salida = new byte[i];
        for (int j = 0; j < salida.length; j++) {
            salida[j] = temp[j];
        }
        return salida;
    }
    
    private int[] limpiar(int[] datos) {
        int[] temp = new int[datos.length * 3];
        int i = 0;
        for(int d : datos){
            if(d >= 100){
                temp[i] = (d / 100);
                d %= 100;
                i++;
                temp[i] = (d / 10);
                d %= 10;
                i++;
                temp[i] = d;
            } else if (d >= 10) {
                temp[i] = (d / 10);
                d %= 10;
                i++;
                temp[i] = d;
            } else {
                temp[i] = d;
            }
            i++;
        }
        int[] salida = new int[i];
        for (int j = 0; j < salida.length; j++) {
            salida[j] = temp[j];
        }
        return salida;
    }

    private void imprimir_arreglo(String titulo, byte[] datos) {
        System.out.print(titulo);
        for(byte d : datos){
            System.out.print(" " + d);
        }
        System.out.println();
    }
    
    private void imprimir_arreglo(String titulo, int[] datos) {
        System.out.print(titulo);
        for(int d : datos){
            System.out.print(" " + d);
        }
        System.out.println();
    }
}
