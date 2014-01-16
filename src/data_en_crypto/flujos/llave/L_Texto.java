/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.llave;

import data_en_crypto.flujos.entrada.E_Texto;
import java.util.InputMismatchException;

/**
 *
 * @author nyx
 */
public class L_Texto extends E_Texto implements Llave_Tipos {

    final private Llave L;

    public L_Texto(String Val_Salida, byte tipo) {
        super(Val_Salida);
        L = this.crearLlave(tipo);
    }

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
            case TIPO_LLAVE_MATRIZ:
                n = data.split("\t").length;
                t = !(n == 0 || n == 1);
                n = data.split(" ").length;
                s = !(n == 0 || n == 1);
                String[] numeros;
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
                    int dim = encontrarCuadrado(numeros.length);
                    int[][] matriz = new int[dim][dim];
                    int p = 0;
                    for (int i = 0; i < matriz.length; i++) {
                        for (int j = 0; j < matriz.length; j++) {
                            n = 0;
                            if (p < numeros.length) {
                                try {
                                    n = (int) (Double.parseDouble(numeros[p]));
                                } catch (NumberFormatException nfe) {
                                    n = crear_numero(numeros[p]);
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

    private int encontrarCuadrado(int longitud) {
        int dim = 0;
        while ((dim * dim) < longitud) {
            dim++;
        }
        return dim;
    }

    private char crear_char(String string) {
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

}
