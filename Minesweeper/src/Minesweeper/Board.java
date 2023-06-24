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
    
    private Box[][] matrixBoxesButtons;
        
    private Box boxClass;

    // private String tag;

    private Random rnd = new Random();

    private int counter, row, column;

    public Board(int matrixSize, int amountMines){
        matrixBoxesButtons = new Box[matrixSize][matrixSize];
        counter = 0;

        for(int i = 0; i < matrixSize; i++){
            for(int j = 0; j < matrixSize; j++){
                // tag = Integer.toString(i) + ", " + Integer.toString(j);
                // adds boxClasses in the matrix
                boxClass = new Box();
                matrixBoxesButtons[i][j] = boxClass;
            }
        }

        while (counter < amountMines){
            row = rnd.nextInt(matrixSize);
            column = rnd.nextInt(matrixSize);
            if (!matrixBoxesButtons[row][column].getMine())
            {
                matrixBoxesButtons[row][column].changeMine(true);
                counter++;
            }
        }
    }

    public Box[][] getMatrixBoxesButtons() {
        return matrixBoxesButtons;
    }
}
