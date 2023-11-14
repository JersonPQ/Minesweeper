/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author jerson
 */
public class AppMinesweeper extends javax.swing.JFrame {

    /**
     * Creates new form AppMinesweeper
     */
    
    private Board board;
    private Box[][] matrixBoxs;
    
    private int counterMines;
    
    private int matrixSize;
    private int amountMines;
    
    private ArrayList<int[]> neighborsList = new ArrayList<>();
    private ArrayList<int[]> exploredNeighbors = new ArrayList<>();
    
    private void neighbors(int row, int column) {
        int matrixLength = matrixBoxs.length;
        if (row > 0 && linearSearch(row - 1, column, neighborsList) == -1 && linearSearch(row - 1, column, exploredNeighbors) == -1 &&
                !matrixBoxs[row - 1][column].getMine() && matrixBoxs[row - 1][column].getMinesAround() == 0)
            neighborsList.add(new int[]{row - 1, column});
        if (row < matrixLength - 1 && linearSearch(row + 1, column, neighborsList) == -1 && linearSearch(row + 1, column, exploredNeighbors) == -1 &&
                !matrixBoxs[row + 1][column].getMine() && matrixBoxs[row + 1][column].getMinesAround() == 0)
            neighborsList.add(new int[]{row + 1, column});
        if (column > 0 && linearSearch(row, column - 1, neighborsList) == -1 && linearSearch(row, column - 1, exploredNeighbors) == -1 &&
                !matrixBoxs[row][column - 1].getMine() && matrixBoxs[row][column - 1].getMinesAround() == 0)
            neighborsList.add(new int[]{row, column - 1});
        if (column < matrixLength - 1 && linearSearch(row, column + 1, neighborsList) == -1 && linearSearch(row, column + 1, exploredNeighbors) == -1 &&
                !matrixBoxs[row][column + 1].getMine() && matrixBoxs[row][column + 1].getMinesAround() == 0)
            neighborsList.add(new int[]{row, column + 1});
    }
    
    // iterates over all the neighbors that have 0 mines around
    private void findNeighbors(int row, int column){
        while (!neighborsList.isEmpty()) {   
            int[] posEvaluate = neighborsList.get(0);
            neighbors(posEvaluate[0], posEvaluate[1]);
            exploredNeighbors.add(new int[]{posEvaluate[0], posEvaluate[1]});
            
            neighborsList.remove(linearSearch(posEvaluate[0], posEvaluate[1], neighborsList));
        }
        
        neighborsList.clear();
    }
    
    private int linearSearch(int row, int column, ArrayList<int[]> arraySearch){
        for (int i = 0; i < arraySearch.size(); i++) {
            int[] tmpPosEvaluate = arraySearch.get(i);
            if (tmpPosEvaluate[0] == row && tmpPosEvaluate[1] == column)
                return i;
        }
        
        return -1;
    }
    
    private void setMatrix(){
        int preferredSizeBox = (40 * matrixSize) / 16;
        matrixBoxs = board.getMatrixBoxesButtons();
        
        panelMatrix.removeAll();
        panelMatrix.setLayout(new GridLayout(matrixSize, matrixSize));
        for (int row = 0; row < matrixSize; row++) {
            for (int column = 0; column < matrixSize; column++) {
                Box box = matrixBoxs[row][column];
                box.setPreferredSize(new Dimension(preferredSizeBox, preferredSizeBox));
                
                box.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // conditional if there's mine
                        if (box.getMine()) {
                            box.setBackground(Color.red);
//                            ImageIcon bombImage = new ImageIcon("src/images/bomb.png");
//                            box.setIcon(bombImage);
                            JOptionPane.showMessageDialog(null, "Oh no! You've lost!");
                        } else {
                            if (box.getMinesAround() == 0){
                                box.setText("");
                                int[] posBox = box.getPosition();
                                neighborsList.add(box.getPosition());
                                
                                findNeighbors(posBox[0], posBox[1]);
                                
                                for (int i = 0; i < exploredNeighbors.size(); i++) {
                                    int[] posDisable = exploredNeighbors.get(i);
                                    matrixBoxs[posDisable[0]][posDisable[1]].setEnabled(false);
                                    matrixBoxs[posDisable[0]][posDisable[1]].setText("");
                                }
                                
                                exploredNeighbors.clear();
                            }
                            else
                                box.setText("" + box.getMinesAround());
                                
                            box.setEnabled(false);
                        }
                    }
                });
                
                panelMatrix.add(box);
            }
        }
    }
    
    public AppMinesweeper() {
        matrixSize = 16;
        amountMines = 35;
        counterMines = 0;
        board = new Board(matrixSize, amountMines);
        
        initComponents();
        
        setMatrix();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelGeneralGame = new javax.swing.JPanel();
        panelTitle = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        panelMatrix = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(700, 700));

        panelTitle.setForeground(new java.awt.Color(0, 0, 0));

        labelTitle.setFont(new java.awt.Font("Liberation Sans", 0, 24)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(0, 0, 0));
        labelTitle.setText("MINESWEEPER");

        javax.swing.GroupLayout panelTitleLayout = new javax.swing.GroupLayout(panelTitle);
        panelTitle.setLayout(panelTitleLayout);
        panelTitleLayout.setHorizontalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addGap(254, 254, 254)
                .addComponent(labelTitle)
                .addContainerGap(256, Short.MAX_VALUE))
        );
        panelTitleLayout.setVerticalGroup(
            panelTitleLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelTitleLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout panelMatrixLayout = new javax.swing.GroupLayout(panelMatrix);
        panelMatrix.setLayout(panelMatrixLayout);
        panelMatrixLayout.setHorizontalGroup(
            panelMatrixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelMatrixLayout.setVerticalGroup(
            panelMatrixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 642, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout panelGeneralGameLayout = new javax.swing.GroupLayout(panelGeneralGame);
        panelGeneralGame.setLayout(panelGeneralGameLayout);
        panelGeneralGameLayout.setHorizontalGroup(
            panelGeneralGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelGeneralGameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(panelMatrix, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTitle, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelGeneralGameLayout.setVerticalGroup(
            panelGeneralGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMatrix, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AppMinesweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AppMinesweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AppMinesweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AppMinesweeper.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        AppMinesweeper minesweeper = new AppMinesweeper();
        minesweeper.setVisible(true);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel labelTitle;
    private javax.swing.JPanel panelGeneralGame;
    private javax.swing.JPanel panelMatrix;
    private javax.swing.JPanel panelTitle;
    // End of variables declaration//GEN-END:variables
}
