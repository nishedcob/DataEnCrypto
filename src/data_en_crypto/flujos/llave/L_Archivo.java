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
final public class L_Archivo extends E_Archivo implements Llave_Tipos {//Llave{
    final private Llave L;
    
    public L_Archivo(String dir, String nom, String ext, boolean leerAlRAM, byte tipo) throws FileNotFoundException {
        super(dir, nom, ext, leerAlRAM);
        L = this.crearLlave(tipo);
    }

    private Llave crearLlave(byte tipo) {
        switch(tipo){
            case TIPO_LLAVE_CLAVE:
                if(isESTA_EN_RAM()){
                    return new Llave(TIPO_LLAVE_CLAVE, super.data);
                } else {
                    String clave = "";
                    try {
                        while(true){
                            clave += super.leerDato();
                        }
                    } catch (IndexOutOfBoundsException ioobe) {
                    } catch (Exception e) {
                    } finally {
                        return new Llave(TIPO_LLAVE_CLAVE, clave);
                    }
                }
                //break;
            case TIPO_LLAVE_LIBRERETA:
                if(isESTA_EN_RAM()){
                    return new Llave(TIPO_LLAVE_LIBRERETA, super.data);
                } else {
                    String librereta = "";
                    try {
                        while(true){
                            librereta += super.leerDato();
                        }
                    } catch (IndexOutOfBoundsException ioobe) {
                    } catch (Exception e) {
                    } finally {
                        return new Llave(TIPO_LLAVE_LIBRERETA, librereta);
                    }
                }
                //break;
            case TIPO_LLAVE_TABLA:
                break;
            case TIPO_LLAVE_NUMERICA:
                break;
            case TIPO_LLAVE_MATRIZ:
                break;
            default:
                return new Llave();
        }
        return new Llave();
    }
    
}
