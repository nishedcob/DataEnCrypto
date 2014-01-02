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
public class Librereta_de_un_Solo_Uso implements Cifras{
    private final String LIBRERETA;
    private int i = 0, j = 0;

    public Librereta_de_un_Solo_Uso(String LIBRERETA) {
        this.LIBRERETA = LIBRERETA;
    }

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

    @Override
    public String decifrar(String texto) {
        String salida = "";
        int p = 0;
        while(j < LIBRERETA.length() && p < texto.length()){
//            int c  = texto.charAt(p) - LIBRERETA.charAt(j);
//            /*while(c < 0){
//                c += 65536;
//            }*/
//            System.out.println("txt val @ p: " + (int)(texto.charAt(p)));
//            System.out.println("lib val @ j: " + (int)(LIBRERETA.charAt(j)));
//            System.out.println("c: " + c);
//            if(c < 0){
//                c = LIBRERETA.charAt(j) - texto.charAt(p);
//                System.out.println("c: " + c);
//            }
            salida += (char)((((int)texto.charAt(p)) - ((int)LIBRERETA.charAt(j))) % 65536);
//            salida += (char)(c % 65536);
            j++; p++;
        }
        return salida;
    }
}
