/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto.flujos.entrada;

/**
 *
 * @author nyx
 */
public class E_Texto extends Entrada{

    public E_Texto(String Val_Salida) {
        super();
        data = leerData("", Val_Salida);
    }

    private String leerData(String data, String Val_Salida) {
        String tmp = lector.nextLine();
        if(tmp == null ? Val_Salida == null : tmp.equals(Val_Salida)){
            return data;
        } else {
            data += tmp;
            return leerData(data, Val_Salida);
        }
    }
    
}
