/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_en_crypto.flujos.llave;

import data_en_crypto.flujos.entrada.E_Texto;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Flujo de Llave por medio de un campo de texto (por ejemplo la consola)
 * @author nyx
 */
final public class L_Texto extends E_Texto implements Llave_Tipos {

    /**
     * La llave encapsulada por este objeto.
     */
    final private Llave L;

    /**
     * Constructor para L_Texto
     * @param Val_Salida la ultima linea que el usuario va a ingresar
     * @param tipo el tipo de llave
     */
    public L_Texto(String Val_Salida, byte tipo) {
        super(Val_Salida);
        L = this.crearLlave(tipo);
    }

    /**
     * Crea un nuevo flujo de entrada de llave de texto con datos ya leidos.
     *
     * @param data   datos para almacenar en este objeto
     * @param lector el scanner que este entrada debe usar para leer
     */
    public L_Texto(String data, Scanner lector, byte tipo) throws Llave_Invalido_Exception {
        super(data, lector);
        L = this.crearLlave2(tipo);
    }

    /**
     * Metodo que crea un tipo de llave especifica
     * @param tipo el tipo de llave que queremos crear
     * @return la llave creada
     */
    private Llave crearLlave(byte tipo) {
        int n;
        boolean t, s;
        switch (tipo) {
            case TIPO_LLAVE_CLAVE:
            case TIPO_LLAVE_LIBRERETA:
                return new Llave(tipo, data);
//                break;
            case TIPO_LLAVE_NUMERICA:
                int num = 0;
                try {
                    num = (int) (Double.parseDouble(data));
                } catch (NumberFormatException nfe) {
                    num = crear_numero(data);
                } finally {
                    return new Llave(tipo, num);
                }
//                break;
            case TIPO_LLAVE_ARREGLO:
                n = data.split("\t").length;
                t = !(n == 0 || n == 1);
                n = data.split(" ").length;
                s = !(n == 0 || n == 1);
                String[] numeros;
                int[] arreglo;
                if (!(t || s)) {
                    throw new InputMismatchException("Valores debe ser seperadas por espacios: \' \' o tabulaciones: \'\t\'!");
                } else {
                    if (t && !s) {
                        numeros = data.split("\t");
                    } else if (s && !t) {
                        numeros = data.split(" ");
                    } else {
                        throw new InputMismatchException("Hay que elegir un seperador, no se puede usar los dos.");
                    }
                    int dim = numeros.length;
                    arreglo = new int[dim];
                    int p = 0;
                    for (int i = 0; i < arreglo.length; i++) {
                        n = 0;
                        try {
                            n = (int) (Double.parseDouble(numeros[p]));
                        } catch (NumberFormatException nfe) {
                            n = crear_numero(numeros[p]);
                        }
                        arreglo[i] = n;
                        p++;
                    }
                }
                return new Llave(tipo, arreglo);
            case TIPO_LLAVE_MATRIZ:
                n = data.split("\t").length;
                t = !(n == 0 || n == 1);
                n = data.split(" ").length;
                s = !(n == 0 || n == 1);
                String[] nums;
                if (!(t || s)) {
                    throw new InputMismatchException("Valores debe ser seperadas por espacios: \' \' o tabulaciones: \'\t\'!");
                } else {
                    if (t && !s) {
                        nums = data.split("\t");
                    } else if (s && !t) {
                        nums = data.split(" ");
                    } else {
                        throw new InputMismatchException("Hay que elegir un seperador, no se puede usar los dos.");
                    }
                    int dim = encontrarCuadrado(nums.length);
                    int[][] matriz = new int[dim][dim];
                    int p = 0;
                    for (int i = 0; i < matriz.length; i++) {
                        for (int j = 0; j < matriz.length; j++) {
                            n = 0;
                            if (p < nums.length) {
                                try {
                                    n = (int) (Double.parseDouble(nums[p]));
                                } catch (NumberFormatException nfe) {
                                    n = crear_numero(nums[p]);
                                }
                            }
                            matriz[i][j] = n;
                            p++;
                        }
                    }
                    return new Llave(tipo, matriz);
                }
//                break;
            case TIPO_LLAVE_TABLA:
                n = data.split("\t").length;
                t = !(n == 0 || n == 1);
                n = data.split(" ").length;
                s = !(n == 0 || n == 1);
                String[] data_puntos = null;
                char[] caracteres = null;
                boolean dp = false,
                 ch = false;
                if (!(t || s)) {

                } else {
                    if (t && !s) {
                        data_puntos = data.split("\t");
                        dp = true;
                    } else if (s && !t) {
                        data_puntos = data.split(" ");
                        dp = true;
                    } else {
                        caracteres = data.toCharArray();
                        ch = true;
                    }
                    int dim = 0;
                    if (dp) {
                        dim = encontrarCuadrado(data_puntos.length);
                    } else if (ch) {
                        dim = encontrarCuadrado(caracteres.length);
                    }
                    char[][] tabla = new char[dim][dim];
                    int p = 0;
                    for (int i = 0; i < tabla.length; i++) {
                        for (int j = 0; j < tabla.length; j++) {
                            char c = 0;
                            if (dp) {
                                if (p < data_puntos.length) {
                                    if (data_puntos[p].length() != 1) {
                                        c = crear_char(data_puntos[p]);
                                    } else {
                                        c = data_puntos[p].charAt(0);
                                    }
                                }
                            } else {
                                if (p < caracteres.length) {
                                    c = caracteres[p];
                                }
                            }
                            tabla[i][j] = c;
                            p++;
                        }
                    }
                    return new Llave(tipo, tabla);
                }
//                break;
            default:
                return new Llave();
//                break;
        }
    }

