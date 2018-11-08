/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.JOptionPane;

/**
 *
 * @author w1597188
 */
public class MainClass extends JFrame {

    // Componenets of the main menu Buttons and textfields
    JPanel Panel;
    JPanel PanelTextfields;
    JButton BtnSpin;
    JButton BtnAddCoin;
    JTextField TxtCredits;
    JLabel LblCredits;
    JButton BtnBetOne;
    JButton BtnBetMax;
    JButton BtnReset;
    JButton BtnStat;
    JLabel LblBetPlaced;
    JTextField TxtBetPlaced;

    // Images that spin and the labels they are placed on
    JPanel PanelImages;
    JLabel Imagelabel1;
    JLabel Imagelabel2;
    JLabel Imagelabel3;
    JFrame StatFrame;
    JLabel WinsStatDisplay;
    JLabel LoseStatDisplay;
    JTextField WinsStatBarDisplay;
    JTextField LoseStatBarDisplay;
    JLabel AverageDisplay;
    JProgressBar AverageBarDisplay;
    JButton BtnstatSave;
    int credits;
    int credits2;
    int winstat = 0, losestat = 0;
    int BetResult;
    double AverageNetted = 0;

    // variables of type worker that are going to make the images spin using thread
    Worker SymbolSpinner1;
    Worker SymbolSpinner2;
    Worker SymbolSpinner3;
    Reel reel1;
    Reel reel2;
    Reel reel3;

