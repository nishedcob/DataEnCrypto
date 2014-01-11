/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.entrada;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author nyx
 */
final public class E_Archivo extends Entrada{
    private final String DIR;
    private final String NOMBRE;
    private final String EXTEN;

    public E_Archivo(String DIR, String NOMBRE, String EXTEN) throws FileNotFoundException {
        super(DIR + NOMBRE + EXTEN);
        this.DIR = DIR;
        this.NOMBRE = NOMBRE;
        this.EXTEN = EXTEN;
        data = this.leerData();
    }

    private String leerData() {
        String leer = "";
        while(lector.hasNextLine()){
            leer += lector.nextLine() + '\n';
        }
        return leer;
    }
    
}
