/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.entrada;

import java.util.Scanner;

/**
 *
 * @author nyx
 */
public class E_Texto extends Entrada{

    /**
     * Constructor que guarda todo el data leido en data del flujo.
     * @param Val_Salida la linea con cual no leimos mas data
     */
    public E_Texto(String Val_Salida) {
        super();
        data = leerData("", Val_Salida);
    }

    /**
     * Crea un nuevo flujo de entrada de texto con datos ya leidos.
     *
     * @param data   datos para almacenar en este objeto
     * @param lector el scanner que este entrada debe usar para leer
     */
    public E_Texto(String data, Scanner lector) {
        super(data, lector);
    }

    /**
     * Lea datos hasta que llega al valor de salida.
     * @param Val_Salida la linea con cual no leimos mas data
     * @return todo el data leido menos la linea que es el valor de salida
     */
    private String leerData(String data, String Val_Salida) {
        String tmp = lector.nextLine();
        if(tmp == null ? Val_Salida == null : tmp.equals(Val_Salida)){
            return data;
        } else {
            data += tmp + "\n";
            return leerData(data, Val_Salida);
        }
    }
    
}
