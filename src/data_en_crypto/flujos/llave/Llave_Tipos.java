/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_en_crypto.flujos.llave;

/**
 * Constantes para cada tipo de llave que puede ser.
 * @author nyx
 */
public interface Llave_Tipos {
    //tipos basados en caracteres
    /** Codigo para una llave de tipo clave. */
    public final byte TIPO_LLAVE_CLAVE = 10;
    /** Codigo para una llave de tipo librereta. */
    public final byte TIPO_LLAVE_LIBRERETA = 11;
    /** Codigo para una llave de tipo tabla. */
    public final byte TIPO_LLAVE_TABLA = 110;
    //tipos basados en numeros
    /** Codigo para una llave de tipo numerica. */
    public final byte TIPO_LLAVE_NUMERICA = 5;
    /** Codigo para una llave de tipo matriz. */
    public final byte TIPO_LLAVE_MATRIZ = 55;
    
}
