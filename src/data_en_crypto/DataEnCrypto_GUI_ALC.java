package data_en_crypto;

import javax.swing.*;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.text.StringContent;
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
public class DataEnCrypto_GUI_ALC extends JFrame implements ItemListener, ActionListener{
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
     * Archivo para guardar la configuracion de entrada
     */
    File l_cfg;

    /**
     * Constructor de este clase. Aqui es donde se dibuja todos los
     * elementos encontrado en la pantalla.
     */
    public DataEnCrypto_GUI_ALC() {
        super("DataEnCrypto (GUI de Configuracion Avanzada de Llave)");
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

                            //debugging [start]
                            System.out.println("f = " + f + " c = " + c);
                            //debugging [end]

                            sMatriz += dtmMatriz.getValueAt(f, c);
                            if (f == fil - 1 && c == col - 1){
                                Object[] nueva = new Object[col];
                                dtmMatriz.addRow(nueva);
                                sMatriz += dtmMatriz.getValueAt(f, c);
                                dtmMatriz.removeRow(fil);
                            }

                            //debugging [start]
                            System.out.println(sMatriz);
                            //debugging [end]

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
        jbMostrar.addActionListener(this);
        jpLayout.add(jbMostrar);

        jbAbrir = new JButton("Abrir");
        jbAbrir.setBounds(510, 100, 80, 20);
        jbAbrir.setEnabled(false);
        jbAbrir.setForeground(Color.DARK_GRAY);
        jbAbrir.addActionListener(this);
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
//        if(!(new File("l_cfg.tmp").exists())){
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
//        }

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
                DataEnCrypto_GUI_Avanzada.entrada_config = null;
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
                            System.out.println("f = " + f + " c = " + c);
                            sMatriz += dtmMatriz.getValueAt(f, c);
                            if (f == fil - 1 && c == col - 1){
                                Object[] nueva = new Object[col];
                                dtmMatriz.addRow(nueva);
                                sMatriz += dtmMatriz.getValueAt(f, c);
                                dtmMatriz.removeRow(fil);
                            }
                            System.out.println(sMatriz);
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
                                    byte /*num_f = 4,*/ num_c = 4;

                                    //debugging [start]
                                    System.out.println("Tipo = 4");
                                    //debugging [end]

                                    String[] filas = valores[4].split("><");

                                    //debugging [start]
                                    System.out.println("Filas:");
                                    for(String fil : filas){
                                        System.out.println(fil);
                                    }
                                    //debugging [end]

                                    for (int f = 0; f < filas.length; f++) {
                                        String[] fila = filas[f].split(";");

                                        //debugging [start]
                                        System.out.println("Fila " + f + ":");
                                        for(String s : fila){
                                            System.out.print(s + " ");
                                        }
                                        System.out.println();
                                        //debugging [end]

                                        String[] fpa = new String[num_c];
                                        fpa[0] = Integer.toString(f + 1);
                                        int c = 1;
                                        for(String col : fila){
                                            fpa[c] = col;
                                            c++;
                                        }

                                        //debugging [start]
                                        System.out.println("Fila Final:");
                                        for(String cpa : fpa){
                                            System.out.print(cpa + " ");
                                        }
                                        //debugging [end]

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
                jpfClave.setEnabled(false);
                jtMatriz.setEnabled(false);
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
                jtMatriz.setEnabled(false);
            } else if (indice == 3) {
                //jtaTexto.setEnabled(false);
                jtaTexto.setEditable(false);
                //jtaTexto.setBackground(Color.LIGHT_GRAY);
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
                //jtaTexto.setEnabled(false);
                jtaTexto.setEditable(false);
                //jtaTexto.setBackground(Color.LIGHT_GRAY);
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
                jpfClave.setEnabled(false);
                jtMatriz.setEnabled(false);
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
