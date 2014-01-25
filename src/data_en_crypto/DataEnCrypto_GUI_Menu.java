package data_en_crypto;

import javax.swing.*;

/**
 * Created by nyx on 1/25/14 at 5:36 PM.
 */
public class DataEnCrypto_GUI_Menu extends JFrame {

    JPanel jpLayout;

    ImageIcon iiIcono;

    JLabel jlIcono;

    JLabel jlTitulo;

    JButton jbInterfazBasica;

    JButton jbInterfazAvanzada;

    JButton jbSalir;

    public DataEnCrypto_GUI_Menu(){
        super("DataEnCrypto");
        this.setSize(500, 400);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jpLayout = new JPanel(null);
        this.setContentPane(jpLayout);

        iiIcono = new ImageIcon("data_en_crypto/imgs/Icon_Medium.png");
        jlIcono = new JLabel(iiIcono);
        jlIcono.setBounds(10, 10, 363, 363);
        jpLayout.add(jlIcono);

        jlTitulo = new JLabel("Menu Principal");
        jlTitulo.setBounds(383, 10, 107, 20);
        jpLayout.add(jlTitulo);

        jbInterfazBasica = new JButton("Interfaz Basica");
        jbInterfazBasica.setBounds(383, 40, 107, 20);
        jpLayout.add(jbInterfazBasica);

        jbInterfazAvanzada = new JButton("Interfaz Avanzada");
        jbInterfazAvanzada.setBounds(383, 70, 107, 20);
        jpLayout.add(jbInterfazAvanzada);

        jbSalir = new JButton("Salir");
        jbSalir.setBounds(383, 100, 107, 20);
        jpLayout.add(jbSalir);

        this.setVisible(true);
    }

}
