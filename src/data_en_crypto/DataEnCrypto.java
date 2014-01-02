/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto;

/*
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
*/
import data_en_crypto.cifras.*;

/**
 *
 * @author nyx
 */
public class DataEnCrypto {
    
    //VARIABLES GLOBALES
    final static byte[] VERSION = {0, 1, 5};
    
    //<editor-fold defaultstate="collapsed" desc=" Nucleo del Programa ">
    public static void main(String[] args) {
        //<editor-fold defaultstate="collapsed" desc=" Para cargar una configuracion -- comentado para no correr ">
        //cargar ultimo configuracion:
        /*
        File cf = null;
        Scanner config_file = null;
        final String PATH = new File("data_en_crypto.config").getAbsolutePath();
//        System.out.println(PATH);
//        String absolutePath = new DataEnCrypto().getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
//        absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
//        System.out.println(absolutePath);
        //System.out.println(PATH + "\bdata_en_crypto.config");
        String[][] configs = null;
        try {
            cf = new File(PATH);
            config_file = new Scanner(cf);
            int num_of_lines = 0;
            while(config_file.hasNextLine()){
                config_file.nextLine();
                num_of_lines++;
            }
            System.out.println("Number of Lines: " + num_of_lines);
            configs = new String[num_of_lines][2];
            try {
                config_file.close();
                config_file = new Scanner(cf);
                int c = 0;
                while(config_file.hasNextLine()){
                    String line = config_file.nextLine();
                    String[] line_temp = line.split(" = ");
                    configs[c][0] = line_temp[0];
                    configs[c][1] = line_temp[1];
                    c++;
                }
                for(String[] l : configs){
                    System.out.println(l[0] + ":" + l[1]);
                }
            } catch (FileNotFoundException fnfe2) {
                System.out.println("ERROR EXTRAÑO, Archivo no encontrado para leer.");
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found!");
        } finally {
            if(cf != null && config_file != null){
                config_file.close();
            }
        }
        if(configs != null){
            //process configuration
        } else {
            //set default configuration
        }
        */
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc=" Prueba de cada Cifra ">
        String texto = "Hola Mundo!";
        String salida;
        
        //<editor-fold defaultstate="collapsed" desc=" AutoTexto -- funciona ">
        String clave = "clave";
        System.out.println("---AutoTexto---");
        AutoTexto at = new AutoTexto(clave);
        salida = at.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = at.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc=" Cesar -- funciona">
        byte llave = 1;
        System.out.println("---Cesar---");
        Cesar ces = new Cesar(llave);
        salida = ces.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = ces.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc=" Librereta de Un Solo Uso -- funciona ">
        /**/
        String librereta = "!odnuM aloH";
        System.out.println("---Librereta de un Solo Uso---");
        Librereta_de_un_Solo_Uso lib = new Librereta_de_un_Solo_Uso(librereta);
        salida = lib.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = lib.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        /**/
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc=" Matrices -- no funciona ">
        //*
        System.out.println("---Matrices---");
        int[][] llave2 = {
            {4, 3, 3},
            {3, 2, 1},
            {1, 1, 3}
        };
        Matrices mat = new Matrices(llave2);
        //System.out.println("DET: " + mat.getDET());
        if(mat.getLLAVE() != null){
            salida = mat.encriptar(texto);
            System.out.println("Encryptado: " + salida);
            salida = mat.decifrar(salida);
            System.out.println("Decifrado:  " + salida);
        }
        //*/
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc=" VICTOR ">
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc=" Vigenere -- funciona ">
        System.out.println("---Vigenere---");
        Vigenere vig = new Vigenere(clave);
        salida = vig.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = vig.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        //</editor-fold>
        
        //</editor-fold>
    }
    //</editor-fold>

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
}
