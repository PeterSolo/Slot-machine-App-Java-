
import javax.swing.ImageIcon;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author w1597188
 */
public interface Isymbol {

    public abstract void setImageDescription(ImageIcon ImageDescription);

    public abstract ImageIcon getImageDescription();

    public abstract void setValue(int value);

    public abstract int getValue();

}
