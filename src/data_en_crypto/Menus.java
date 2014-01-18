/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_en_crypto;

//import data_en_crypto.flujos.entrada.E_Texto;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Clase que actua como interfaz de texto para nuestro programa cuando corre por consola.
 * @author nyx
 */
class Menus {

    //Constantes Globales (estaticas)
    /**
     * El numero de menus differentes que tiene.
     */
    final static byte NUM_MENUS = 6;
    /**
     * Un Scanner para leer respuestas ingresados por el teclado.
     */
    final static Scanner LECTOR = new Scanner(System.in);
    
    //Variables de un Objeto Menu
    /**
     * El menu en que estamos ahora.
     */
    private byte pos = 0;
    /**
     * Un valor que nos dice si existen mas menus para presentar o no.
     */
    private boolean tiene_mas = true;
    
    /**
     * Un metodo para mostrar el proximo menu.
     * @return las opciones possibles en este menu.
     */
    public char[] mostrar() {
        char[] opciones;
        if (tieneMas()) {
            switch (getPos()) {
                case 0:
                    opciones = algoritmos();
                    break;
                case 1:
                    opciones = modo();
                    break;
                case 2:
                    opciones = tipoEntrada();
                    break;
                case 3:
                    opciones = tipoLlave();
                    break;
                case 4:
                    opciones = tipoSalida();
                    break;
                case 5:
                    opciones = lecturaLlave();
                    break;
                default:
                    opciones = null;
                    break;
            }
            pos++;
            if (getPos() == NUM_MENUS) {
                tiene_mas = false;
            }
            return opciones;
        }
        return null;
    }

    //<editor-fold defaultstate="collapsed" desc=" Menus ">
    /**
     * Un Menu que es una lista de todos los algoritmos que el programa conoce.
     * @return un arreglo que representa todos las opciones possibles con este menu.
     */
    private char[] algoritmos() {
        System.out.println("Elegir un algoritmo:");
        System.out.println("[1] -- Auto[T]exto");
        System.out.println("[2] -- [C]esar");
        System.out.println("[3] -- [L]ibrereta de Un Solo Uso");
        System.out.println("[4] -- [M]atrices");
        System.out.println("[5] -- V[I]CTOR");
        System.out.println("[6] -- [V]igenere");
        System.out.println();
        System.out.println("[0] -- [S]alir");
        char[] op = {'1', '2', '3', '4', '5', '6', '0', 
                    'T', 'C', 'L', 'M', 'I', 'V', 'S'};
        return op;
    }

    /**
     * Un Menu que nos da una opcion entre dos modos de operacion.
     * @return un arreglo que representa todos las opciones possibles con este menu.
     */
    private char[] modo() {
        System.out.println("Elegir un modo de operacion:");
        System.out.println("[1] -- [E]ncriptar");
        System.out.println("[2] -- [D]ecifrar");
        System.out.println();
        System.out.println("[9] -- [A]tras");
        System.out.println("[0] -- [S]alir");
        char[] op = {'1', '2', '9', '0',
                    'E', 'D', 'A', 'S'};
        return op;
    }
    
    /**
     * Un submenu que es la opcion entre dos possibles flujos.
     * @return un arreglo que representa todos las opciones possibles con este menu.
     */
    private char[] flujos(){
        System.out.println("[1] -- [C]onsola");
        System.out.println("[2] -- a[R]chivo");
        System.out.println();
        System.out.println("[9] -- [A]tras");
        System.out.println("[0] -- [S]alir");
        char[] op = {'1', '2', '9', '0',
                    'C', 'R', 'A', 'S'};
        return op;
    }
    
    /**
     * Un menu que es la opcion entre dos possibles flujos.
     * @return un arreglo que representa todos las opciones possibles con este menu.
     */
    private char[] tipoEntrada() {
        System.out.println("Tipo de Entrada:");
        return flujos();
    }
    
    /**
     * Un menu que es la opcion entre dos possibles flujos.
     * @return un arreglo que representa todos las opciones possibles con este menu.
     */
    private char[] tipoLlave() {
        System.out.println("Tipo de Llave:");
        return flujos();
    }
    
