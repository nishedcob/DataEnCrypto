package data_en_crypto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * Interfaz Grafica para Usuarios Avanzadas
 * @author nyx
 */
public class DataEnCrypto_GUI_Avanzada extends JFrame implements ActionListener {

    /**
     * Un objeto para guardar la configuracion de la entrada y dar una interfaz grafica
     */
    static DataEnCrypto_GUI_AEC entrada_config;

    /**
     * Configuracion de Entrada
     * @param e evento (click) que llamo a este metodo
     */
    private static void configEntrada(ActionEvent e) {
        entrada_config = new DataEnCrypto_GUI_AEC();
    }

    /**
     * Configuracion de Llave
     * @param e evento (click) que llamo a este metodo
     */
    private static void configLlave(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aun no implimentado!", 
                "Configuracion de Llave", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Configuracion de Salida
     * @param e evento (click) que llamo a este metodo
     */
    private static void configSalida(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aun no implimentado!", 
                "Configuracion de Salida", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Configuraciones Addicionales
     * @param e evento (click) que llamo a este metodo
     */
    private static void configAddicional(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aun no implimentado!", 
                "Configuracion Addicional", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Procesar los datos con la configuracion de ahora
     * @param e evento (click) que llamo a este metodo
     */
    private static void procesar(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aun no implimentado!", 
                "Proceso", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * El panel que contiene todo
     */
    JPanel jpLayout;

    // los butones
    /**
     * Buton para configurar la entrada
     */
    JButton jbConfigEntrada;
    /**
     * Buton para configurar la llave
     */
    JButton jbConfigLlave;
    /**
     * Buton para configurar la salida
     */
    JButton jbConfigSalida;
    /**
     * Buton para ir al menu principal
     */
    JButton jbAtras;
    /**
     * Buton para salir/cerrar el programa
     */
    JButton jbSalir;
    /**
     * Buton para configuraciones extras
     */
    JButton jbConfigAddicional;
    /**
     * Buton para iniciar el proceso
     */
    JButton jbProcesar;

    /**
     * Label para la consola
     */
    JLabel jlConsola;

    /**
     * Salida de la consola
     */
    JTextArea jtaConsola;

    /**
     * Constuctor para Este Interfaz Grafica (pone todos los objetos en sus propios
     * lugares y despues dibuja la ventana)
     * @throws HeadlessException
     */
    public DataEnCrypto_GUI_Avanzada() throws HeadlessException {
        super("DataEnCrypto (GUI Avanzada)");
        //this.setLocationRelativeTo(null);
        this.setSize(600, 200);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        
        jpLayout = new JPanel();
        jpLayout.setLayout(null);
        this.setContentPane(jpLayout);
        
        jbConfigEntrada = new JButton("Configurar Entrada");
        jbConfigEntrada.setBounds(10, 40, 175, 20);
        jbConfigEntrada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataEnCrypto_GUI_Avanzada.configEntrada(e);
            }
        });
        jpLayout.add(jbConfigEntrada);
        
        jbConfigLlave = new JButton("Configurar Llave");
        jbConfigLlave.setBounds(10, 70, 175, 20);
        jbConfigLlave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataEnCrypto_GUI_Avanzada.configLlave(e);
            }
        });
        jpLayout.add(jbConfigLlave);
        
        jbConfigSalida = new JButton("Configurar Salida");
        jbConfigSalida.setBounds(10, 100, 175, 20);
        jbConfigSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataEnCrypto_GUI_Avanzada.configSalida(e);
            }
        });
        jpLayout.add(jbConfigSalida);
        
        jlConsola = new JLabel("Consola:");
        jlConsola.setBounds(200, 10, 100, 20);
        jpLayout.add(jlConsola);
        
        jtaConsola = new JTextArea();
        jtaConsola.setBounds(200, 35, 385, 90);
        jtaConsola.setBackground(Color.BLACK);
        jtaConsola.setForeground(Color.WHITE);
        jpLayout.add(jtaConsola);
        
        jbAtras = new JButton("Atras");
        jbAtras.setBounds(10, 135, 100, 20);
        jbAtras.addActionListener(this);
        jpLayout.add(jbAtras);
        
        jbSalir = new JButton("Salir");
        jbSalir.setBounds(120, 135, 100, 20);
        jbSalir.addActionListener(this);
        jpLayout.add(jbSalir);
        
        jbConfigAddicional = new JButton("Configuracion Addicional");
        jbConfigAddicional.setBounds(230, 135, 210, 20);
        jbConfigAddicional.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataEnCrypto_GUI_Avanzada.configAddicional(e);
            }
        });
        jpLayout.add(jbConfigAddicional);
        
        jbProcesar = new JButton("Procesar");
        jbProcesar.setBounds(485, 135, 100, 20);
        jbProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataEnCrypto_GUI_Avanzada.procesar(e);
            }
        });
        jpLayout.add(jbProcesar);
        
        //this.add(jpLayout);
        this.setVisible(true);
    }

    /**
     * Metodo principal de este clase
     * @param args argumentos bajo que el programa se ejecuto en la consola
     */
    public static void main(String[] args) {
        new DataEnCrypto_GUI_Avanzada();
    }

    /**
     * Un metodo para trapar clicks en nuestra interfaz
     * @param e evento (click) que llamo a este metodo
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(jbAtras)){
            this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            this.dispose();
            JOptionPane.showMessageDialog(null, "Normalmente se lanzaria "
                    + "el menu principal en este momento, pero como no esta "
                    + "implimentado, sale este mensaje su lugar.", "Atras",
                    JOptionPane.WARNING_MESSAGE);
        } else if(e.getSource().equals(jbSalir)) {
            this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            this.dispose();
        }
    }
}
