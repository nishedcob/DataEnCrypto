package data_en_crypto;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    DataEnCrypto_GUI_Basico dec_gui_b;

    DataEnCrypto_GUI_Avanzada dec_gui_a;

    public DataEnCrypto_GUI_Menu(){
        super("DataEnCrypto");
        this.setSize(225, 280);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jpLayout = new JPanel(null);
        this.setContentPane(jpLayout);

//        setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon_Small.png")));
//
//        iiIcono = new ImageIcon("data_en_crypto/imgs/Icon_Medium.png");
        jlIcono = new JLabel();
        jlIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data_en_crypto/imgs/Icon_Small.png")));
        jlIcono.setBounds(50, 10, 73, 73);
        jpLayout.add(jlIcono);

        jlTitulo = new JLabel("Menu Principal");
        jlTitulo.setBounds(50, 83, 107, 20);
        jpLayout.add(jlTitulo);

        jbInterfazBasica = new JButton("Interfaz Basica");
        jbInterfazBasica.setBounds(17, 123, 180, 20);
        jbInterfazBasica.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dec_gui_b = new DataEnCrypto_GUI_Basico();
            }
        });
        jpLayout.add(jbInterfazBasica);

        jbInterfazAvanzada = new JButton("Interfaz Avanzada");
        jbInterfazAvanzada.setBounds(17, 163, 180, 20);
        jbInterfazAvanzada.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dec_gui_a = new DataEnCrypto_GUI_Avanzada();
            }
        });
        jpLayout.add(jbInterfazAvanzada);

        jbSalir = new JButton("Salir");
        jbSalir.setBounds(17, 203, 180, 20);
        jbSalir.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//                setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
//                dispose();
                System.exit(0);
            }
        });
        jpLayout.add(jbSalir);

        this.setVisible(true);
    }

}