    /**
     * Metodo que crea un tipo de llave especifica de datos leidos del configuracion de llave
     * @param tipo el tipo de llave que queremos crear
     * @return la llave creada
     */
    private Llave crearLlave2(byte tipo) throws Llave_Invalido_Exception {
        int n;
        boolean t, s;
        switch (tipo) {
            case TIPO_LLAVE_CLAVE:
            case TIPO_LLAVE_LIBRERETA:
                return new Llave(tipo, data);
            case TIPO_LLAVE_NUMERICA:
                int num = 0;
                try {
                    num = (int) (Double.parseDouble(data));
                } catch (NumberFormatException nfe) {
                    num = crear_numero(data);
                } finally {
                    return new Llave(tipo, num);
                }
            case TIPO_LLAVE_ARREGLO:
                n = data.split("\t").length;
                t = !(n == 0 || n == 1);
                n = data.split(" ").length;
                s = !(n == 0 || n == 1);
                String[] numeros;
                int[] arreglo;
                if (!(t || s)) {
                    throw new InputMismatchException("Valores debe ser seperadas por espacios: \' \' o tabulaciones: \'\t\'!");
                } else {
                    if (t && !s) {
                        numeros = data.split("\t");
                    } else if (s && !t) {
                        numeros = data.split(" ");
                    } else {
                        throw new InputMismatchException("Hay que elegir un seperador, no se puede usar los dos.");
                    }
                    int dim = numeros.length;
                    arreglo = new int[dim];
                    int p = 0;
                    for (int i = 0; i < arreglo.length; i++) {
                        n = 0;
                        try {
                            n = (int) (Double.parseDouble(numeros[p]));
                        } catch (NumberFormatException nfe) {
                            n = crear_numero(numeros[p]);
                        }
                        arreglo[i] = n;
                        p++;
                    }
                }
                return new Llave(tipo, arreglo);
            case TIPO_LLAVE_MATRIZ:
                String col[] = data.split(";");
                int num_fil = data.split("><").length;
                int num_col = col.length / num_fil;
                boolean pc = (col.length != 0 && num_col >= 2);
                boolean ln = (num_fil != 0 && num_fil >= 2);
                boolean cuad = (pc && ln) && (num_col == num_fil);
                String[][] nums = new String[num_col][num_fil];
                if (!cuad) {
                    throw new Llave_Invalido_Exception();
                } else {
                    int f = 0, c = 0;
                    for (String v : col){
                        if (v.charAt(0) == '>') v = v.substring(1);
                        if (v.charAt(0) == '<') v = v.substring(1);
                        nums[c][f] = v;
                        c++;
                        if (c == num_col) {
                            c = 0;
                            f++;
                        }
                    }
                    int[][] matriz = new int[num_col][num_fil];
                    for (int i = 0; i < num_col; i++) {
                        for (int j = 0; j < num_fil; j++) {
                            try {
                                n = (int) (Double.parseDouble(nums[i][j]));
                            } catch (NumberFormatException nfe) {
                                n = crear_numero(nums[i][j]);
                            }
                            matriz[i][j] = n;
                        }
                    }
                    return new Llave(tipo, matriz);
                }
            case TIPO_LLAVE_TABLA:
                n = data.split("\t").length;
                t = !(n == 0 || n == 1);
                n = data.split(" ").length;
                s = !(n == 0 || n == 1);
                String[] data_puntos = null;
                char[] caracteres = null;
                boolean dp = false,
                        ch = false;
                if (!(t || s)) {

                } else {
                    if (t && !s) {
                        data_puntos = data.split("\t");
                        dp = true;
                    } else if (s && !t) {
                        data_puntos = data.split(" ");
                        dp = true;
                    } else {
                        caracteres = data.toCharArray();
                        ch = true;
                    }
                    int dim = 0;
                    if (dp) {
                        dim = encontrarCuadrado(data_puntos.length);
                    } else if (ch) {
                        dim = encontrarCuadrado(caracteres.length);
                    }
                    char[][] tabla = new char[dim][dim];
                    int p = 0;
                    for (int i = 0; i < tabla.length; i++) {
                        for (int j = 0; j < tabla.length; j++) {
                            char c = 0;
                            if (dp) {
                                if (p < data_puntos.length) {
                                    if (data_puntos[p].length() != 1) {
                                        c = crear_char(data_puntos[p]);
                                    } else {
                                        c = data_puntos[p].charAt(0);
                                    }
                                }
                            } else {
                                if (p < caracteres.length) {
                                    c = caracteres[p];
                                }
                            }
                            tabla[i][j] = c;
                            p++;
                        }
                    }
                    return new Llave(tipo, tabla);
                }
            default:
                return new Llave();
        }
    }

