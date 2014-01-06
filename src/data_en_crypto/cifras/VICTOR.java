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
public class VICTOR implements Cifras{
    private final char[][] TABLA_LLAVE;
    private final byte[] LLAVE;
    private final int[] CHAR_ESPECIAL;
    private final int[] CHAR_NULOS;

    public VICTOR(char[][] tabla_llave, byte[] llave) {
        this.TABLA_LLAVE = tabla_llave;
        this.LLAVE = limpiar(llave);
//        this.imprimir_arreglo("LLAVE:  ", LLAVE);
        this.CHAR_ESPECIAL = this.encontrar_en_tabla('.');
        this.CHAR_NULOS = this.encontrar_nulos();
    }

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

    @Override
    public String decifrar(String texto) {
        texto = texto.toUpperCase();
        int[] temp = this.texto_a_numeros(texto);
        temp = this.disjuntar(temp, LLAVE);
        temp = this.limpiar(temp);
//        this.imprimir_arreglo("Numeros:", temp);
        return this.numeros_a_texto(temp);
    }

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