    public MainClass() {

        // intialising reels that will hold the values of each imageIcon 
        reel1 = new Reel();
        reel2 = new Reel();
        reel3 = new Reel();

        // intailising stat frame
        StatFrame = new JFrame("Statistics Display");
        StatFrame.setLayout(new GridLayout(4, 2, 2, 5));
        StatFrame.setSize(200, 300);
        WinsStatDisplay = new JLabel("Wins");
        LoseStatDisplay = new JLabel("Loses");
        AverageDisplay = new JLabel("Average Netted");
        WinsStatBarDisplay = new JTextField("WinsB");
        LoseStatBarDisplay = new JTextField("WinsL");
        AverageBarDisplay = new JProgressBar();
        BtnstatSave = new JButton("Save");
        // adding components to statistics display frame
        StatFrame.add(WinsStatDisplay);
        StatFrame.add(WinsStatBarDisplay);
        StatFrame.add(LoseStatDisplay);
        StatFrame.add(LoseStatBarDisplay);
        StatFrame.add(AverageDisplay);
        StatFrame.add(AverageBarDisplay);
        StatFrame.add(BtnstatSave);

        // adding main container that will hold everything in the frame
        Container Maincontainer = getContentPane();
        Maincontainer.setLayout(new GridLayout(2, 1));

        // intializing panel that will hold the buttons and textfields
        Panel = new JPanel();
        Panel.setLayout(new GridLayout(2, 3));
        BtnSpin = new JButton("Spin");
        BtnAddCoin = new JButton("Add Coin");
        TxtCredits = new JTextField("10");
        TxtCredits.setEditable(false);
        LblCredits = new JLabel("Number of credits");
        BtnBetOne = new JButton("Bet One");
        BtnBetMax = new JButton("Bet Maximum");
        BtnReset = new JButton("Reset");
        LblBetPlaced = new JLabel("Bets placed");
        TxtBetPlaced = new JTextField("0");
        TxtBetPlaced.setEditable(false);
        BtnStat = new JButton("Statistics");

        // adding components to panel
        Panel.add(BtnSpin);
        Panel.add(BtnAddCoin);

        Panel.add(BtnBetOne);
        Panel.add(BtnBetMax);
        Panel.add(BtnReset);
        Panel.add(BtnStat);
        Panel.add(LblCredits);
        Panel.add(TxtCredits);
        Panel.add(LblBetPlaced);
        Panel.add(TxtBetPlaced);

        // addding the top panel to the frame or container 
        Maincontainer.add(Panel);

        // adding the panel the will hold the images that are going to spin 
        PanelImages = new JPanel();
        PanelImages.setLayout(new GridLayout(1, 3, 10, 0));
        Imagelabel1 = new JLabel(new ImageIcon(getClass().getResource("/Images/lemon.png")));
        Imagelabel1.setPreferredSize(new Dimension(50, 50));
        Imagelabel2 = new JLabel(new ImageIcon(getClass().getResource("/Images/lemon.png")));
        Imagelabel2.setPreferredSize(new Dimension(200, 200));
        Imagelabel3 = new JLabel(new ImageIcon(getClass().getResource("/Images/lemon.png")));
        Imagelabel3.setPreferredSize(new Dimension(200, 200));

        // adding spinijng images to bottom panel
        PanelImages.add(Imagelabel1);
        PanelImages.add(Imagelabel2);
        PanelImages.add(Imagelabel3);

        // adding the panel to frame
        Maincontainer.add(PanelImages);

        // registering action listneres
        Thelistener BtnListener = new Thelistener();
        BtnSpin.addActionListener(BtnListener);
        BtnAddCoin.addActionListener(BtnListener);
        BtnBetOne.addActionListener(BtnListener);
        BtnBetMax.addActionListener(BtnListener);
        BtnReset.addActionListener(BtnListener);
        BtnStat.addActionListener(BtnListener);

        // directly adding the listener implementation when registering the image click listeners 
        Imagelabel1.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent ME) {
                SymbolSpinner1.cancel(true);

                SymbolSpinner2.cancel(true);

                SymbolSpinner3.cancel(true);

                compare();
            }
        });
        Imagelabel2.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent ME) {
                SymbolSpinner1.cancel(true);
                //clickevt = true;
                SymbolSpinner2.cancel(true);
                //clickevt = true;
                SymbolSpinner3.cancel(true);

                compare();
                //SymbolSpinner1.done();

            }

        });
        Imagelabel3.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent ME) {
                SymbolSpinner1.cancel(true);
                //clickevt = true;
                SymbolSpinner2.cancel(true);
                //clickevt = true;
                SymbolSpinner3.cancel(true);

                compare();
                //SymbolSpinner1.done();

            }
        });

        // adding listener and implementation to statistics save button
        BtnstatSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                Date date = new Date();
                SimpleDateFormat dateFormat = new SimpleDateFormat("HH-mm-ss yyyy-MM-dd ");
                File file = new File(dateFormat.format(date) + ".txt");
                BufferedWriter out;
                try {
                    out = new BufferedWriter(new FileWriter(file));
                    out.write(WinsStatBarDisplay.getText());
                    out.newLine();
                    out.write(LoseStatBarDisplay.getText());
                    out.newLine();
                    out.write(AverageBarDisplay.getValue());
                    String info = "Saved successfully";
                    credits2 = 0;
                    infoBoxD(info, credits2);
                    out.close();
                } catch (IOException ex) {
                    System.out.println("There is an error with file saving");
                }

            }
        });

    }

    // private inner class that thats going to handle some of the click events
    private class Thelistener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            // using if statement to choose which buttons
            if (e.getSource() == BtnAddCoin) {
                credits = Integer.parseInt((TxtCredits.getText()));
                credits++;
                TxtCredits.setText(Integer.toString(credits));

            } else if (e.getSource() == BtnBetOne) {
                if ((Integer.parseInt((TxtCredits.getText())) < 1)) {
                    String string = "Please add coins!!";
                    int k = 0;
                    infoBoxD(string, k);
                } else {
                    credits = Integer.parseInt((TxtCredits.getText()));
                    credits--;
                    TxtCredits.setText(Integer.toString(credits));
                    credits = Integer.parseInt((TxtBetPlaced.getText()));
                    credits++;
                    TxtBetPlaced.setText(Integer.toString(credits));
                }

            } else if (e.getSource() == BtnBetMax) {
                if ((Integer.parseInt((TxtCredits.getText())) < 3)) {
                    String string = "Please add coins";
                    int k = 0;
                    infoBoxD(string, k);
                } else {
                    credits = Integer.parseInt((TxtCredits.getText()));
                    credits = (credits - 3);
                    TxtCredits.setText(Integer.toString(credits));
                    credits = Integer.parseInt((TxtBetPlaced.getText()));
                    credits = (credits + 3);
                    TxtBetPlaced.setText(Integer.toString(credits));
                }
            } else if (e.getSource() == BtnReset) {
                credits = Integer.parseInt((TxtBetPlaced.getText()));
                credits2 = Integer.parseInt((TxtCredits.getText()));
                credits2 = (credits2 + credits);
                credits = 0;
                TxtCredits.setText(Integer.toString(credits2));
                TxtBetPlaced.setText(Integer.toString(credits));
            } else if (e.getSource() == BtnSpin) {
                if ((Integer.parseInt((TxtBetPlaced.getText())) < 1)) {
                    String string = "Please add credits";
                    int k = 0;
                    infoBoxD(string, k);
                } else {
                    SymbolSpinner1 = new Worker(Imagelabel1, reel1);
                    SymbolSpinner1.execute();

                    SymbolSpinner2 = new Worker(Imagelabel2, reel2);
                    SymbolSpinner2.execute();

                    SymbolSpinner3 = new Worker(Imagelabel3, reel3);
                    SymbolSpinner3.execute();
                }
            } else if (e.getSource() == BtnStat) {

                WinsStatBarDisplay.setText(Integer.toString(winstat));
                LoseStatBarDisplay.setText(Integer.toString(losestat));

                AverageBarDisplay.setValue((int) AverageNetted);

                AverageBarDisplay.setStringPainted(true);

                StatFrame.setVisible(true);

            }

        }
    }

    // this method will compare and display result if you hae won or not
    private void compare() {

        if (SymbolSpinner1.finalval == SymbolSpinner2.finalval) {
            credits = Integer.parseInt((TxtBetPlaced.getText()));
            BetResult = credits * SymbolSpinner1.finalval;
            credits2 = Integer.parseInt((TxtCredits.getText()));
            credits2 = credits2 + BetResult;
            TxtCredits.setText(Integer.toString(credits2));
            String result = "You win!!";
            infoBox(result, credits2);
            winstat++;
            AverageNetted = ((AverageNetted * (winstat + losestat)) + credits) / (winstat + losestat);

        } else if (SymbolSpinner1.finalval == SymbolSpinner3.finalval) {
            credits = Integer.parseInt((TxtBetPlaced.getText()));
            BetResult = credits * SymbolSpinner1.finalval;
            credits2 = Integer.parseInt((TxtCredits.getText()));
            credits2 = credits2 + BetResult;
            TxtCredits.setText(Integer.toString(credits2));
            String result = "You win!!";
            infoBox(result, credits2);
            winstat++;
            AverageNetted = ((AverageNetted * (winstat + losestat)) + credits) / (winstat + losestat);
        } else if (SymbolSpinner2.finalval == SymbolSpinner3.finalval) {
            credits = Integer.parseInt((TxtBetPlaced.getText()));
            BetResult = credits * SymbolSpinner1.finalval;
            credits2 = Integer.parseInt((TxtCredits.getText()));
            credits2 = credits2 + BetResult;
            TxtCredits.setText(Integer.toString(credits2));
            String result = "You win!!";
            infoBox(result, credits2);
            winstat++;
            AverageNetted = ((AverageNetted * (winstat + losestat)) + credits) / (winstat + losestat);
        } else if ((SymbolSpinner1.finalval == SymbolSpinner2.finalval) && (SymbolSpinner1.finalval == SymbolSpinner3.finalval)) {
            credits = Integer.parseInt((TxtBetPlaced.getText()));
            BetResult = credits * SymbolSpinner1.finalval;
            credits2 = Integer.parseInt((TxtCredits.getText()));
            credits2 = credits2 + BetResult;
            TxtCredits.setText(Integer.toString(credits2));
            String result = "You win!!";
            infoBox(result, credits2);
            winstat++;
            AverageNetted = ((AverageNetted * (winstat + losestat)) + credits) / (winstat + losestat);
        } else {
            credits = 0;
            TxtBetPlaced.setText(Integer.toString(credits));
            String result = "You Lose";
            infoBox(result, credits);
            losestat++;
            AverageNetted = ((AverageNetted * (winstat + losestat)) + credits) / (winstat + losestat);
        }

    }

    // this method will be used to display pop uo messages to the user 
    public static void infoBox(String infoMessage, int Res) {
        JOptionPane.showMessageDialog(null, infoMessage, "Giving you of credits: " + Res, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void infoBoxD(String infoMessage, int Res) {
        JOptionPane.showMessageDialog(null, infoMessage, "Ignore this: " + Res, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        MainClass frame = new MainClass();
        frame.setTitle("Slot Machine");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 500);
        frame.setVisible(true);
    }
}
