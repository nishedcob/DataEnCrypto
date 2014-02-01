package data_en_crypto;

import data_en_crypto.flujos.entrada.E_Archivo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Interfaz Grafica para Usuarios Avanzadas
 * @author nyx
 */
public class DataEnCrypto_GUI_Avanzada extends JFrame{// implements ActionListener {

    /**
     * Un objeto para guardar la configuracion de la entrada y dar una interfaz grafica
     */
    DataEnCrypto_GUI_AEC entrada_config;

    /**
     * Configuracion de Entrada
     * @param e evento (click) que llamo a este metodo
     */
    private void configEntrada(ActionEvent e) {
        entrada_config = new DataEnCrypto_GUI_AEC();
    }

    DataEnCrypto_GUI_ALC llave_config;

    /**
     * Configuracion de Llave
     * @param e evento (click) que llamo a este metodo
     */
    private void configLlave(ActionEvent e) {
        llave_config = new DataEnCrypto_GUI_ALC();
    }

    DataEnCrypto_GUI_ASC salida_config;

    /**
     * Configuracion de Salida
     * @param e evento (click) que llamo a este metodo
     */
    private void configSalida(ActionEvent e) {
        salida_config = new DataEnCrypto_GUI_ASC();
    }

    /**
     * Configuraciones Addicionales
     * @param e evento (click) que llamo a este metodo
     */
    private static void configAddicional(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Aun no implimentado! En una futura version existara esta posibilidad.",
                "Configuracion Addicional", JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Procesar los datos con la configuracion de ahora
     * @param e evento (click) que llamo a este metodo
     */
    private void procesar(ActionEvent e) {
        jtaConsola.setText("Processando:\n");
        if(new File("e_cfg.tmp").exists()){
            jtaConsola.setText(jtaConsola.getText() + "Configuracion de Entrada Existe.\n");
            if(new File("l_cfg.tmp").exists()) {
                jtaConsola.setText(jtaConsola.getText() + "Configuracion de Llave Existe.\n");
                jtaConsola.setText(jtaConsola.getText() + "Tratando de cargar Configuracion de Entrada al RAM.\n");
                try {
                    E_Archivo config_e = new E_Archivo("e_cfg.tmp", true);
                    jtaConsola.setText(jtaConsola.getText() + "Cargo Configuracion de Entrada con exito.\n");
                    jtaConsola.setText(jtaConsola.getText() + "Tratando de cargar Configuracion de Llave al RAM.\n");
                    try {
                        E_Archivo config_l = new E_Archivo("l_cfg.tmp", true);
                        jtaConsola.setText(jtaConsola.getText() + "Cargo Configuracion de Entrada con exito.\n");
                        String[] e_cfg = config_e.getData().split(",");
                        String[] l_cfg = config_l.getData().split(",");
                        config_e = null;
                        config_l = null;
                        System.gc();

                    } catch (IOException ioe) {
                        jtaConsola.setText(jtaConsola.getText() + "No pudo cargar la configuracion de Llave (" + new File("l_cfg.tmp").getAbsolutePath() + ") en RAM.\n"
                                + "Salio con 1 error:\n"
                                + "\tNo pudo cargar la configuracion de Llave.");
                    }
                } catch (IOException ioe) {
                    jtaConsola.setText(jtaConsola.getText() + "No pudo cargar la configuracion de Entrada (" + new File("e_cfg.tmp").getAbsolutePath() + ") en RAM.\n"
                            + "Salio con 1 error:\n"
                            + "\tNo pudo cargar la configuracion de Entrada.");
                }
            } else {
                jtaConsola.setText(jtaConsola.getText() + "Configuacion de Llave no existe. Por favor configura la llave.\n"
                        + "Salio con 1 error:\n"
                        + "\tFalta de Configuracion de Llave.");
                JOptionPane.showMessageDialog(null, "No existe una configuracion de Llave", "Error: Configuracion de Llave no Encontrado", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            jtaConsola.setText(jtaConsola.getText() + "Configuacion de Entrada no existe. Por favor configura la entrada.\n"
                + "Salio con 1 error:\n"
                + "\tFalta de Configuracion de Entrada.");
            JOptionPane.showMessageDialog(null, "No existe una configuracion de Entrada", "Error: Configuracion de Entrada no Encontrado", JOptionPane.ERROR_MESSAGE);
        }
        /*JOptionPane.showMessageDialog(null, "Aun no implimentado!",
                "Proceso", JOptionPane.WARNING_MESSAGE);*/
    }

    /**
     * El panel que contiene todo
     */
    JPanel jpLayout;

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

    JScrollPane jspConsola;

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
        super("DataEnCrypto v" + Cargador.getVersion() + " ALPHA (GUI Avanzada)");
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
                configEntrada(e);
            }
        });
        jpLayout.add(jbConfigEntrada);
        
