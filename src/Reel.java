/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.Dimension;
import java.awt.Image;
import java.util.*;
import javax.swing.*;

/**
 *
 * @author w1597188
 */
public class Reel {

    private Symbol Imageone;
    private Symbol Imagetwo;
    private Symbol Imagethree;
    private Symbol Imagefour;
    private Symbol Imagefive;
    private Symbol Imagesix;
    public ArrayList<Symbol> symbol;

    public Reel() {

        // the below variables will be used to represnt each image     
        Imageone = null;
        Imagetwo = null;
        Imagethree = null;
        Imagefour = null;
        Imagefive = null;
        Imagesix = null;

        // this arraylist will be used to store information of each 
        symbol = new ArrayList<>();

        // adding images to the arraylist
        try {
            Imageone = new Symbol(1, new ImageIcon(getClass().getResource("/Images/bell.png")));
        } catch (NullPointerException e) {
            System.out.println("Image1 are not added properly");
        }
        try {
            Imagetwo = new Symbol(2, new ImageIcon(getClass().getResource("/Images/cherry.png")));
        } catch (NullPointerException e) {
            System.out.println("Image1 are not added properly");
        }
        try {
            Imagethree = new Symbol(3, new ImageIcon(getClass().getResource("/Images/lemon.png")));
        } catch (NullPointerException e) {
            System.out.println("Image1 are not added properly");
        }
        try {
            Imagefour = new Symbol(4, new ImageIcon(getClass().getResource("/Images/plum.png")));
        } catch (NullPointerException e) {
            System.out.println("Image1 are not added properly");
        }
        try {
            Imagefive = new Symbol(5, new ImageIcon(getClass().getResource("/Images/redseven.png")));
        } catch (NullPointerException e) {
            System.out.println("Image1 are not added properly");
        }
        try {
            Imagesix = new Symbol(6, new ImageIcon(getClass().getResource("/Images/watermelon.png")));
        } catch (NullPointerException e) {
            System.out.println("Image1 are not added properly");
        }

        // adding all possible reel faces to the arraylist
        symbol.add(Imageone);
        symbol.add(Imagetwo);
        symbol.add(Imagethree);
        symbol.add(Imagefour);
        symbol.add(Imagefive);
        symbol.add(Imagesix);

    }

    public Symbol spin() {
        // retrieve a random number
        Random rand = new Random();
        int randomNum;
        randomNum = rand.nextInt(symbol.size());

        return symbol.get(randomNum);
    }

}
