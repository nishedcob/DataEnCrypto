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
public class Matrices implements Cifras{

    public static boolean es_llave_valido(int[][] llave){
        if(es_cuadrado(llave)){
            int det = calcular_determinante(llave);
            return (det % 2) == 1;
        }
        return false;
    }
    
    private static boolean es_cuadrado(int[][] llave) {
        for(int[] r : llave){
            if(r.length != llave.length){
                return false;
            }
        }
        return true;
    }
    
    private static int calcular_determinante(int[][] llave) {
        if (llave.length == 1) {
            return llave[0][0];
        } else if (llave.length == 2) {
            return (llave[0][0] * llave[1][1]) - (llave[0][1] * llave[1][0]);
        } else {
            int[] pos = new int[llave.length];
            int[] neg = new int[llave.length];
            for (int i = 0; i < llave.length; i++) {
                pos[i] = 1;
                neg[i] = 1;
            }
            for (int r = 0; r < llave.length; r++) {
                for (int c = 0; c < llave[r].length; c++) {
                    int ppos = c - r + llave.length;
                    int npos = c + r - llave.length;
                    if (ppos >= llave.length) {
                        ppos -= llave.length;
                    }
                    if (npos < 0) {
                        npos += llave.length;
                    }
                    pos[ppos] *= llave[r][c];
                    neg[npos] *= llave[r][c];
                }
            }
            int spos = 0, sneg = 0;
            for (int i = 0; i < llave.length; i++) {
                spos += pos[i];
                sneg += neg[i];
            }
            return spos - sneg;
        }
    }
    
    private final int[][] LLAVE;
    private final int DET;

    public Matrices(int[][] LLAVE) {
        if(es_llave_valido(LLAVE)){
            this.LLAVE = LLAVE;
            this.DET = calcular_determinante(LLAVE);
        } else {
            System.out.println("Operacion illegal. Llave no es valido");
            this.LLAVE = null;
            this.DET = 0;
        }
    }

    @Override
    public String encriptar(String texto) {
        String salida;
        int[][] mensaje = this.texto_a_matriz(texto);
        mensaje = multiplicar(LLAVE, mensaje);
        salida = this.matriz_a_texto(mensaje);
        return salida;
    }

    @Override
    public String decifrar(String texto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private int[][] texto_a_matriz(String texto) {
        int col = LLAVE.length;
        int fil = texto.length() / col;
        fil += ((fil % col) != 0) ? 1 : 0;
        int[][] salida = new int[fil][col];
        int c = 0, f = 0;
        for(char p : texto.toCharArray()){
            salida[f][c] = p;
            c++;
            if(c == col){
                c = 0;
                f++;
            }
        }
        return salida;
    }

    private String matriz_a_texto(int[][] matriz) {
        String salida = "";
        for(int[] f : matriz){
            for(int c : f){
                salida += (char)c;
            }
        }
        return salida;
    }

    private int[][] multiplicar(int[][] llave, int[][] datos) {
        int numF = llave.length; //# filas
        int numC = datos[0].length; //# columnas
        int numO; //# operaciones
        if(llave[0].length != datos.length){
            int[][] salida = new int[1][1];
            salida[0][0] = -3;
            return salida;
        }
        numO = datos.length;
        int[][] salida = new int[numF][numC];
        for (int f = 0; f < numF; f++) {
            for (int c = 0; c < numC; c++) {
                int suma = 0;
                for (int o = 0; o < numO; o++) {
                    suma += llave[f][o] * datos[o][c];
                }
                salida[f][c] = suma;
            }
        }
        return salida;
    }
    
}
