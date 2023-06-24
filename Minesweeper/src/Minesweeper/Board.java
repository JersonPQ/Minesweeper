/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Minesweeper;

import java.util.Random;

/**
 *
 * @author jerson
 */
public class Board {
    
    private Box[][] matrixBoxs;
        
    private Box boxClass;

    // private String tag;

    private Random rnd = new Random();

    private int matrixSize;
    private int counterMines, row, column;
    private int minesAround;

    public Board(int matrixSizePartr, int amountMines){
        matrixSize = matrixSizePartr;
        matrixBoxs = new Box[matrixSize][matrixSize];
        counterMines = 0;

        for(int row = 0; row < matrixSize; row++){
            for(int column = 0; column < matrixSize; column++){
                // adds boxClasses in the matrix
                boxClass = new Box();
                boxClass.setPosition(row, column);
                matrixBoxs[row][column] = boxClass;
            }
        }

        while (counterMines < amountMines){
            row = rnd.nextInt(matrixSize);
            column = rnd.nextInt(matrixSize);
            if (!matrixBoxs[row][column].getMine())
            {
                matrixBoxs[row][column].changeMine(true);
                counterMines++;
            }
        }
        
        // sets amount of mines around each box
        for(int row = 0; row < matrixSize; row++){
            for(int column = 0; column < matrixSize; column++){
                minesAround = 0;
                searchMines(row, column);
                matrixBoxs[row][column].setMinesAround(minesAround);
            }
        }
    }

    private void searchMines(int positionRowBox, int positionColumnBox){
        // looks for mines around the box
        for (int column = positionColumnBox - 1; column <= positionColumnBox + 1; column++) {
            // above the box
            try {
                if (!(column < 0 || column >= matrixSize) && matrixBoxs[positionRowBox - 1][column].getMine()) {
                    minesAround++;
                }
            } catch (IndexOutOfBoundsException e) {
            }
            
            // under the box
            try {
                if (!(column < 0 || column >= matrixSize) && matrixBoxs[positionRowBox + 1][column].getMine()) {
                    minesAround++;
                }
            } catch (IndexOutOfBoundsException e) {
            }
        }
        
        // right to the box
        try {
            if (matrixBoxs[positionRowBox][positionColumnBox + 1].getMine())
                minesAround++;
        } catch (IndexOutOfBoundsException e) {
        }
        
        // left to the box
        try {
            if (matrixBoxs[positionRowBox][positionColumnBox - 1].getMine())
                minesAround++;
        } catch (IndexOutOfBoundsException e) {
        }
    }
    
    // getters
    public Box[][] getMatrixBoxesButtons() {
        return matrixBoxs;
    }
}
