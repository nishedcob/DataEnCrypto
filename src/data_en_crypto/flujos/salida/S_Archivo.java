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
 *
 * @author nyx
 */
public class S_Archivo extends Salida{
    
    private final FileWriter fw;

    public S_Archivo(String path, boolean append) throws IOException {
        super(new File(path));
        this.fw = new FileWriter(super.f, append);
    }
    
    @Override
    public void imprimir(String s) throws IOException{
        fw.write(s);
        fw.flush();
    }
    
    @Override
    public void imprimirln(String s) throws IOException{
        fw.write(s + "\n");
        fw.flush();
    }

    @Override
    public void imprimirtodo() throws IOException {
        fw.write(data);
        fw.flush();
    }
    
    public void guardar() throws IOException {
        fw.flush();
    }
    
    public void cerrar() throws IOException {
        fw.flush();
        fw.close();
    }
}
