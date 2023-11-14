/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Minesweeper;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;

/**
 *
 * @author jerson
 */
public class Box extends JButton{

    private boolean mine;
    private int minesAround;
    
    private int[] position;

    public Box(){
        this.mine = false;
        this.minesAround = 0;
        
        this.position = new int[2];

        this.setBackground(Color.white);
        this.setBorderPainted(false);
    }
    
    public void changeMine(boolean value) {
        this.mine = value;
    }
    
    // getters
    public boolean getMine() {
        return mine;
    }
    
    public int getMinesAround(){
        return minesAround;
    }
    
    public int[] getPosition(){
        return position;
    }
    
    // setters
    public void setMinesAround(int mines) {
        this.minesAround = mines;
    }
    
    public void setPosition(int row, int column) {
        this.position[0] = row;
        this.position[1] = column;
    }
}