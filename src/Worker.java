/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author w1597188
 */
import java.util.List;
import java.util.concurrent.ExecutionException;
import javax.swing.*;

public class Worker extends SwingWorker<Boolean, Symbol> {

    JLabel reelLabel;
    Reel reel;
    boolean clickevt = false;
    int i = 0;
    int finalval = 0;
    public Symbol lastsymbol;

    public Worker(JLabel Label, Reel r) {
        this.reelLabel = Label;
        reel = r;
    }

    @Override
    protected Boolean doInBackground() throws Exception {

        while (true) {
            lastsymbol = reel.spin();
            publish(lastsymbol);
            Thread.sleep(100);
        }

    }

    protected void process(List<Symbol> chunks) {
        // Here we receive the values that we publish().
        // They may come grouped in chunks.
        Symbol mostRecentValue = chunks.get(chunks.size() - 1);
        reelLabel.setIcon(mostRecentValue.getImageDescription());
        finalval = lastsymbol.getValue();
        //System.out.println(finalval);
    }

}
