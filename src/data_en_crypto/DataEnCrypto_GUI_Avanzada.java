
import java.awt.Color;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author nyx
 */
public class DataEnCrypto_GUI_Avanzada extends JFrame implements ActionListener {

    private static void configEntrada(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aun no implimentado!", 
                "Configuracion de Entrada", JOptionPane.WARNING_MESSAGE);
    }

    private static void configLlave(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aun no implimentado!", 
                "Configuracion de Llave", JOptionPane.WARNING_MESSAGE);
    }

    private static void configSalida(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aun no implimentado!", 
                "Configuracion de Salida", JOptionPane.WARNING_MESSAGE);
    }

    private static void configAddicional(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aun no implimentado!", 
                "Configuracion Addicional", JOptionPane.WARNING_MESSAGE);
    }

    private static void procesar(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aun no implimentado!", 
                "Proceso", JOptionPane.WARNING_MESSAGE);
    }

    JPanel jpLayout;
    
    JButton jbConfigEntrada;
    JButton jbConfigLlave;
    JButton jbConfigSalida;
    JButton jbAtras;
    JButton jbSalir;
    JButton jbConfigAddicional;
    JButton jbProcesar;
    
    JLabel jlConsola;
    
    JTextArea jtaConsola;

    public DataEnCrypto_GUI_Avanzada() throws HeadlessException {
        super("DataEnCrypto (GUI Avanzada)");
        //this.setLocationRelativeTo(null);
        this.setSize(600, 200);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        
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
    
    public static void main(String[] args) {
        new DataEnCrypto_GUI_Avanzada();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(jbAtras)){
            this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            this.dispose();
            JOptionPane.showMessageDialog(null, "Normalmente se lanzaria "
                    + "el menu principal en este momento, pero como no esta "
                    + "implimentado, sale este mensaje su lugar.", "Atras",
                    JOptionPane.WARNING_MESSAGE);
        } else if(e.getSource().equals(jbSalir)) {
            this.setDefaultCloseOperation(EXIT_ON_CLOSE);
            this.dispose();
        }
    }
}
