/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.llave;

import data_en_crypto.flujos.entrada.E_Archivo;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Un flujo de entrada de llave para archivos
 * @author nyx
 */
final public class L_Archivo extends E_Archivo implements Llave_Tipos {//Llave{
    /**
     * La llave encapsulado por este clase
     */
    final private Llave L;

    /**
     * Constructor para abrir un nuevo archivo de llave
     * @param dir Directorio en donde se encuentra el archivo.
     * @param nom Nombre del archivo.
     * @param ext Extension del archivo.
     * @param leerAlRAM ¿Debemos copiar todo el contenido del archivo al RAM?
     * @param tipo el tipo de llave que debemos generar
     * @throws FileNotFoundException si el archivo no fue encontrado
     */
    public L_Archivo(String dir, String nom, String ext, boolean leerAlRAM, byte tipo) throws FileNotFoundException {
        super(dir, nom, ext, leerAlRAM);
        L = this.crearLlave(tipo);
    }

    /**
     * Crea un flujo de archivo de entrada con todo el path completo
     * @param path todo el path completo
     * @param leerAlRAM si debemos leer al RAM o no
     * @param tipo el tipo de llave que debemos generar
     * @throws FileNotFoundException si el archivo no fue encontrado
     */
    public L_Archivo(String path, boolean leerAlRAM, byte tipo) throws FileNotFoundException {
        super(path, leerAlRAM);
        L = this.crearLlave(tipo);
    }

    /**
     * Crea un nuevo flujo de llave con datos ya leidos.
     *
     * @param data   datos para almacenar en este objeto
     * @param lector el scanner que este entrada debe usar para leer
     *               nuevos datos despues
     */
    public L_Archivo(String data, Scanner lector, byte tipo) {
        super(data, lector);
        L = this.crearLlave(tipo);
    }

    /**
     * Usando el tipo de llave, lea los datos de la forma necessario para crear un nueva llave
     * @param tipo el tipo de llave que debemos generar
     * @return la llave nueva
     */
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
                throw new UnsupportedOperationException("Not Supported yet.");
                //break;
            case TIPO_LLAVE_NUMERICA:
                return new Llave(TIPO_LLAVE_NUMERICA, (int)super.leerDato());
                //break;
            case TIPO_LLAVE_ARREGLO:
                throw new UnsupportedOperationException("Not Supported yet.");
            case TIPO_LLAVE_MATRIZ:
                throw new UnsupportedOperationException("Not Supported yet.");
                //break;
            default:
                return new Llave();
        }
        //return new Llave();
    }

    /**
     * Devuelve la llave encapsulado por este objeto
     * @return la llave de este flujo
     */
    public Llave getL(){
        return L;
    }

}
