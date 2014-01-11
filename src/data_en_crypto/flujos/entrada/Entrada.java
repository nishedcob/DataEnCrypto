/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.entrada;

import data_en_crypto.flujos.Flujo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author nyx
 */
public class Entrada extends Flujo{
    protected Scanner lector;
    protected File f = null;

    public Entrada(String path) throws FileNotFoundException {
        super();
        f = new File(path);
        lector = new Scanner(f);
    }
    
    public Entrada(){
        super();
        lector = new Scanner(System.in);
    }
}
