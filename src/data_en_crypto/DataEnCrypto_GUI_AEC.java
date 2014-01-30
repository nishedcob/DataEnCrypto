package data_en_crypto;

import sun.print.resources.serviceui_zh_TW;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Interfaz grafica para configurar el flujo de entrada.
 * Created by nyx on 1/20/14 at 11:04 AM.
 */
public class DataEnCrypto_GUI_AEC extends JFrame implements ItemListener, ActionListener {

    /**
     * El fondo de toda la pantalla.
     */
    JPanel jpLayout;

    /**
     * Buton para eliminar configuracion
     */
    JButton jbEliminar;

    /**
     * Buton para mostrar el contenido de un archivo selecionado.
     */
    JButton jbMostrar;
    /**
     * Buton para abrir un archivo.
     */
    JButton jbAbrir;
    /**
     * Buton para cancelar (volver a la ultima pantalla sin guardar esta configuracion)
     */
    JButton jbCancelar;
    /**
     * Buton para guardar (guardar este configuracion y vuelve a la patalla anterior)
     */
    JButton jbGuardar;

    /**
     * Una label para ayudar el usuario entender el combo box
     */
    JLabel jlTipo;
    /**
     * Un combo box para pedir el usario que tipo de flujo de entrada quiere
     */
    JComboBox<String> jcbTipo;

    /**
     * Una pantalla para dejar el usuario teclar algo o para mostrar el contenido de un archivo.
     */
    JTextArea jtaTexto;

    /**
     * Un objeto para hacer la pantalla de texto mas navegable
     */
    JScrollPane jspTexto;

    /**
     * Un lugar para guardar y mostrar la dirrecion absoluta y completa del ultimo archivo selecionado
     */
    JTextField jtfArchivo;

    /**
     * el ultimo indice selecionado del combo box
     */
    int indice = 0;

    /**
     * Archivo para guardar la configuracion de entrada
     */
    File e_cfg;

