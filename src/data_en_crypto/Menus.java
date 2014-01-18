/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_en_crypto;

import data_en_crypto.flujos.entrada.E_Texto;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 *
 * @author nyx
 */
class Menus {

    final static byte NUM_MENUS = 2;
    private byte pos = 0;
    private boolean tiene_mas = true;
    private E_Texto teclado;
    private Scanner lector;
    
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
    private char[] algoritmos() {
        System.out.println("Elegir un algoritmo:");
        System.out.println("[1] -- [A]utoTexto");
        System.out.println("[2] -- [C]esar");
        System.out.println("[3] -- [L]ibrereta de Un Solo Uso");
        System.out.println("[4] -- [M]atrices");
        System.out.println("[5] -- V[I]CTOR");
        System.out.println("[6] -- [V]igenere");
        System.out.println();
        System.out.println("[0] -- [S]alir");
        char[] op = {'1', '2', '3', '4', '5', '6', '0', 
                    'A', 'C', 'L', 'M', 'I', 'V', 'S'};
        return op;
    }

    private char[] modo() {
        System.out.println("Elegir un modo de operacion:");
        System.out.println("[1] -- [E]ncriptar");
        System.out.println("[2] -- [D]ecifrar");
        System.out.println();
        System.out.println("[3] -- [A]tras");
        System.out.println("[0] -- [S]alir");
        char[] op = {'1', '2', '3', '0',
                    'E', 'D', 'A', 'S'};
        return op;
    }
    //</editor-fold>

    public char obtenerRespuesta(String prompt, char[] posib) {
        System.out.print(prompt);
        try {
            String res = lector.nextLine();
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
