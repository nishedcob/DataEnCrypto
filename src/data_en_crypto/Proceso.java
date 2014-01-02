/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package data_en_crypto;

/**
 *
 * @author nyx
 */
public class Proceso {
    protected boolean root;
    protected static boolean s_root;

    public boolean isRoot() {
        return root;
    }

    public void setRoot(boolean root) {
        this.root = root;
    }

    public static boolean isS_root() {
        return s_root;
    }

    public static void setS_root(boolean s_root) {
        Proceso.s_root = s_root;
    }
}
