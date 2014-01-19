/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.salida;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Flujo de Salida por medio de un Archivo
 * @author nyx
 */
public class S_Archivo extends Salida{
    
    /**
     * El objeto que nos va a permitir escribir al objeto.
     */
    private final FileWriter fw;

    /**
     * Constuctor que inicializa los objetos necesarios para escribir a un archivo.
     * @param path el directorio, nombre y extencion del archivo querido
     * @param append un boolean que nos dice si el usuario quiere escribir al 
     * final del archivo o sobre escribirlo
     * @throws IOException si encuentre algun error con el archivo
     */
    public S_Archivo(String path, boolean append) throws IOException {
        super(new File(path));
        this.fw = new FileWriter(super.f, append);
    }
    
    /**
     * Imprime un String
     * @param s el string que queremos imprimir
     * @throws IOException si encuentre algun error con el archivo
     */
    @Override
    public void imprimir(String s) throws IOException{
        fw.write(s);
        fw.flush();
    }
    
    /**
     * imprime un String y despues una salta de linea
     * @param s el String para imprimir
     * @throws IOException si encuentre algun error con el archivo
     */
    @Override
    public void imprimirln(String s) throws IOException{
        fw.write(s + "\n");
        fw.flush();
    }

    /**
     * Imprime todo el contenido de este flujo.
     * @throws IOException si encuentre algun error con el archivo
     */
    @Override
    public void imprimirtodo() throws IOException {
        fw.write(data);
        fw.flush();
    }
    
    /**
     * Guarda el archivo.
     * @throws IOException si encuentre algun error con el archivo
     */
    public void guardar() throws IOException {
        fw.flush();
    }
    
    /**
     * Ciera el archivo.
     * @throws IOException si encuentre algun error con el archivo
     */
    public void cerrar() throws IOException {
        fw.flush();
        fw.close();
    }
}
