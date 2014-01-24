package data_en_crypto;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//import data_en_crypto.*;

/**
 * Clase principal del proyecto -- Carga el resto del programa.
 * @author nyx
 */
public class Cargador {
    
    //VARIABLES GLOBALES
    /**
     * Numero de version del Programa.
     */
    final static byte[] VERSION = {0, 6, 1, 2, 5};
    
    /**
     * Metodo principal del cargador -- este metodo carga el resto del programa
     * @param args argumentos dado por ejecutacion por consola
     */
    public static void main(String[] args) {
            DataEnCrypto_GUI_Menu menu = new DataEnCrypto_GUI_Menu();
            //DataEnCrypto.prueba(args);
        }

    //<editor-fold defaultstate="collapsed" desc=" Metodos Auxiliares ">
    /**
     * @deprecated
     * Prueba para ver si una cierta argumento, val (valor), existe de forma sola en args (argumentos)
     * @param val valor para probar
     * @param args conjunto de argumentos de cual estamos probando
     * @return un valor booleano que muestra si existe o no existe el argumento pedido
     */
    private static boolean existe(String val, String[] args) {
        for(String arg : args){
            if(val == null ? arg == null : val.equals(arg)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * @deprecated
     * Prueba para ver si una cierta argumento, val (valor), existe de forma conjunta (con otros argumentos) en args (argumentos)
     * @param val valor para probar
     * @param args conjunto de argumentos de cual estamos probando
     * @return un valor booleano que muestra si existe o no existe el argumento pedido
     */
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
    
    /**
     * Devuelve el numero de version del programa.
     * @return numero de version del programa en forma de un String.
     */
    public static String getVersion() {
        String ver = "";
        try{
            ver += VERSION[0];
            for(byte c = 1; c < VERSION.length; c++){
                ver += "." + VERSION[c];
            }
        } catch (NullPointerException npe) {
            return "No existe numero de version.";
        }
        return ver;
    }
    //</editor-fold>
}
