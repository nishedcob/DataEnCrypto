/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.salida;

/**
 *
 * @author nyx
 */
public class S_Texto extends Salida{

    public S_Texto() {
        super();
    }
    
    public void imprimir(Object o){
        System.out.print(o);
    }
    
    public void imprimir(String s){
        System.out.print(s);
    }
    
    public void imprimirln(Object o){
        System.out.println(o);
    }
    
    public void imprimirln(String s){
        System.out.println(s);
    }
    
    public void imprimirtodo(){
        System.out.println(data);
    }
    
}
