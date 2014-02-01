package data_en_crypto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
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
 * Created by nyx on 1/23/14 at 11:43 AM.
 */
public class DataEnCrypto_GUI_ALC extends JFrame {
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
     * Un combo box para pedir el usario que tipo de flujo de llave quiere
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

    JLabel jlMatriz;

    JScrollPane jspMatriz;

    JTable jtMatriz;

    JLabel jlClave;

    JPasswordField jpfClave;

    /**
     * el ultimo indice selecionado del combo box
     */
    int indice = 0;

    /**
     * Archivo para guardar la configuracion de llave
     */
    File l_cfg;

    /**
     * Constructor de este clase. Aqui es donde se dibuja todos los
     * elementos encontrado en la pantalla.
     */
    public DataEnCrypto_GUI_ALC() {
        super("DataEnCrypto v" + Cargador.getVersion() + " ALPHA (GUI de Configuracion Avanzada de Llave)");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.setSize(600, 400);

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
                jpfClave.setText("");
                DefaultTableModel dtmMatriz2 = (DefaultTableModel)jtMatriz.getModel();
                int num_filas = 0;
                while (dtmMatriz2.getRowCount() != 0){
                    dtmMatriz2.removeRow(0);
                    num_filas++;
                }
                for (int f = 0; f < num_filas; f++) {
                    Object[] columnas = new Object[dtmMatriz2.getColumnCount()];
                    for (int c = 0; c < columnas.length; c++) {
                        if (c == 0){
                            columnas[c] = Integer.toString(f + 1);
                        } else {
                            columnas[c] = "";
                        }
                    }
                    dtmMatriz2.addRow(columnas);
                }
                jtMatriz = new JTable(dtmMatriz2);
                l_cfg = new File("l_cfg.tmp");
                try {
                    PrintWriter escri = new PrintWriter(l_cfg);
                    String clave = "";
                    if(jpfClave.getPassword() != null || jpfClave.getPassword().length != 0){
                        for(char c : jpfClave.getPassword()){
                            clave += c;
                        }
                    } else {
                        clave = "\u0000";
                    }
                    DefaultTableModel dtmMatriz = (DefaultTableModel) jtMatriz.getModel();
                    int fil = dtmMatriz.getRowCount();
                    int col = dtmMatriz.getColumnCount();
                    Object[][] datos = new String[fil][col];
                    String sMatriz = "";
                    for (int f = 0; f < fil; f++) {
                        for (int c = 1; c < col; c++) {
                            datos[f][c] = dtmMatriz.getValueAt(f, c);

                            sMatriz += dtmMatriz.getValueAt(f, c);
                            if (f == fil - 1 && c == col - 1){
                                Object[] nueva = new Object[col];
                                dtmMatriz.addRow(nueva);
                                sMatriz += dtmMatriz.getValueAt(f, c);
                                dtmMatriz.removeRow(fil);
                            }

                            if(c != col-1){
                                sMatriz += ";";
                            } else {
                                if(f != fil-1){
                                    sMatriz += "><";
                                }
                            }
                        }
                    }
                    if(sMatriz.charAt(sMatriz.length() - 1) == ';'){
                        sMatriz += dtmMatriz.getValueAt(fil-1, col-1);
                    }
                    escri.printf("%d,%s,%s,%s,%s", jcbTipo.getSelectedIndex(),
                            (jtaTexto.getText() == null ? "\u0000" : jtaTexto.getText()),
                            (jtfArchivo.getText() == null ? "\u0000" : jtfArchivo.getText()),
                            clave,sMatriz);
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
        jbMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                File f = new File(jtfArchivo.getText());
                Scanner sc = null;
                try {
                    sc = new Scanner(f);
                    jtaTexto.setEnabled(false);
                    jtaTexto.setText("");
                    while (sc.hasNextLine()){
                        jtaTexto.setText(jtaTexto.getText() + sc.nextLine() + "\n");
                    }
                } catch (FileNotFoundException fnfe) {
                    //System.out.println("No se encontro el archivo!");
                } finally {
                    if(sc != null){
                        sc.close();
                    }
                }
            }
        });
        jpLayout.add(jbMostrar);

        jbAbrir = new JButton("Abrir");
        jbAbrir.setBounds(510, 100, 80, 20);
        jbAbrir.setEnabled(false);
        jbAbrir.setForeground(Color.DARK_GRAY);
        jbAbrir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser jfcAbrir = new JFileChooser(jtfArchivo.getText());
                jfcAbrir.setDialogTitle("Abrir");
                jfcAbrir.setApproveButtonText("Ok");
                int op = jfcAbrir.showOpenDialog(null);
                if (op == JFileChooser.APPROVE_OPTION){
                    jtfArchivo.setText(jfcAbrir.getSelectedFile().getAbsolutePath());
                }
            }
        });
        jpLayout.add(jbAbrir);

        jlClave = new JLabel("Clave:");
        jlClave.setBounds(10, 130, 50, 20);
        jpLayout.add(jlClave);

        jpfClave = new JPasswordField();
        jpfClave.setBounds(70, 130, 90, 20);
        jpfClave.setEnabled(false);
        jpLayout.add(jpfClave);

        DefaultTableModel dtmMatriz = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0;
            }
        };
        byte num_fila = 4;
        byte num_colum = 4;
        for(byte c = 0; c < num_colum; c++){
            if(c == 0) {
                dtmMatriz.addColumn("");
            } else {
                dtmMatriz.addColumn(c);
            }
        }
        for(byte f = 1; f < num_fila; f++){
            String[] fila = new String[num_colum];
            for(byte c = 0; c < num_colum; c++){
                if(c == 0){
                    fila[c] = Byte.toString(f);
                } else {
                    fila[c] = "";
                }
            }
            dtmMatriz.addRow(fila);
        }

        jtMatriz = new JTable(dtmMatriz);
        jtMatriz.setBounds(0, 0, 210, 140);
        jtMatriz.setEnabled(false);

        jbCancelar = new JButton("Cancelar");
        jbCancelar.setBounds(10, 330, 100, 20);
        jbCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                dispose();
            }
        });
        jpLayout.add(jbCancelar);

        jbGuardar = new JButton("Guardar");
        jbGuardar.setBounds(490, 330, 100, 20);
        jbGuardar.setEnabled(false);
        jbGuardar.setForeground(Color.DARK_GRAY);
        jbGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                l_cfg = new File("l_cfg.tmp");
                try {
                    PrintWriter escri = new PrintWriter(l_cfg);
                    String clave = "";
                    if(jpfClave.getPassword() != null || jpfClave.getPassword().length != 0){
                        for(char c : jpfClave.getPassword()){
                            clave += c;
                        }
                    } else {
                        clave = "\u0000";
                    }
                    DefaultTableModel dtmMatriz = (DefaultTableModel) jtMatriz.getModel();
                    int fil = dtmMatriz.getRowCount();
                    int col = dtmMatriz.getColumnCount();
                    Object[][] datos = new String[fil][col];
                    String sMatriz = "";
                    for (int f = 0; f < fil; f++) {
                        for (int c = 1; c < col; c++) {
                            datos[f][c] = dtmMatriz.getValueAt(f, c);
                            //System.out.println("f = " + f + " c = " + c);
                            sMatriz += dtmMatriz.getValueAt(f, c);
                            //System.out.println(sMatriz);
                            if(c != col-1){
                                sMatriz += ";";
                            } else {
                                if(f != fil-1){
                                    sMatriz += "><";
                                }
                            }
                        }
                    }
                    escri.printf("%d,%s,%s,%s,%s", jcbTipo.getSelectedIndex(),
                            (jtaTexto.getText() == null ? "\u0000" : jtaTexto.getText()),
                            (jtfArchivo.getText() == null ? "\u0000" : jtfArchivo.getText()),
                            clave,sMatriz);
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

        String[] cosas = {"", "Texto", "Archivo", "Clave", "Matriz"};
        jcbTipo = new JComboBox<String>();
        for(String s : cosas){
            jcbTipo.addItem(s);
        }
        jcbTipo.setSelectedIndex(indice);
        jcbTipo.setBounds(70, 10, 90, 20);
        jcbTipo.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (jcbTipo.getSelectedIndex() != indice){
                    indice = jcbTipo.getSelectedIndex();
                    //El Usuario Seleciono:
                    if (indice == 1){
                        //Texto
                        jtaTexto.setEditable(true);
                        jbGuardar.setEnabled(true);
                        jbGuardar.setForeground(Color.BLACK);
                        jbAbrir.setEnabled(false);
                        jbAbrir.setForeground(Color.DARK_GRAY);
                        jbMostrar.setEnabled(false);
                        jbMostrar.setForeground(Color.DARK_GRAY);
                        jtfArchivo.setEnabled(false);
                        jtfArchivo.setBackground(Color.LIGHT_GRAY);
                        jpfClave.setEnabled(false);
                        jtMatriz.setEnabled(false);
                    } else if (indice == 2) {
                        //Archivo
                        jtaTexto.setEditable(false);
                        jbGuardar.setEnabled(true);
                        jbGuardar.setForeground(Color.BLACK);
                        jbAbrir.setEnabled(true);
                        jbAbrir.setForeground(Color.BLACK);
                        jbMostrar.setEnabled(true);
                        jbMostrar.setForeground(Color.BLACK);
                        jtfArchivo.setEnabled(true);
                        jtfArchivo.setBackground(Color.WHITE);
                        jtMatriz.setEnabled(false);
                    } else if (indice == 3) {
                        //Clave
                        jtaTexto.setEditable(false);
                        jbGuardar.setEnabled(true);
                        jbGuardar.setForeground(Color.BLACK);
                        jbAbrir.setEnabled(false);
                        jbAbrir.setForeground(Color.DARK_GRAY);
                        jbMostrar.setEnabled(false);
                        jbMostrar.setForeground(Color.DARK_GRAY);
                        jtfArchivo.setEnabled(false);
                        jtfArchivo.setBackground(Color.LIGHT_GRAY);
                        jpfClave.setEnabled(true);
                        jtMatriz.setEnabled(false);
                    } else if (indice == 4) {
                        //Matriz
                        jtaTexto.setEditable(false);
                        jbGuardar.setEnabled(true);
                        jbGuardar.setForeground(Color.BLACK);
                        jbAbrir.setEnabled(false);
                        jbAbrir.setForeground(Color.DARK_GRAY);
                        jbMostrar.setEnabled(false);
                        jbMostrar.setForeground(Color.DARK_GRAY);
                        jtfArchivo.setEnabled(false);
                        jtfArchivo.setBackground(Color.LIGHT_GRAY);
                        jpfClave.setEnabled(false);
                        jtMatriz.setEnabled(true);
                    } else if (indice == 0) {
                        //Ninguna Opcion
                        jtaTexto.setEditable(false);
                        jbGuardar.setEnabled(false);
                        jbGuardar.setForeground(Color.DARK_GRAY);
                        jbAbrir.setEnabled(false);
                        jbAbrir.setForeground(Color.DARK_GRAY);
                        jbMostrar.setEnabled(false);
                        jbMostrar.setForeground(Color.DARK_GRAY);
                        jtfArchivo.setEnabled(false);
                        jtfArchivo.setBackground(Color.LIGHT_GRAY);
                        jpfClave.setEnabled(false);
                        jtMatriz.setEnabled(false);
                    }
                }
            }
        });
        jpLayout.add(jcbTipo);

        jtaTexto = new JTextArea();
        jtaTexto.setBounds(0, 0, 400, 60);
        jtaTexto.setEditable(false);

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

        jlMatriz = new JLabel("Matriz:");
        jlMatriz.setBounds(170, 130, 60, 20);
        jpLayout.add(jlMatriz);

        if(new File("l_cfg.tmp").exists()){
            l_cfg = new File("l_cfg.tmp");
            try {
                Scanner lec = new Scanner(l_cfg);
                if(lec.hasNextLine()){
                    String datos = "";
                    while(lec.hasNextLine()){
                        datos += lec.nextLine();
                        if(lec.hasNextLine()){
                            datos += '\n';
                        }
                    }
                    String[] valores = datos.split(",");
                    if(valores.length == 5){
                        int tipo;
                        try {
                            tipo = Integer.parseInt(valores[0]);
                            if(tipo >= 0 && tipo <= 4){
                                jcbTipo.setSelectedIndex(tipo);
                                if(tipo == 1){
                                    jtaTexto.setText(valores[1]);
                                } else if (tipo == 2) {
                                    jtfArchivo.setText(valores[2]);
                                } else if (tipo == 3) {
                                    jpfClave.setText(valores[3]);
                                } else if (tipo == 4) {
                                    byte num_c = 4;

                                    String[] filas = valores[4].split("><");

                                    for (int f = 0; f < filas.length; f++) {
                                        String[] fila = filas[f].split(";");

                                        String[] fpa = new String[num_c];
                                        fpa[0] = Integer.toString(f + 1);
                                        int c = 1;
                                        for(String col : fila){
                                            fpa[c] = col;
                                            c++;
                                        }

                                        dtmMatriz.removeRow(0);
                                        dtmMatriz.addRow(fpa);
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

        jtMatriz = new JTable(dtmMatriz);
        jtMatriz.setBounds(0, 0, 210, 140);
        jtMatriz.setEnabled(false);

        jspMatriz = new JScrollPane(jtMatriz);
        jspMatriz.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        jspMatriz.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        jspMatriz.setBounds(170, 150, 400, 160);

        jpLayout.add(jspMatriz);

        this.setVisible(true);
    }

    /**
     * Metodo principal de esta clase, solo se debe usar para probar este clase
     * @param args argumentos con que se ejecuta la clase
     */
    public static void main(String[] args) {
        new DataEnCrypto_GUI_ALC();
    }

}
