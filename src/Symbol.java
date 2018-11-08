
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
public class Symbol implements Isymbol {

    private int value;
    private ImageIcon ImageDescription;

    public Symbol() {
        value = 0;
        ImageDescription = null;
    }

    public Symbol(int value, ImageIcon ImageDescription) {
        this.value = value;
        this.ImageDescription = ImageDescription;
    }

    public ImageIcon getImageDescription() {
        return ImageDescription;
    }

    public int getValue() {
        return value;
    }

    public void setImageDescription(ImageIcon ImageDescription) {
        this.ImageDescription = ImageDescription;
    }

    public void setValue(int value) {
        this.value = value;
    }

}