        jbConfigLlave = new JButton("Configurar Llave");
        jbConfigLlave.setBounds(10, 70, 175, 20);
        jbConfigLlave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configLlave(e);
            }
        });
        jpLayout.add(jbConfigLlave);
        
        jbConfigSalida = new JButton("Configurar Salida");
        jbConfigSalida.setBounds(10, 100, 175, 20);
        jbConfigSalida.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                configSalida(e);
            }
        });
        jpLayout.add(jbConfigSalida);
        
        jlConsola = new JLabel("Consola:");
        jlConsola.setBounds(200, 10, 100, 20);
        jpLayout.add(jlConsola);

        jtaConsola = new JTextArea();
        jtaConsola.setBounds(0, 0, 365, 70);
        jtaConsola.setBackground(Color.BLACK);
        jtaConsola.setForeground(Color.WHITE);

        jspConsola = new JScrollPane(jtaConsola);
        jspConsola.setBounds(200, 35, 385, 90);
        jspConsola.setPreferredSize(new Dimension(385, 90));
        jspConsola.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspConsola.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        jpLayout.add(jspConsola);
        
        jbAtras = new JButton("Atras");
        jbAtras.setBounds(10, 135, 100, 20);
        jbAtras.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        jpLayout.add(jbAtras);
        
        jbSalir = new JButton("Salir");
        jbSalir.setBounds(120, 135, 100, 20);
        jbSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        jpLayout.add(jbSalir);
        
        jbConfigAddicional = new JButton("Eliminar Configuraciones");
        jbConfigAddicional.setBounds(230, 135, 210, 20);
        jbConfigAddicional.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //DataEnCrypto_GUI_Avanzada.configAddicional(e);
                boolean ent = false, lla = false, dos;
                File f;
                try {
                    f = new File("e_cfg.tmp");
                    if(f.exists()){
                        f.delete();
                        ent = true;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se borro e_cfg.tmp. Ud. debe borrarlo manualmente.", "ERROR EN BORRAR e_cfg.tmp", JOptionPane.ERROR_MESSAGE);
                }
                try {
                    f = new File("l_cfg.tmp");
                    if(f.exists()){
                        f.delete();
                        lla = true;
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "No se borro l_cfg.tmp. Ud. debe borrarlo manualmente.", "ERROR EN BORRAR l_cfg.tmp", JOptionPane.ERROR_MESSAGE);
                }
                dos = ent && lla;
                if(dos){
                    jtaConsola.setText("Los dos configuraciones se eliminaron correctamente.");
                } else if (ent) {
                    jtaConsola.setText("Se elimino configuracion de Entrada correctamente.");
                } else if (lla) {
                    jtaConsola.setText("Se elimino configuracion de Llave correctamente.");
                } else {
                    jtaConsola.setText("Ningun configuracion fue eliminado.");
                }
            }
        });
        jpLayout.add(jbConfigAddicional);
        
        jbProcesar = new JButton("Procesar");
        jbProcesar.setBounds(485, 135, 100, 20);
        jbProcesar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                procesar(e);
            }
        });
        jpLayout.add(jbProcesar);

        this.setVisible(true);
    }

    /**
     * Metodo principal de este clase
     * @param args argumentos bajo que el programa se ejecuto en la consola
     */
    public static void main(String[] args) {
        new DataEnCrypto_GUI_Avanzada();
    }
}
