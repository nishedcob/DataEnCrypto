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
//            System.out.println("Llave es cuadrado");
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
//                System.out.println("pos[" + i + "] = " + pos[i]);
//                System.out.println("neg[" + i + "] = " + neg[i]);
                spos += pos[i];
                sneg += neg[i];
            }
//            System.out.println("spos - sneg: " + spos + " - " + sneg);
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
        this.imprimir_matriz("\n", mensaje, "\n");
        mensaje = multiplicar(LLAVE, mensaje);
        this.imprimir_matriz("\n", mensaje, "\n");
        mensaje = modulo(mensaje, 65536);
        this.imprimir_matriz("\n", mensaje, "\n");
        salida = this.matriz_a_texto(mensaje);
        return salida;
    }

    @Override
    public String decifrar(String texto) {
        String salida;
        int[][] mensaje = this.texto_a_matriz(texto);
        this.imprimir_matriz("\n", mensaje, "\n");
        mensaje = multiplicar(inverso(LLAVE), mensaje);
        this.imprimir_matriz("\n", mensaje, "\n");
        mensaje = modulo(mensaje, 65536);
        this.imprimir_matriz("\n", mensaje, "\n");
        salida = this.matriz_a_texto(mensaje);
        return salida;
    }

    private int[][] texto_a_matriz(String texto) {
        int fil = LLAVE.length;
        int col = texto.length() / fil;
        col += ((texto.length() % fil) != 0) ? 1 : 0;
//        System.out.println("col = " + col + " fil = " + fil);
        int[][] salida = new int[fil][col];
        int c = 0, f = 0;
        for(char p : texto.toCharArray()){
//            System.out.println("p = " + p);
//            System.out.println("f = " + f + " c = " + c);
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

    private int[][] multiplicar(double[][] llave, int[][] datos) {
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

    private double[][] inverso(int[][] llave) {
//        System.out.print("Llave:");
//        for (int f = 0; f < llave.length; f++) {
//            System.out.print(" " + Arrays.toString(llave[f]));
//        }
        //int DET;
//        System.out.println("\nDeterminante: " + determinante);
        double[][] inverso = new double[llave.length][llave[0].length];
        boolean signo = false;
        for (int f = 0; f < llave.length; f++) {
            for (int c = 0; c < llave[f].length; c++) {
                int[][] matriz_de_adentro = new int[llave.length-1][llave[f].length-1];
                int fda = 0, cda = 0; //fila, columna de adentro
                for (int f2 = 0; f2 < llave.length; f2++) {
                    for (int c2 = 0; c2 < llave[f2].length; c2++) {
                        if (f2 != f && c2 != c) {
//                            System.out.print("\nfda = " + fda + " cda = " + cda);
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
//                System.out.println("dda = " + dda);
//                System.out.println("det = " + DET);
//                int[] cortos = hacerCortos(0.5, dda, determinante);
//                int dda2 =  cortos[1];
//                int det2 =  cortos[0];
//                System.out.println("dda2 = " + dda2);
//                System.out.println("det2 = " + det2);
//                inverso[f][c] = dda2 / det2;
                inverso[f][c] = ((double)dda) / ((double)DET);
//                System.out.println("inverso[" + f + "][" + c + "] = " + inverso[f][c]);
            }
        }
//        System.out.print("Inverso:");
//        for (int f = 0; f < inverso.length; f++) {
//            System.out.print(" " + Arrays.toString(inverso[f]));
//        }
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
    
}
