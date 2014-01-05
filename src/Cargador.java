/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import data_en_crypto.*;

/**
 * Clase principal del proyecto -- Carga el resto del programa.
 * @author nyx
 */
public class Cargador {
    /**
     * Metodo principal del cargador -- este metodo carga el resto del programa
     * @param args argumentos dado por ejecutacion por consola
     */
    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc=" codigo del cargador -- comentado porque su implimentacion no esta listo todavia ">
        /*
        if(existe("-h", args) || existe('h', args)){
            //Ayuda desde consola
            System.out.println("");
        } else {
            if(existe("-c", args) || existe('c', args)){
                //en nuestro programa final, este argumento significa que el 
                //usuario quiere correr nuestro programa desde la consola, 
                //entonces vamos a usar nuestro programa de consola.
                DataEnCrypto.main(args);
            } else if (existe("-g", args) || existe('g', args) ||
                       existe("-m", args) || existe('m', args) ||
                       existe("-a", args) || existe('a', args) ||
                       existe("-b", args) || existe('b', args)) {
                //el usuario quiere alguno de nuestros interfaces graficas
                if(existe("-g", args) || existe('g', args) ||
                   existe("-m", args) || existe('m', args)) {
                    //el usuario quiere el menu principal
                    DataEnCrypto_GUI_Menu.main(args);
                } else if (existe("-a", args) || existe('a', args)) {
                    //el usuario quiere el interfaz avanzada
                    DataEnCrypto_GUI_Avanzada.main(args);
                } else if (existe("-p", args) || existe('p', args)) {
                    //el usuario quiere ver las pruebas de cada cifra
                    DataEnCrypto.main(args);
                    //por ahora, las pruebas son en el clase de arriba. Despues
                    //estaran en otro metodo y sera que cambiar este linea
                } else {
                    //el usuario quiere el interfaz basica
                    DataEnCrypto_GUI_Basico.main(args);
                }
            } else if (args.length > 0) {
                //no reconocemos los argumentos
                String[] nuevo_args = {"-h"};
                Cargador.main(nuevo_args);
            } else {
                //ejecuto sin argumentos
                //en caso de que esto es un usuario que no sabe los argumentos y
                //que lo esta ejecutando sin consola, lanzamos el menu principal
                DataEnCrypto_GUI_Menu.main(args);
            }
            //*/
            //</editor-fold>
            //aqui va el metodo principal del clase que queremos cargar:
            DataEnCrypto.main(args);
        }
    //}

    /**
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
}
