/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.cifras;

/**
 * La cifra de matrices, tambien conocido la cifra de Hill es una cifra que usa
 * multiplicacion entre matrices para encriptar y multiplicacion entre una
 * matriz que representa el mensaje encriptado y la matriz inversa de la matriz
 * usado para encriptar. Como usa multiplicacion de matrices para sus
 * operaciones, esconde informacion del mensaje original para hacer su
 * criptoanalisis mas dificil. Pero igual, de todas formas, esta cifra no debe
 * ser considerado seguro ahora que computadoras tienen mucho poder. De todas
 * formas, esta cifra es obsoleta.
 * @author nyx
 */
public class Matrices implements Cifras{

    /**
     * Prueba para ver si la llave dado es valido
     * @param llave llave para probar
     * @return si la llave es valido o no valido
     */
    public static boolean es_llave_valido(int[][] llave){
        if(es_cuadrado(llave)){
            int det = calcular_determinante(llave);
            return (det % 2) != 1;
        }
        return false;
    }

    /**
     * prueba para ver si la llave dado es cuadrado
     * @param llave llave para probar
     * @return si la llave es cuadrado o no
     */
    private static boolean es_cuadrado(int[][] llave) {
        for(int[] r : llave){
            if(r.length != llave.length){
                return false;
            }
        }
        return true;
    }

    /**
     * Calcula el determinante de una llave
     * @param llave la llave dado
     * @return el determinante de la llave dado
     */
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

    /**
     * La llave de este objeto
     */
    private final int[][] LLAVE;
    /**
     * Determinante de la llave del objeto
     */
    private final int DET;

    /**
     * Constructor de esta clase
     * @param LLAVE llave para almacencar
     */
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

    /**
     * Desvuelve la llave del objeto
     * @return Llave
     */
    public int[][] getLLAVE() {
        return LLAVE;
    }

    /**
     * Devuelve la determinante de la llave del objeto
     * @return determinante
     */
    public int getDET() {
        return DET;
    }

    /**
     * toma cada valor en mensaje y devuelve su modulo
     * @param mensaje la matriz de entrada
     * @param mod el numero con que tomamos el modulo
     * @return una matriz que tenga todos los valores con el modulo calculado
     */
    private int[][] modulo(int[][] mensaje, int mod){
        for (int i = 0; i < mensaje.length; i++) {
            for (int j = 0; j < mensaje[i].length; j++) {
                mensaje[i][j] %= mod;
            }
        }
        return mensaje;
    }

    /**
     * Usando el metodo de Matrices:
     * <br>Toma texto y con este texto, lo encripta
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma encriptada.
     */
    @Override
    public String encriptar(String texto) {
        String salida;
        int[][] mensaje = this.texto_a_matriz(texto);
        //imprimir_matriz("Entrada:\n", mensaje, "\n================\n\n");
        mensaje = multiplicar(LLAVE, mensaje);
        mensaje = modulo(mensaje, 65536);
        salida = this.matriz_a_texto(mensaje);
        //imprimir_matriz("Llave:\n", LLAVE, "\n================\n\n");
        //imprimir_matriz("Mensaje:\n", mensaje, "\n================\n\n");
        return salida;
    }

    /**
     * Usando el metodo de Matrices:
     * <br>Toma texto y con este texto, lo decifra
     * @param texto texto de entrada.
     * @return el texto de entrada de una forma decifrada.
     */
    @Override
    public String decifrar(String texto) {
        String salida;
        int[][] mensaje = this.texto_a_matriz(texto);
        //imprimir_matriz("Entrada:\n", mensaje, "\n================\n\n");
        mensaje = multiplicar(inverso(LLAVE), mensaje);
        mensaje = modulo(mensaje, 65536);
        salida = this.matriz_a_texto(mensaje);
        //imprimir_matriz("Llave:\n", LLAVE, "\n================\n\n");
        //imprimir_matriz("Inverso:\n", inverso(LLAVE), "\n================\n\n");
        //imprimir_matriz("Llave:\n", LLAVE, "\n================\n\n");
        //imprimir_matriz("Mensaje:\n", mensaje, "\n================\n\n");
        return salida;
    }

    /**
     * Convierte un texto a una matriz
     * @param texto el texto para convertir
     * @return una matriz de salida que representa el texto de entrada
     */
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

    /**
     * Convierte una matriz a texto
     * @param matriz la matriz de entrada
     * @return texto de salida que representa el texto de entrada
     */
    private String matriz_a_texto(int[][] matriz) {
        String salida = "";
        for(int[] f : matriz){
            for(int c : f){
                salida += (char)c;
            }
        }
        return salida;
    }

    /**
     * Multiplicar dos matrices de forma entera
     * @param llave matriz que es la llave
     * @param datos matriz que es los datos
     * @return matriz que es los datos encriptados
     */
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

    /**
     * Multiplicar dos matrices de cual una es de tipo double y el otro de tipo entera
     * @param llave matriz que es la inversa de la llave (tipo double)
     * @param datos matriz que es los datos encriptados
     * @return matriz que es los datos descriptado
     */
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

    /**
     * Calcular el inverso de una llave dado
     * @param llave la llave de que queremos el inverso
     * @return el inverso de la llave dado
     */
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

    /**
     * Imprimir una matriz
     * @param ini texto para imprimir antes de imprimir el matriz
     * @param mensaje el matriz para imprimir
     * @param ter texto para imprimir despues de imprimir el matriz
     */
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

    /**
     * Imprimir una matriz
     * @param ini texto para imprimir antes de imprimir el matriz
     * @param mensaje el matriz para imprimir
     * @param ter texto para imprimir despues de imprimir el matriz
     */
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
