package data_en_crypto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Configuracion de Flujo de Salida
 * Created by nyx on 1/29/14 at 7:54 PM.
 */
public class DataEnCrypto_GUI_ASC extends JFrame {

    /**
     * El Panel de contenido de este interfaz grafica
     */
    JPanel jpLayout;

    /**
     * El caja de texto que nos permite ver la salida del programa
     */
    JTextArea jtaMuestra;

    /**
     * El objeto que nos facilita navegacion de la caja de texto arriba
     */
    JScrollPane jspMuestra;

    /**
     * El buton para mostrar la salida
     */
    JButton jbMostrar;

    /**
     * Un campo que nos dice donde va a guardar el archivo
     */
    JTextField jtfArchivo;

    /**
     * Un buton para seleccionar el lugar donde queremos guardar el archivo
     */
    JButton jbGuardarComo;

    /**
     * Un buton para cancelar
     */
    JButton jbCancelar;

    /**
     * Un buton para guardar en el lugar y archivo seleccionado
     */
    JButton jbGuardar;

    /**
     * El variable que guarda los datos para guardar o mostrar despues
     */
    private final String DATA;

    /**
     * El constructor de esta clase
     * @param data los datos de salida
     */
    public DataEnCrypto_GUI_ASC(String data) {
        super("DataEnCrypto v" + Cargador.getVersion() + " (GUI de Configuracion Avanzada de Salida)");
        this.DATA = data;
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(600, 200);

        jpLayout = new JPanel();
        jpLayout.setLayout(null);
        this.setContentPane(jpLayout);

        jtaMuestra = new JTextArea();
        jtaMuestra.setBounds(0, 0, 400, 60);
        jtaMuestra.setEditable(false);

        jspMuestra = new JScrollPane(jtaMuestra);
        jspMuestra.setBounds(170, 10, 420, 80);
        jspMuestra.setPreferredSize(new Dimension(420, 80));
        jspMuestra.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jspMuestra.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspMuestra.setEnabled(true);

        jpLayout.add(jspMuestra);

        jbMostrar = new JButton("Mostrar");
        jbMostrar.setBounds(10, 10, 150, 20);
        jbMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jtaMuestra.setText(DATA);
            }
        });
        jpLayout.add(jbMostrar);

        jtfArchivo = new JTextField();
        jtfArchivo.setBounds(10, 100, 420, 20);
        jpLayout.add(jtfArchivo);

        jbGuardarComo = new JButton("Guardar Como");
        jbGuardarComo.setBounds(440, 100, 150, 20);
        jbGuardarComo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfcSelector = new JFileChooser(jtfArchivo.getText());
                jtfArchivo.setText("");
                jfcSelector.setDialogTitle("Guardar");
                jfcSelector.setApproveButtonText("Guardar");
                int op = jfcSelector.showSaveDialog(null);
                if (op == JFileChooser.APPROVE_OPTION){
                    jtfArchivo.setText(jfcSelector.getSelectedFile().getAbsolutePath());
                }
            }
        });
        jpLayout.add(jbGuardarComo);

        jbCancelar = new JButton("Cancelar");
        jbCancelar.setBounds(10, 130, 100, 20);
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        jpLayout.add(jbCancelar);

        jbGuardar = new JButton("Guardar");
        jbGuardar.setBounds(490, 130, 100, 20);
        jbGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File arch = new File(jtfArchivo.getText());
                try {
                    PrintWriter ae = new PrintWriter(arch);
                    ae.print(DATA);
                    ae.flush();
                    ae.close();
                } catch (FileNotFoundException fnfe) {
                    jtaMuestra.setText("Archivo " + arch.getAbsolutePath() + " no fue encontrado.");
                }
            }
        });
        jpLayout.add(jbGuardar);

        this.setVisible(true);
    }

}
