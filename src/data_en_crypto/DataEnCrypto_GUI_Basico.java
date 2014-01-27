
package data_en_crypto;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author nyx
 */
public class DataEnCrypto_GUI_Basico extends javax.swing.JFrame {

    JPanel jpDiseno;

    JLabel jlTitulo;

    JLabel jlEntrada, jlSalida, jlLlave;

    JScrollPane jspEntrada;

    JTextArea jtaEntrada;

    JScrollPane jspSalida;

    JTextArea jtaSalida;

    public DataEnCrypto_GUI_Basico() {
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setSize(600, 500);

        jpDiseno = new JPanel(null);
        this.setContentPane(jpDiseno);

        jlTitulo = new JLabel("Bienvenidos a DataEnCrypto");
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

        this.setVisible(true);
    }
}
