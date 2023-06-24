/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Minesweeper;

import javax.swing.JButton;

/**
 *
 * @author jerson
 */
public class Box extends JButton{

    private boolean mine;

    public Box(){
        this.mine = false;
    }
    
    
    // getters
    public boolean getMine() {
        return mine;
    }
    
    // setters
    public void changeMine(boolean value) {
        this.mine = value;
    }
}