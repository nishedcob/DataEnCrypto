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

    public VICTOR(char[][] TABLA_LLAVE, byte[] LLAVE) {
        this.TABLA_LLAVE = TABLA_LLAVE;
        this.LLAVE = LLAVE;
        this.CHAR_ESPECIAL = this.encontrar_en_tabla('.');
        this.CHAR_NULOS = this.encontrar_nulos();
    }

    @Override
    public String encriptar(String texto) {
        texto = texto.toUpperCase();
        String salida = this.texto_a_numero(texto);
        salida = this.juntar(salida, LLAVE);
        salida = this.numero_a_texto(salida);
        return salida;
    }

    @Override
    public String decifrar(String texto) {
        texto = texto.toUpperCase();
        String salida = this.texto_a_numero(texto);
        salida = this.disjuntar(salida, LLAVE);
        salida = this.numero_a_texto(salida);
        return salida;
    }

    private String texto_a_numero(String texto) {
        String salida = "";
        char[] texto_arreglo = texto.toCharArray();
        for (char c : texto_arreglo) {
            int[] pos = encontrar_en_tabla(c);
            if(pos[0] != 0){
                salida += pos[0];
            }
            salida += pos[1];
        }
        return salida;
    }

    private String numero_a_texto(String texto) {
        String salida = "";
        char[] texto_arreglo = texto.toCharArray();
        for (int i = 0; i < texto_arreglo.length; i++) {
            char c = texto_arreglo[i];
            byte b = Byte.parseByte(""+c);
            int[] pos = new int[2];
            pos[0] = -1;
            pos[1] = b;
            if(existe(b, CHAR_NULOS)){
                i++;
                c = texto_arreglo[i];
                b = Byte.parseByte(""+c);
                pos[0] = pos[1];
                pos[1] = b;
            }
            if(pos[0] == -1){
                salida += TABLA_LLAVE[0][pos[1]];
            } else {
                salida += TABLA_LLAVE[pos[0]][pos[1]];
            }
        }
        return salida;
    }

    private String juntar(String salida, byte[] LLAVE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String disjuntar(String salida, byte[] LLAVE) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

    private boolean existe(byte b, int[] arreglo) {
        for(int i : arreglo){
            if(b == i){
                return true;
            }
        }
        return false;
    }
    
}