    /**
     * Crea un numero de un String (para valores invalidas)
     * @param data el string que vamos a convertir
     * @return el numero que representa el string
     */
    private int crear_numero(String data) {
        char[] conjunto_data = data.toCharArray();
        boolean par = false;
        short s = 0;
        int i = 0;
        for (char c : conjunto_data) {
            if (par) {
                if (c < Byte.MAX_VALUE) {
                    c *= Byte.MAX_VALUE;
                }
                s += c;
                i += s;
            } else {
                s += c % Byte.MAX_VALUE;
            }
            par = !par;
        }
        return i;
    }

    /**
     * Toma un numero y encuentra como poner este numero adentro de un cuadrado.
     * @param longitud el numero de entrada
     * @return la longitud de uno de los dos lados del cuadrado.
     */
    private int encontrarCuadrado(int longitud) {
        int dim = 0;
        while ((dim * dim) < longitud) {
            dim++;
        }
        return dim;
    }

    /**
     * Crea un caracter de un String (para valores invalidas)
     * @param data el string que vamos a convertir
     * @return el caracter que representa el string
     */
    private char crear_char(String data) {
        char[] conjunto_data = data.toCharArray();
        boolean par = false;
        short s = 0;
        char c = 0;
        for (char c2 : conjunto_data) {
            if (par) {
                c2 %= 256;
                s += c2;
                c += s;
                s = 0;
            } else {
                s += c2;
            }
            par = !par;
        }
        return c;
    }

    /**
     * Devuelve la llave encapsulado por este objeto
     * @return la llave de este flujo
     */
    public Llave getL() {
        return L;
    }

}
