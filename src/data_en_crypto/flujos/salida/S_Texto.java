/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.salida;


/**
 * Flujo de Salida por un Panel de Texto (como una consola).
 * @author nyx
 */
public class S_Texto extends Salida{

    /**
     * Constructor Vacia.
     */
    public S_Texto() {
        super();
    }
    
    /**
     * Imprime un objecto
     * @param o el objeto que queremos imprimir
     */
    public void imprimir(Object o){
        System.out.print(o);
    }
    
    /**
     * Imprime un String
     * @param s el string que queremos imprimir
     */
    @Override
    public void imprimir(String s){
        System.out.print(s);
    }
    
    /**
     * imprime un objeto y despues una salta de linea
     * @param o el objeto para imprimir
     */
    public void imprimirln(Object o){
        System.out.println(o);
    }
    
    /**
     * imprime un String y despues una salta de linea
     * @param s el String para imprimir
     */
    @Override
    public void imprimirln(String s){
        System.out.println(s);
    }
    
    /**
     * Imprime todo el contenido de este flujo.
     */
    @Override
    public void imprimirtodo(){
        System.out.println(data);
    }
    
}