    /**
     * Constructor de este clase. Aqui es donde se dibuja todos los
     * elementos encontrado en la pantalla.
     */
    public DataEnCrypto_GUI_AEC() {
        super("DataEnCrypto v" + Cargador.getVersion() + " (GUI de Configuracion Avanzada de Entrada)");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(600, 200);

        jpLayout = new JPanel();
        jpLayout.setLayout(null);
        this.setContentPane(jpLayout);

        jbEliminar = new JButton("Eliminar Configuracion");
        jbEliminar.setBounds(10, 40, 150, 20);
        jbEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                jcbTipo.setSelectedIndex(0);
                jtaTexto.setText("");
                jtfArchivo.setText("");
                e_cfg = new File("e_cfg.tmp");
                try {
                    PrintWriter escri = new PrintWriter(e_cfg);
                    escri.printf("%d,%s,%s", jcbTipo.getSelectedIndex(),
                            (jtaTexto.getText() == null ? "\u0000" : jtaTexto.getText()),
                            (jtfArchivo.getText() == null ? "\u0000" : jtfArchivo.getText()));
                    escri.flush();
                    escri.close();
                } catch (FileNotFoundException fnfe) {
                    fnfe.printStackTrace();
                }
            }
        });
        jpLayout.add(jbEliminar);

        jbMostrar = new JButton("Mostrar Archivo");
        jbMostrar.setBounds(10, 70, 150, 20);
        jbMostrar.setEnabled(false);
        jbMostrar.setForeground(Color.DARK_GRAY);
        jbMostrar.addActionListener(this);
        jpLayout.add(jbMostrar);

        jbAbrir = new JButton("Abrir");
        jbAbrir.setBounds(510, 100, 80, 20);
        jbAbrir.setEnabled(false);
        jbAbrir.setForeground(Color.DARK_GRAY);
        jbAbrir.addActionListener(this);
        jpLayout.add(jbAbrir);

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
        jbGuardar.setEnabled(false);
        jbGuardar.setForeground(Color.DARK_GRAY);
        jbGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                e_cfg = new File("e_cfg.tmp");
                try {
                    PrintWriter escri = new PrintWriter(e_cfg);
                    escri.printf("%d,%s,%s", jcbTipo.getSelectedIndex(),
                            (jtaTexto.getText() == null ? "\u0000" : jtaTexto.getText()),
                            (jtfArchivo.getText() == null ? "\u0000" : jtfArchivo.getText()));
                    escri.flush();
                    escri.close();
                } catch (FileNotFoundException fnfe) {
                    fnfe.printStackTrace();
                }
                setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        jpLayout.add(jbGuardar);

        jlTipo = new JLabel("Tipo:");
        jlTipo.setBounds(10, 10, 50, 20);
        jpLayout.add(jlTipo);

        String[] cosas = {"", "Texto", "Archivo"};
        jcbTipo = new JComboBox<String>();
        for(String s : cosas){
            jcbTipo.addItem(s);
        }
        jcbTipo.setSelectedIndex(indice);
        jcbTipo.setBounds(70, 10, 90, 20);
        jcbTipo.addItemListener(this);
        jpLayout.add(jcbTipo);

        jtaTexto = new JTextArea();
        jtaTexto.setBounds(0, 0, 400, 60);
        //jtaTexto.setEnabled(false);
        jtaTexto.setEditable(false);
        //jtaTexto.setBackground(Color.LIGHT_GRAY);

        jspTexto = new JScrollPane(jtaTexto);
        jspTexto.setBounds(170, 10, 420, 80);
        jspTexto.setPreferredSize(new Dimension(420, 80));
        jspTexto.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        jspTexto.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspTexto.setEnabled(true);

        jpLayout.add(jspTexto);

        jtfArchivo = new JTextField();
        jtfArchivo.setBounds(10, 100, 490, 20);
        jtfArchivo.setEnabled(false);
        jtfArchivo.setBackground(Color.LIGHT_GRAY);
        jpLayout.add(jtfArchivo);

        if(new File("e_cfg.tmp").exists()){
            e_cfg = new File("e_cfg.tmp");
            try {
                Scanner lec = new Scanner(e_cfg);
                if(lec.hasNextLine()){
                    String datos = "";
                    while(lec.hasNextLine()){
                        datos += lec.nextLine();
                        if(lec.hasNextLine()){
                            datos += '\n';
                        }
                    }
                    String[] valores = datos.split(",");
                    if(valores.length > 0 && valores.length <= 3){
                        int tipo;
                        try {
                            tipo = Integer.parseInt(valores[0]);
                            if(tipo >= 0 && tipo <= 2){
                                jcbTipo.setSelectedIndex(tipo);
                                if(tipo == 1){
                                    try {
                                        jtaTexto.setText(valores[1]);
                                    } catch (Exception e) {
                                    }
                                } else if (tipo == 2) {
                                    try {
                                        jtfArchivo.setText(valores[2]);
                                    } catch (Exception e) {
                                    }
                                }
                            }
                        } catch (NumberFormatException e) {
                            //el archivo no es escrito de una forma correcta
                        }
                    }
                }
            } catch (FileNotFoundException e) {
                //error impossible
            }
        }

        this.setVisible(true);
    }

    /**
     * Metodo principal de esta clase, solo se debe usar para probar este clase
     * @param args argumentos con que se ejecuta la clase
     */
    public static void main(String[] args) {
        new DataEnCrypto_GUI_AEC();
    }

    /**
     * Lanzado cuando un elemento ha sido selecionado o deselecionado
     * por el usario. El codigo en este metodo es lo que ejecuta cuando
     * un usuario seleciona o deseleciona un elemento.
     *
     * @param e Evento que lanza la accion
     */
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (this.jcbTipo.getSelectedIndex() != indice){
            this.indice = jcbTipo.getSelectedIndex();
            if (indice == 1){
                //jtaTexto.setEnabled(true);
                jtaTexto.setEditable(true);
                //jtaTexto.setBackground(Color.WHITE);
                jbGuardar.setEnabled(true);
                jbGuardar.setForeground(Color.BLACK);
                jbAbrir.setEnabled(false);
                jbAbrir.setForeground(Color.DARK_GRAY);
                jbMostrar.setEnabled(false);
                jbMostrar.setForeground(Color.DARK_GRAY);
                jtfArchivo.setEnabled(false);
                jtfArchivo.setBackground(Color.LIGHT_GRAY);
            } else if (indice == 2) {
                //jtaTexto.setEnabled(false);
                jtaTexto.setEditable(false);
                //jtaTexto.setBackground(Color.LIGHT_GRAY);
                jbGuardar.setEnabled(true);
                jbGuardar.setForeground(Color.BLACK);
                jbAbrir.setEnabled(true);
                jbAbrir.setForeground(Color.BLACK);
                jbMostrar.setEnabled(true);
                jbMostrar.setForeground(Color.BLACK);
                jtfArchivo.setEnabled(true);
                jtfArchivo.setBackground(Color.WHITE);
            } else if (indice == 0) {
                //jtaTexto.setEnabled(false);
                jtaTexto.setEditable(false);
                //jtaTexto.setBackground(Color.LIGHT_GRAY);
                jbGuardar.setEnabled(false);
                jbGuardar.setForeground(Color.DARK_GRAY);
                jbAbrir.setEnabled(false);
                jbAbrir.setForeground(Color.DARK_GRAY);
                jbMostrar.setEnabled(false);
                jbMostrar.setForeground(Color.DARK_GRAY);
                jtfArchivo.setEnabled(false);
                jtfArchivo.setBackground(Color.LIGHT_GRAY);
            }
        }
    }

    /**
     * Lanzada cuando una accion ocure (por ejemplo cuando hagan click en un buton)
     *
     * @param e Evento que lanza la accion
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource().equals(jbAbrir)){
            JFileChooser jfcAbrir = new JFileChooser(jtfArchivo.getText());
            jfcAbrir.setDialogTitle("Abrir");
            jfcAbrir.setApproveButtonText("Ok");
            int op = jfcAbrir.showOpenDialog(null);
            if (op == JFileChooser.APPROVE_OPTION){
                jtfArchivo.setText(jfcAbrir.getSelectedFile().getAbsolutePath());
            }
        } else if (e.getSource().equals(jbMostrar)) {
            File f = new File(this.jtfArchivo.getText());
            Scanner sc = null;
            try {
                sc = new Scanner(f);
                jtaTexto.setEnabled(false);
                jtaTexto.setText("");
                while (sc.hasNextLine()){
                    jtaTexto.setText(jtaTexto.getText() + sc.nextLine() + "\n");
                }
            } catch (FileNotFoundException fnfe) {
                System.out.println("No se encontro el archivo!");
            } finally {
                if(sc != null){
                    sc.close();
                }
            }

        }
    }
}
