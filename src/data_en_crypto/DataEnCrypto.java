/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Paquete principal. Contiene el nucleo del programa y interfaces graficas.
 */
package data_en_crypto;

/*
 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.Scanner;
 */
import data_en_crypto.cifras.*;
import data_en_crypto.flujos.entrada.E_Archivo;
import data_en_crypto.flujos.entrada.E_Texto;
import data_en_crypto.flujos.entrada.Entrada;
import java.io.FileNotFoundException;

/**
 * Nucleo para el programa para cuando corre sin interfaz grafica.
 *
 * @author nyx
 */
public class DataEnCrypto {
    //<editor-fold defaultstate="collapsed" desc=" Nucleo del Programa ">
    /**
     * Nucleo de este clase. Corre todo el programa sin interfaz grafica.
     *
     * @param args los argumentos bajo que fue ejecutado el programa.
     */
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

        if (args.length == 0) {
            Menus menu = new Menus();
            char[] op = new char[Menus.NUM_MENUS];
            boolean salir = false;
            while (menu.tieneMas()) {
                char[] posib = menu.mostrar();
                op[menu.getPos() - 1] = menu.obtenerRespuesta("Opcion: ", posib);
                if (op[menu.getPos() - 1] == 'A' || op[menu.getPos() - 1] == '9') {
                    menu.irAtras((byte) 2);
                } else if (op[menu.getPos() - 1] == 'S' || op[menu.getPos() - 1] == '0') {
                    char[] sal_posib = menu.salir_pregunta();
                    char res = menu.obtenerRespuesta("Opcion: ", sal_posib);
                    salir = ((res != '0' && res != 'N') && (res == '1' || res == 'S'));
                    if (salir) {
                        System.out.println("Ahora el programa va a salir!");
                        break;
                    } else {
                        if (res != '0' && res != 'N') {
                            System.out.println("Opcion \'" + res + "\' no existe!");
                        }
                        menu.irAtras((byte) 1);
                    }
                }
            }
            if (!salir) {
                boolean error = false;
                if (!error) {
                    System.out.println("---Configuracion de Entrada---");
                    Entrada entrd = null;
                    if (!error && (op[2] == '1' || op[2] == 'C')) {
                        System.out.println("\tUd. elegio ingresar los datos de entrada por consola:");
                        System.out.println("El valor de salida es el ultima linea que uno ingrese para mostrar que ha terminado de ingresar.");
                        System.out.println("Ojo! Este ultima linea no vamos a poner al final los datos.");
                        System.out.println("Debe ser una linea que no existe en sus datos.");
                        String val_sal = menu.obtenerRespuesta("Valor de Salida: ");
                        System.out.println("Entrar los datos abajo. Tecle su valor de salida en su propio linea cuando termina.");
                        entrd = new E_Texto(val_sal);
                        /*
                        //prueba para ver:
                        System.out.println();
                        System.out.println("Prueba:");
                        System.out.print(entrd.getData());
                        */
                    } else if (!error && (op[2] == '2' || op[2] == 'R')) {
                        System.out.println("\tUd. elegio ingresar los datos de entrada por medio de un archivo:");
                        System.out.println("Directorio debe ser una dirrecion absoluta.");
                        System.out.println("En otras palabras empieza con la carpeta raiz (Unix/Linux) \'/\'");
                        System.out.println("O empieza con la letra de uno de sus depositivos (Windows) \'C:\\\' \'D:\\\' ... etc");
                        System.out.println("Y termina con la carpeta en donde se encuentra tu archivo seguido por un slash");
                        System.out.println("\'/\' (UNIX/LINUX) o \'\\\' (Windows)");
                        String dir = menu.obtenerRespuesta("Directorio: ");
                        System.out.println("Ahora el nombre de su archivo (sin la extencion)");
                        String nom = menu.obtenerRespuesta("Nombre: ");
                        System.out.println("Ahora la extension de su archivo (por ejemplo \'.txt\' o \'.doc\'");
                        String ext = menu.obtenerRespuesta("Extension: ");
                        System.out.println("Si Ud. tiene bastante RAM para cargar todo el archivo, es recommendable que "
                                + "haga eso para que el programa puede correr mas rapido.");
                        char[] opciones = {'1', '0', 'S', 'N'};
                        System.out.println("Su archivo es demaciado grande para cargar al RAM?");
                        System.out.println("[1] -- [S]i");
                        System.out.println("[0] -- [N]o");
                        char res = menu.obtenerRespuesta("Opcion: ", opciones);
                        boolean cargarEnRAM = (res == '1' || res == 'S');
                        try {
                            entrd = new E_Archivo(dir, nom, ext, cargarEnRAM);
                        } catch (FileNotFoundException fnfe) {
                            System.out.println("ERROR! No se encontro el archivo: " + dir + nom + ext);
                            error = true;
                        }
                        /*
                        if(entrd != null) {
                            //prueba para ver:
                            System.out.println();
                            System.out.println("Prueba:");
                            System.out.print(entrd.getData());
                        }
                        */
                    } else if (error) {
                        System.out.println("Hay un error! Saliendo!");
                    } else {
                        //nunca se debe llegar a este bloque.
                        System.out.println("ERROR EXTRAÑO. SALIENDO.");
                        error = true;
                    }
                }
            }
        } else {

        }
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Prueba de cada Cifra ">
    /**
     * Una pequeña programa en la forma de un metodo. Toma argumentos de
     * ejecutacion como parametro y despues muestra como es cada cifra.
     *
     * @param args argumentos de ejecutacion
     */
    public static void prueba(String[] args) {

        System.out.println("---Estado Inicial---");
        String texto = "Hola Mundo!";
        String salida;
        System.out.println("Texto de Entrada: " + texto);
        String clave = "clave";
        System.out.println("Clave: " + clave);
        byte llave = 1;
        System.out.println("Llave: " + llave);
        String librereta = "!odnuM aloH";
        System.out.println("Librereta: " + librereta);
        int[][] matriz = {
            {4, 3, 3},
            {3, 2, 1},
            {1, 1, 3}
        };
        imprimir_matriz("Matriz:\n", matriz);
        char[][] tabla_llave = {
            //0    1    2   2/3  3/4  4/5  5/6  6/7  6/8  7/9   8    9
            {'E', 'T', '\u0000', 'A', 'O', 'N', '\u0000', 'R', 'I', 'S'}, //0
            {'B', 'C', 'D', 'F', 'G', 'H', 'J', 'K', 'L', 'M'}, //1 (2#)
            {'P', 'Q', ' ', 'U', 'V', 'W', 'X', 'Y', 'Z', '.'} //2 (6#)
        };
        imprimir_matriz("Tabla:\n", tabla_llave);
        byte[] clave2 = {'c', 'l', 'a', 'v', 'e'};
        imprimir_arreglo("Segundo Clave: ", clave2);
        System.out.println();

        //<editor-fold defaultstate="collapsed" desc=" AutoTexto -- funciona ">  
        System.out.println("---AutoTexto---");
        System.out.println("Usando clave!");
        AutoTexto at = new AutoTexto(clave);
        salida = at.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = at.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Cesar -- funciona">
        System.out.println("---Cesar---");
        System.out.println("Usando llave!");
        Cesar ces = new Cesar(llave);
        salida = ces.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = ces.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Librereta de Un Solo Uso -- funciona ">
        /**/
        System.out.println("---Librereta de un Solo Uso---");
        System.out.println("Usando Librereta!");
        Librereta_de_un_Solo_Uso lib = new Librereta_de_un_Solo_Uso(librereta);
        salida = lib.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = lib.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        /**/
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Matrices -- funciona ">
        //*
        System.out.println("---Matrices---");
        System.out.println("Usando matriz!");
        Matrices mat = new Matrices(matriz);
        //System.out.println("DET: " + mat.getDET());
        if (mat.getLLAVE() != null) {
            salida = mat.encriptar(texto);
            System.out.println("Encryptado: " + salida);
            salida = mat.decifrar(salida);
            System.out.println("Decifrado:  " + salida);
        }
        //*/
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" VICTOR -- casi funciona -- tiene una pequeña bug ">
        System.out.println("---VICTOR---");
        System.out.println("Usando Tabla!");
        System.out.println("Usando Segundo Clave!");
        VICTOR vic = new VICTOR(tabla_llave, clave2);
        salida = vic.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = vic.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc=" Vigenere -- funciona ">
        System.out.println("---Vigenere---");
        System.out.println("Usando clave!");
        Vigenere vig = new Vigenere(clave);
        salida = vig.encriptar(texto);
        System.out.println("Encryptado: " + salida);
        salida = vig.decifrar(salida);
        System.out.println("Decifrado:  " + salida);
        //</editor-fold>

    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Metodos Auxilliares ">
    /**
     * Metodo para imprimir una matriz formado por numeros de tipo int.
     *
     * @param titulo lo que debemos imprimir antes de la matriz
     * @param matriz la matriz para imprimir
     */
    private static void imprimir_matriz(String titulo, int[][] matriz) {
        System.out.print(titulo);
        for (int[] fila : matriz) {
            System.out.print("[\t");
            for (int num : fila) {
                System.out.print(num + "\t");
            }
            System.out.print("]\n");
        }
    }

    /**
     * Metodo para imprimir una tabla (un tipo de matriz especial -- usa
     * caracteres en lugar de numeros de tipo int).
     *
     * @param titulo Lo que queremos imprimir antes de la tabla
     * @param tabla la tabla que queremos imprimir
     */
    private static void imprimir_matriz(String titulo, char[][] tabla) {
        System.out.print(titulo);
        for (char[] fila : tabla) {
            System.out.print("[\t");
            for (char c : fila) {
                System.out.print(c + "\t");
            }
            System.out.print("]\n");
        }
    }

    /**
     * Un metodo para imprimir un arreglo con datos de tipo byte.
     *
     * @param titulo Lo que queremos imprimir antes de imprimir el arreglo.
     * @param clave el arreglo que queremos imprimir.
     */
    private static void imprimir_arreglo(String titulo, byte[] clave) {
        System.out.print(titulo);
        System.out.print('[');
        for (byte b : clave) {
            System.out.print(" " + b);
        }
        System.out.print(" ]");
    }
    //</editor-fold>
}
