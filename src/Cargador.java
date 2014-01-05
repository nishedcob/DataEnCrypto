/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data_en_crypto.*;

/**
 * Clase principal del proyecto.
 * @author nyx
 */
public class Cargador {
    public static void main(String[] args) {
        if(existe("-h", args) || existe('h', args)){
            //Ayuda desde consola
            System.out.println("");
        } else {
            //aqui va el metodo principal del clase que queremos cargar:
            DataEnCrypto.main(args);
        }
    }

    private static boolean existe(String val, String[] args) {
        for(String arg : args){
            if(val == null ? arg == null : val.equals(arg)){
                return true;
            }
        }
        return false;
    }
    
    private static boolean existe(char val, String[] args) {
        for(String arg : args){
            if(arg.charAt(0) == '-'){
                char[] arg_chars = arg.toCharArray();
                for(char c : arg_chars){
                    if(c == val){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
