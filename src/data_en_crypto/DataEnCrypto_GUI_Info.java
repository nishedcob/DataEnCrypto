package data_en_crypto;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by nyx at 10:19 PM on 05 February 2014.
 */
public class DataEnCrypto_GUI_Info extends JFrame {

    JPanel jpLayout;

    JLabel jlIcono;

    JLabel jlTitulo;

    JLabel jlVersion;

    /**
     * Constructs a new frame that is initially invisible.
     * <p/>
     * This constructor sets the component's locale property to the value
     * returned by <code>JComponent.getDefaultLocale</code>.
     *
     * @throws java.awt.HeadlessException if GraphicsEnvironment.isHeadless()
     *                                    returns true.
     * @see java.awt.GraphicsEnvironment#isHeadless
     * @see java.awt.Component#setSize
     * @see java.awt.Component#setVisible
     * @see javax.swing.JComponent#getDefaultLocale
     */
    public DataEnCrypto_GUI_Info() throws HeadlessException {
        super("DataEnCrypto v" + Cargador.getVersion() + " ALPHA Informacion");
        try {
            this.setIconImage(ImageIO.read(new File("/data_en_crypto/imgs/Icon_64x64.png")));
        } catch (IOException e) {
        }
        this.setSize(225, 180);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);

        jpLayout = new JPanel(null);
        this.setContentPane(jpLayout);

        jlIcono = new JLabel();
        jlIcono.setIcon(new javax.swing.ImageIcon(getClass().getResource("/data_en_crypto/imgs/Icon_Small.png")));
        jlIcono.setBounds(70, 10, 73, 73);
        jpLayout.add(jlIcono);

        jlTitulo = new JLabel("DataEnCrypto");
        jlTitulo.setBounds(50, 83, 107, 20);
        jpLayout.add(jlTitulo);

        jlVersion = new JLabel("v" + Cargador.getVersion());
        jlVersion.setBounds(30, 108, 127, 20);
        jpLayout.add(jlVersion);

        this.setVisible(true);
    }
}
