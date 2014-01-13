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
            return (det % 2) != 1;
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

    public int[][] getLLAVE() {
        return LLAVE;
    }

    public int getDET() {
        return DET;
    }
    
    private int[][] modulo(int[][] mensaje, int mod){
        for (int i = 0; i < mensaje.length; i++) {
            for (int j = 0; j < mensaje[i].length; j++) {
                mensaje[i][j] %= mod;                
            }
        }
        return mensaje;
    }

    @Override
    public String encriptar(String texto) {
        String salida;
        int[][] mensaje = this.texto_a_matriz(texto);
        mensaje = multiplicar(LLAVE, mensaje);
        mensaje = modulo(mensaje, 65536);
        salida = this.matriz_a_texto(mensaje);
        return salida;
    }

    @Override
    public String decifrar(String texto) {
        String salida;
        int[][] mensaje = this.texto_a_matriz(texto);
        mensaje = multiplicar(inverso(LLAVE), mensaje);
        mensaje = modulo(mensaje, 65536);
        salida = this.matriz_a_texto(mensaje);
        return salida;
    }

    private int[][] texto_a_matriz(String texto) {
        int fil = LLAVE.length;
        int col = texto.length() / fil;
        col += ((texto.length() % fil) != 0) ? 1 : 0;
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
        if (llave[0].length != datos.length) {
            throw new IllegalArgumentException("Llave Columnas: " + llave[0].length + " =/= Dato Filas: " + datos.length);
        }
        int numF = llave.length;    //# filas
        int numC = datos[0].length; //# columnas
        int numO = datos.length;    //# operaciones
        int[][] salida = new int[numF][numC];
        for (int f = 0; f < numF; f++) {
            for (int c = 0; c < numC; c++) {
                salida[f][c] = 0;
            }
        }
        for (int f = 0; f < numF; f++) {
            for (int c = 0; c < numC; c++) {
                for (int o = 0; o < numO; o++) {
                    salida[f][c] += llave[o][f] * datos[o][c];
                }
            }
        }
        return salida;
    }

    private int[][] multiplicar(double[][] llave, int[][] datos) {
        if (llave[0].length != datos.length) {
            throw new IllegalArgumentException("Llave Columnas: " + llave[0].length + " =/= Dato Filas: " + datos.length);
        }
        int numF = llave.length;    //# filas
        int numC = datos[0].length; //# columnas
        int numO = datos.length;    //# operaciones
        int[][] salida = new int[numF][numC];
        for (int f = 0; f < numF; f++) {
            for (int c = 0; c < numC; c++) {
                salida[f][c] = 0;
            }
        }
        for (int f = 0; f < numF; f++) {
            for (int c = 0; c < numC; c++) {
                for (int o = 0; o < numO; o++) {
                    salida[f][c] += llave[f][o] * datos[o][c];
                }
            }
        }
        return salida;
    }

    private double[][] inverso(int[][] llave) {
        double[][] inverso = new double[llave.length][llave[0].length];
        boolean signo = false;
        for (int f = 0; f < llave.length; f++) {
            for (int c = 0; c < llave[f].length; c++) {
                int[][] matriz_de_adentro = new int[llave.length-1][llave[f].length-1];
                int fda = 0, cda = 0; //fila, columna de adentro
                for (int f2 = 0; f2 < llave.length; f2++) {
                    for (int c2 = 0; c2 < llave[f2].length; c2++) {
                        if (f2 != f && c2 != c) {
                            matriz_de_adentro[fda][cda] = llave[f2][c2];
                            cda++;
                            if(cda == matriz_de_adentro[fda].length){
                                cda = 0;
                                fda++;
                            }
                        }
                    }
                }
                int dda = calcular_determinante(matriz_de_adentro);
                if(signo){
                    dda *= -1;
                }
                signo = !signo;
                inverso[f][c] = ((double)dda) / ((double)DET);
            }
        }
        return inverso;
    }

    private void imprimir_matriz(String ini, int[][] mensaje, String ter) {
        System.out.print(ini);
        for(int[] f : mensaje){
            System.out.print("[");
            for(int c : f){
                System.out.print("\t" + c);
            }
            System.out.println("\t]");
        }
        System.out.print(ter);
    }
    
    private void imprimir_matriz(String ini, double[][] mensaje, String ter) {
        System.out.print(ini);
        for(double[] f : mensaje){
            System.out.print("[");
            for(double c : f){
                System.out.print("\t" + c);
            }
            System.out.println("\t]");
        }
        System.out.print(ter);
    }
}