    /**
     * Un menu que es la opcion entre dos possibles flujos.
     * @return un arreglo que representa todos las opciones possibles con este menu.
     */
    private char[] tipoSalida() {
        System.out.println("Tipo de Salida:");
        return flujos();
    }
    
    /**
     * Un Menu que es una lista de todas las maneras possibles que el programa conoce para leer una llave.
     * @return un arreglo que representa todos las opciones possibles con este menu.
     */
    private char[] lecturaLlave(){
        System.out.println("Como se debe leer el llave?");
        System.out.println("[1] -- [C]lave");
        System.out.println("[2] -- [L]ibrereta");
        System.out.println("[3] -- [T]abla de Caracteres");
        System.out.println("[4] -- A[R]reglo Numerica");
        System.out.println("[5] -- [N]umero");
        System.out.println("[6] -- [M]atriz");
        System.out.println();
        System.out.println("[9] -- [A]tras");
        System.out.println("[0] -- [S]alir");
        char[] op = {'1', '2', '3', '4', '5', '6', '9', '0',
                     'C', 'L', 'T', 'R', 'N', 'M', 'A', 'S'};
        return op;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Metodos de Control ">
    /**
     * Pedir el Usuario para una opcion en uno de los menus.
     * @param prompt la linea con que pedimos el usuario para una opcion
     * @param posib las possibilidades. si el usuario no responde con uno
     * de esas, sabemos que es una respuesta invalida y que hay que pedir
     * de nuevo
     * @return una respuesta valida
     */
    public char obtenerRespuesta(String prompt, char[] posib) {
        System.out.print(prompt);
        try {
            String res = LECTOR.nextLine();
            char c = res.toUpperCase().charAt(0);
            if(existe(c, posib)){
                return c;
            } else {
                System.out.println("\'" + c + "\' es una opcion invalida!");
                return obtenerRespuesta(prompt, posib);
            }
        } catch(NoSuchElementException nsee) {
            System.out.println("Hay que ingresar una linea!");
            return obtenerRespuesta(prompt, posib);
        }
    }
    
    /**
     * Pedir el usuario para una linea. Si nos da nada, sabemos que es invalido
     * y pedimos de nuevo.
     * @param prompt la linea con que pedimos el usuario para una linea
     * @return la linea del usuario
     */
    public String obtenerRespuesta(String prompt) {
        System.out.print(prompt);
        try {
            return LECTOR.nextLine();
        } catch (NoSuchElementException nsee) {
            System.out.println("Hay que ingresar algo!");
            return obtenerRespuesta(prompt);
        }
    }
    
    /**
     * Volver a algun menu anterior
     * @param menus el numero de menus atras que queremos ir
     */
    public void irAtras(byte menus){
        if(pos > 0) pos--;
        if(!tiene_mas) tiene_mas = true;
        if(menus > 1){
            menus--;
            irAtras(menus);
        }
    }
    
    /**
     * Preguntar el usuario si quiere salir
     * @return las opciones possibles
     */
    public char[] salir_pregunta(){
        System.out.println("Ud. esta seguro que quiere salir?");
        System.out.println("[0] -- [N]o");
        System.out.println("[1] -- [S]i");
        char[] sal_preg = {'S', 'N', '1', '0'};
        return sal_preg;
    }
    //</editor-fold>

    //<editor-fold defaultstate="collapsed" desc=" Metodos Auxiliares ">
    /**
     * @return la pos
     */
    public byte getPos() {
        return pos;
    }

    /**
     * @return si tiene mas menus
     */
    public boolean tieneMas() {
        return tiene_mas;
    }

    /**
     * Prueba de existencia
     * @param c valor que queremos ver si existe
     * @param posib el lugar donde estamos buscando
     * @return verdadero si existe, si no falso
     */
    private boolean existe(char c, char[] posib) {
        for(char t :  posib){
            if(t == c){
                return true;
            }
        }
        return false;
    }
    
    //</editor-fold>
    
}
