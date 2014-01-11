/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.llave;

import data_en_crypto.flujos.entrada.E_Archivo;
import java.io.FileNotFoundException;

/**
 *
 * @author nyx
 */
final public class L_Archivo extends E_Archivo {//Llave{
    final private Llave L;
    
    public L_Archivo(String dir, String nom, String ext, boolean leerAlRAM, byte tipo) throws FileNotFoundException {
        super(dir, nom, ext, leerAlRAM);
        L = this.crearLlave(tipo);
    }

    private Llave crearLlave(byte tipo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
