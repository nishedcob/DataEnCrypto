
package data_en_crypto;

import data_en_crypto.cifras.AutoTexto;
import data_en_crypto.cifras.Cesar;
import data_en_crypto.cifras.Librereta_de_un_Solo_Uso;
import data_en_crypto.cifras.Vigenere;
import data_en_crypto.flujos.entrada.E_Archivo;
import data_en_crypto.flujos.llave.L_Archivo;
import data_en_crypto.flujos.llave.L_Texto;
import data_en_crypto.flujos.llave.Llave_Tipos;
import data_en_crypto.flujos.salida.S_Archivo;

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
    JComboBox<Object> jcbAlgoritmo;

    /**
     * Menu para seleccion su modo de operacion
     */
    JComboBox jcbModo;

    /**
     * Buton para iniciar el processo
     */
    JButton jbProcesar;

    /**
     * Buton para volver al menu anterior
     */
    JButton jbAtras;

    /**
     * Buton para salir
     */
    JButton jbSalir;

    /**
     * Constructor de la clase (dibuja todo)
     */
    public DataEnCrypto_GUI_Basico() {
        super("DataEnCrypto v" + Cargador.getVersion() + " ALPHA (GUI Basico)");
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(605, 500);

        jpDiseno = new JPanel(null);
        this.setContentPane(jpDiseno);

        jlTitulo = new JLabel("Bienvenidos a DataEnCrypto v" + Cargador.getVersion() + " ALPHA");
        jlTitulo.setBounds(190, 10, 230, 20);
        jpDiseno.add(jlTitulo);

        jlEntrada = new JLabel("Entrada");
        jlEntrada.setBounds(20, 40, 60, 20);
        jpDiseno.add(jlEntrada);

        jlSalida = new JLabel("Salida");
        jlSalida.setBounds(320, 40, 60, 20);
        jpDiseno.add(jlSalida);

        jtaEntrada = new JTextArea();
        jtaEntrada.setBounds(0, 0, 265, 180);
        jtaEntrada.setEnabled(false);

        jspEntrada = new JScrollPane(jtaEntrada);
        jspEntrada.setBounds(10, 60, 285, 200);
        jspEntrada.setPreferredSize(new Dimension(285, 200));
        jspEntrada.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jspEntrada.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspEntrada.setEnabled(true);
        jpDiseno.add(jspEntrada);

        jtaSalida = new JTextArea();
        jtaSalida.setBounds(0, 0, 265, 180);
        jtaSalida.setEnabled(false);

        jspSalida = new JScrollPane(jtaSalida);
        jspSalida.setBounds(310, 60, 285, 200);
        jspSalida.setPreferredSize(new Dimension(285, 200));
        jspSalida.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jspSalida.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspSalida.setEnabled(true);
        jpDiseno.add(jspSalida);

        jbEntradaMostrar = new JButton("Mostrar");
        jbEntradaMostrar.setBounds(10, 270, 285, 20);
        jbEntradaMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File arch = new File(jtfEntrada.getText());
                jtaEntrada.setText("");
                if(arch.exists()){
                    try {
                        Scanner lect = new Scanner(arch);
                        while (lect.hasNextLine()){
                            jtaEntrada.setText(jtaEntrada.getText() + lect.nextLine());
                            if (lect.hasNextLine()){
                                jtaEntrada.setText(jtaEntrada.getText() + "\n");
                            }
                        }
                    } catch (FileNotFoundException fnfe) {
                        jtaEntrada.setText("No se encontro el archivo!");
                    }
                }
            }
        });
        jpDiseno.add(jbEntradaMostrar);

        jbSalidaMostrar = new JButton("Mostrar");
        jbSalidaMostrar.setBounds(310, 270, 285, 20);
        jbSalidaMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File arch = new File(jtfSalida.getText());
                jtaSalida.setText("");
                if(arch.exists()){
                    try {
                        Scanner lect = new Scanner(arch);
                        while (lect.hasNextLine()){
                            jtaSalida.setText(jtaSalida.getText() + lect.nextLine());
                            if (lect.hasNextLine()){
                                jtaSalida.setText(jtaSalida.getText() + "\n");
                            }
                        }
                    } catch (FileNotFoundException fnfe) {
                        jtaSalida.setText("No se encontro el archivo!");
                    }
                }
            }
        });
        jpDiseno.add(jbSalidaMostrar);

        jtfEntrada = new JTextField();
        jtfEntrada.setBounds(10, 300, 185, 20);
        jpDiseno.add(jtfEntrada);

        jbEntradaAbrir = new JButton("Abrir");
        jbEntradaAbrir.setBounds(205, 300, 90, 20);
        jbEntradaAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfcSelector = new JFileChooser(jtfEntrada.getText());
                int op = jfcSelector.showOpenDialog(null);
                if(op == JFileChooser.APPROVE_OPTION){
                    jtfEntrada.setText(jfcSelector.getSelectedFile().getAbsolutePath());
                }
            }
        });
        jpDiseno.add(jbEntradaAbrir);

        jtfSalida = new JTextField();
        jtfSalida.setBounds(310, 300, 165, 20);
        jpDiseno.add(jtfSalida);

        jbSalidaGuardar = new JButton("Guardar");
        jbSalidaGuardar.setBounds(485, 300, 110, 20);
        jbSalidaGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfcSelector = new JFileChooser(jtfSalida.getText());
                int op = jfcSelector.showSaveDialog(null);
                if(op == JFileChooser.APPROVE_OPTION){
                    jtfSalida.setText(jfcSelector.getSelectedFile().getAbsolutePath());
                }
            }
        });
        jpDiseno.add(jbSalidaGuardar);

        jlLlave = new JLabel("Ingresa Contraseña");
        jlLlave.setBounds(125, 350, 170, 20);
        jpDiseno.add(jlLlave);

        jpfClave = new JPasswordField();
        jpfClave.setBounds(10, 380, 285, 20);
        jpDiseno.add(jpfClave);

        Object[] algoritmos = {"", "AutoTexto", "Cesar", "Librereta de un Solo Uso", "Vigenere"};
        jcbAlgoritmo = new JComboBox<Object>(algoritmos);
        jcbAlgoritmo.setBounds(310, 350, 280, 20);
        jpDiseno.add(jcbAlgoritmo);

        Object[] modos = {"", "Encriptar", "Desencriptar"};
        jcbModo = new JComboBox<Object>(modos);
        jcbModo.setBounds(310, 380, 150, 20);
        jpDiseno.add(jcbModo);

        jbProcesar = new JButton("Procesar");
        jbProcesar.setBounds(470, 380, 120, 20);
        jbProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(jcbModo.getSelectedIndex() != 0 && jcbAlgoritmo.getSelectedIndex() != 0){
                    boolean encriptar = jcbModo.getSelectedIndex() == 1;
                    byte algo = (byte) jcbAlgoritmo.getSelectedIndex();
                    jtaEntrada.setText("");
                    E_Archivo e_archivo = null;
                    try {
                        e_archivo = new E_Archivo(jtfEntrada.getText(), true);
                    } catch (FileNotFoundException fnfe) {
                        jtaEntrada.setText("No hay accesso a: " + jtfEntrada.getText());
                    }
                    byte tipo = 0;
                    switch(algo){
                        case 1:
                        case 4:
                            tipo = TIPO_LLAVE_CLAVE;
                            break;
                        case 2:
                            tipo = TIPO_LLAVE_NUMERICA;
                            break;
                        case 3:
                            tipo = TIPO_LLAVE_LIBRERETA;
                            break;
                    }
                    String clave = "";
                    for(char c : jpfClave.getPassword()){
                        clave += c;
                    }
                    L_Texto l_texto = new L_Texto(clave, tipo);
                    S_Archivo s_archivo = null;
                    try {
                        s_archivo = new S_Archivo(jtfSalida.getText(), false);
                        String datos = "";
                        switch(algo){
                            case 1:
                                AutoTexto at = new AutoTexto(l_texto.getL().getLIBRERETA());
                                if(encriptar){
                                    datos = at.encriptar(e_archivo.getData());
                                } else {
                                    datos = at.decifrar(e_archivo.getData());
                                }
                                break;
                            case 2:
                                Cesar cesar = new Cesar(l_texto.getL().getLLAVE_NUMERICA());
                                if(encriptar){
                                    datos = cesar.encriptar(e_archivo.getData());
                                } else {
                                    datos = cesar.decifrar(e_archivo.getData());
                                }
                                break;
                            case 3:
                                Librereta_de_un_Solo_Uso ldusu = new Librereta_de_un_Solo_Uso(l_texto.getL().getLIBRERETA());
                                if (encriptar){
                                    datos = ldusu.encriptar(e_archivo.getData());
                                } else {
                                    datos = ldusu.decifrar(e_archivo.getData());
                                }
                                break;
                            case 4:
                                Vigenere vig = new Vigenere(l_texto.getL().getLIBRERETA());
                                if(encriptar) {
                                    datos = vig.encriptar(e_archivo.getData());
                                } else {
                                    datos = vig.decifrar(e_archivo.getData());
                                }
                                break;
                        }
                        s_archivo.setData(datos);
                        s_archivo.imprimirtodo();
                        s_archivo.cerrar();
                    } catch (IOException e1) {
                        jtaSalida.setText("No hay accesso a: " + jtfSalida.getText());
                    }
                }
            }
        });
        jpDiseno.add(jbProcesar);

        jbAtras = new JButton("Atras");
        jbAtras.setBounds(100, 420, 100, 20);
        jbAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        jpDiseno.add(jbAtras);

        jbSalir = new JButton("Salir");
        jbSalir.setBounds(400, 420, 100, 20);
        jbSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jpDiseno.add(jbSalir);

        this.setVisible(true);
    }
}
