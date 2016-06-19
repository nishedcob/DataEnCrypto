
package data_en_crypto;

import data_en_crypto.cifras.Librereta_de_un_Solo_Uso;
import data_en_crypto.cifras.Vigenere;
import data_en_crypto.flujos.llave.L_Texto;
import data_en_crypto.flujos.llave.Llave_Tipos;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Interfaz Basica
 * @author nyx
 */
public class DataEnCrypto_GUI_Basico extends javax.swing.JFrame implements Llave_Tipos {

    private final JTextArea jtaLlave;
    private final JScrollPane jspLlave;
    private final JLabel jlCifra;
    private final JLabel jlModo;
    /**
     * Panel de contenido
     */
    JPanel jpDiseno;

    /**
     * Texto del titulo
     */
    JLabel jlTitulo;

    /**
     * Marcadores de Entrada, Salida y Llave respectivamente
     */
    JLabel jlEntrada, jlSalida, jlLlave;

    /**
     * Para facilitar lectura del caja de texto de entrada
     */
    JScrollPane jspEntrada;

    /**
     * Caja de texto para mostrar la entrada
     */
    JTextArea jtaEntrada;

    /**
     * Para facilitar lectura del caja de texto de salida
     */
    JScrollPane jspSalida;

    /**
     * Caja de texto para mostrar la salida
     */
    JTextArea jtaSalida;

    /**
     * Buton para mostrar la entrada
     */
    JButton jbEntradaMostrar;

    /**
     * Buton para mostrar la salida
     */
    JButton jbSalidaMostrar;

    /**
     * Campo para la dirreccion y lugar del archivo de entrada
     */
    JTextField jtfEntrada;

    /**
     * Buton para seleccionar el archivo que uno quiere abrir
     */
    JButton jbEntradaAbrir;

    /**
     * Campo para la dirreccion y lugar del archivo de salida
     */
    JTextField jtfSalida;

    /**
     * Buton para seleccionar donde y el nombre del archivo en cual uno quiere guardar
     */
    JButton jbSalidaGuardar;

    /**
     * Lugar para ingresar su clave
     */
    JPasswordField jpfClave;

    /**
     * Menu para seleccionar su algoritmo
     */
    JComboBox jcbAlgoritmo;

    /**
     * Menu para seleccion su modo de operacion
     */
    JComboBox jcbModo;

    /**
     * Buton para iniciar el processo
     */
    JButton jbProcesar;

    /**
     * Buton para salir
     */
    JButton jbSalir;

    GridBagLayout gbl;
    GridBagConstraints gbc;

    /**
     * Constructor de la clase (dibuja todo)
     */
    public DataEnCrypto_GUI_Basico() {
        super("DataEnCrypto v" + Cargador.getVersion() + " ALPHA (GUI Basico)");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(1000, 500);

        gbl = new GridBagLayout();
        gbc = new GridBagConstraints();
        int fila = 0;
        final int COL1 = 0, COL2 = 1, COL3 = 2, COL4 = 3, COL5 = 4, COL6 = 5;

        jpDiseno = new JPanel(null);
        this.setContentPane(jpDiseno);
        jpDiseno.setLayout(gbl);

        jlTitulo = new JLabel("Bienvenidos a DataEnCrypto v" + Cargador.getVersion() + " ALPHA");
        gbc.gridy = fila++;
        gbc.gridx = COL1;
        gbc.gridwidth = 6;
        jpDiseno.add(jlTitulo, gbc);

        jlEntrada = new JLabel("Entrada");
        gbc.gridy = fila++;
        gbc.gridwidth = 2;
        jpDiseno.add(jlEntrada, gbc);

        jlLlave = new JLabel("Llave");
        gbc.gridx = COL3;
        jpDiseno.add(jlLlave, gbc);

        jlSalida = new JLabel("Salida");
        gbc.gridx = COL5;
        jpDiseno.add(jlSalida, gbc);

        jtaEntrada = new JTextArea();
        jtaEntrada.setColumns(30);
        jtaEntrada.setRows(10);
        jtaEntrada.setEnabled(true);

        jspEntrada = new JScrollPane(jtaEntrada);
        jspEntrada.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jspEntrada.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jspEntrada.setEnabled(true);
        gbc.gridx = COL1;
        gbc.gridy = fila++;
        jpDiseno.add(jspEntrada, gbc);

        jtaLlave = new JTextArea();
        jtaLlave.setColumns(30);
        jtaLlave.setRows(10);
        jtaLlave.setEnabled(true);

        jspLlave = new JScrollPane(jtaLlave);
        jspLlave.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jspLlave.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jspLlave.setEnabled(true);
        gbc.gridx = COL3;
        jpDiseno.add(jspLlave, gbc);

        jtaSalida = new JTextArea();
        jtaSalida.setColumns(30);
        jtaSalida.setRows(10);
        jtaSalida.setEnabled(true);

        jspSalida = new JScrollPane(jtaSalida);
        jspSalida.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        jspSalida.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        jspSalida.setEnabled(true);
        gbc.gridx = COL5;
        jpDiseno.add(jspSalida, gbc);

        gbc.gridwidth = 1;
        jlCifra = new JLabel("Cifra");
        gbc.gridx = COL1;
        gbc.gridy = fila++;
        jpDiseno.add(jlCifra, gbc);

        String[] algoritmos = {"", "Librereta de un Solo Uso", "Vigenere"};
        jcbAlgoritmo = new JComboBox(algoritmos);
        gbc.gridx = COL2;
        jpDiseno.add(jcbAlgoritmo, gbc);

        jlModo = new JLabel("Modo");
        gbc.gridx = COL3;
        jpDiseno.add(jlModo, gbc);

        String[] modos = {"", "Encriptar", "Desencriptar"};
        jcbModo = new JComboBox(modos);
        gbc.gridx = COL4;
        jpDiseno.add(jcbModo, gbc);

        jbProcesar = new JButton("Procesar");
        jbProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcbModo.getSelectedIndex() != 0 && jcbAlgoritmo.getSelectedIndex() != 0){
                    boolean encriptar = jcbModo.getSelectedIndex() == 1;
                    byte algo = (byte) jcbAlgoritmo.getSelectedIndex();
                    String entrada = jtaEntrada.getText();
                    String llave = jtaLlave.getText();
                    String salida = "";
                    switch(algo){
                        case 1:
                            Librereta_de_un_Solo_Uso ldusu = new Librereta_de_un_Solo_Uso(llave);
                            if (encriptar){
                                salida = ldusu.encriptar(entrada);
                            } else {
                                salida = ldusu.decifrar(entrada);
                            }
                            break;
                        case 2:
                            Vigenere vig = new Vigenere(llave);
                            if(encriptar) {
                                salida = vig.encriptar(entrada);
                            } else {
                                salida = vig.decifrar(entrada);
                            }
                            break;
                    }
                    jtaSalida.setText(salida);
                } else {
                    JOptionPane.showMessageDialog(null, "Falta llenar un campo", "Falta llenar un campo", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        gbc.gridx = COL5;
        jpDiseno.add(jbProcesar, gbc);

        jbSalir = new JButton("Salir");
        gbc.gridx = COL6;
        jbSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jpDiseno.add(jbSalir, gbc);

        this.setVisible(true);
    }
}
