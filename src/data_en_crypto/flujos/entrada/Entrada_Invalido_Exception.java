package data_en_crypto.flujos.entrada;

/**
 * Exception para cuando un entrada no es valido
 * Created by nyx at 1:09 PM on 02 February 2014.
 */
public class Entrada_Invalido_Exception extends Exception {
    /**
     * Constructs a new exception with {@code null} as its detail message.
     * The cause is not initialized, and may subsequently be initialized by a
     * call to {@link #initCause}.
     */
    public Entrada_Invalido_Exception() {
        super("Error! Configuracion de Entrada es Invalido!");
    }
    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public Entrada_Invalido_Exception(String message) {
        super(message);
    }
}
