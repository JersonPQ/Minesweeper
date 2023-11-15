/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Minesweeper;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author jerson
 */
public class AppMinesweeper extends javax.swing.JFrame {

    /**
     * Creates new form AppMinesweeper
     */
    
    private boolean gameOver;

    private Board board;
    private Box[][] matrixBoxs;
    
    private int matrixSize;
    private int amountMines;
    
    private ArrayList<int[]> neighborsList = new ArrayList<>();
    private ArrayList<int[]> exploredNeighbors = new ArrayList<>();
    private ArrayList<int[]> unlockableBoxs = new ArrayList<>();
    
    private void neighborsNoMinesAround(int row, int column) {
        int matrixLength = matrixBoxs.length;
        // checks up
        if (row > 0 && linearSearch(row - 1, column, this.neighborsList) == -1 && linearSearch(row - 1, column, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row - 1][column].getMine() && this.matrixBoxs[row - 1][column].getMinesAround() == 0 && !this.matrixBoxs[row - 1][column].getFlag())
            this.neighborsList.add(new int[]{row - 1, column});
        // checks down
        if (row < matrixLength - 1 && linearSearch(row + 1, column, this.neighborsList) == -1 && linearSearch(row + 1, column, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row + 1][column].getMine() && this.matrixBoxs[row + 1][column].getMinesAround() == 0 && !this.matrixBoxs[row + 1][column].getFlag())
            this.neighborsList.add(new int[]{row + 1, column});
        // checks left
        if (column > 0 && linearSearch(row, column - 1, this.neighborsList) == -1 && linearSearch(row, column - 1, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row][column - 1].getMine() && this.matrixBoxs[row][column - 1].getMinesAround() == 0 && !this.matrixBoxs[row][column - 1].getFlag())
            this.neighborsList.add(new int[]{row, column - 1});
        // checks right
        if (column < matrixLength - 1 && linearSearch(row, column + 1, this.neighborsList) == -1 && linearSearch(row, column + 1, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row][column + 1].getMine() && this.matrixBoxs[row][column + 1].getMinesAround() == 0 && !this.matrixBoxs[row][column + 1].getFlag())
            this.neighborsList.add(new int[]{row, column + 1});
    }
    
    private void neighborsMinesAround(int row, int column){
        int matrixLength = matrixBoxs.length;
        // checks up
        if (row > 0 && linearSearch(row - 1, column, this.unlockableBoxs) == -1 && linearSearch(row - 1, column, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row - 1][column].getMine() && !this.matrixBoxs[row - 1][column].getFlag())
            this.unlockableBoxs.add(new int[]{row - 1, column});
        // checks down
        if (row < matrixLength - 1 && linearSearch(row + 1, column, this.unlockableBoxs) == -1 && linearSearch(row + 1, column, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row + 1][column].getMine() && !this.matrixBoxs[row + 1][column].getFlag())
            this.unlockableBoxs.add(new int[]{row + 1, column});
        // checks left
        if (column > 0 && linearSearch(row, column - 1, this.unlockableBoxs) == -1 && linearSearch(row, column - 1, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row][column - 1].getMine() && !this.matrixBoxs[row][column - 1].getFlag())
            this.unlockableBoxs.add(new int[]{row, column - 1});
        // checks right
        if (column < matrixLength - 1 && linearSearch(row, column + 1, this.unlockableBoxs) == -1 && linearSearch(row, column + 1, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row][column + 1].getMine() && !this.matrixBoxs[row][column + 1].getFlag())
            this.unlockableBoxs.add(new int[]{row, column + 1});
        // checks diagonal up-left
        if (row > 0 && column > 0 && linearSearch(row - 1, column - 1, this.unlockableBoxs) == -1 && linearSearch(row - 1, column - 1, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row - 1][column - 1].getMine() && !this.matrixBoxs[row - 1][column - 1].getFlag())
            this.unlockableBoxs.add(new int[]{row - 1, column - 1});
        // checks diagonal down-right
        if (row < matrixLength - 1 && column < matrixLength - 1 && linearSearch(row + 1, column + 1, this.unlockableBoxs) == -1 && linearSearch(row + 1, column + 1, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row + 1][column + 1].getMine() && !this.matrixBoxs[row + 1][column + 1].getFlag())
            this.unlockableBoxs.add(new int[]{row + 1, column + 1});
        // checks diagonal down-left
        if (row < matrixLength - 1 && column > 0 && linearSearch(row + 1, column - 1, this.unlockableBoxs) == -1 && linearSearch(row + 1, column - 1, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row + 1][column - 1].getMine() && !this.matrixBoxs[row + 1][column - 1].getFlag())
            this.unlockableBoxs.add(new int[]{row + 1, column - 1});
        // checks diagonal up-right
        if (row > 0 && column < matrixLength - 1 && linearSearch(row - 1, column + 1, this.unlockableBoxs) == -1 && linearSearch(row - 1, column + 1, this.exploredNeighbors) == -1 &&
                !this.matrixBoxs[row - 1][column + 1].getMine() && !this.matrixBoxs[row - 1][column + 1].getFlag())
            this.unlockableBoxs.add(new int[]{row - 1, column + 1});
    }
    
    // iterates over all the neighbors that have 0 mines around
    private void findNeighbors(int row, int column){
        // start finding neighbors to unlock the boxes that don't have mines around
        while (!this.neighborsList.isEmpty()) {   
            int[] posEvaluate = neighborsList.get(0);
            neighborsNoMinesAround(posEvaluate[0], posEvaluate[1]);
            
            this.exploredNeighbors.add(new int[]{posEvaluate[0], posEvaluate[1]});
            this.unlockableBoxs.add(new int[]{posEvaluate[0], posEvaluate[1]});
            
            this.neighborsList.remove(linearSearch(posEvaluate[0], posEvaluate[1], neighborsList));
        }
        
        this.neighborsList.clear();
        this.exploredNeighbors.clear();
        
        this.neighborsList = (ArrayList<int[]>) unlockableBoxs.clone();
        
        // starts iterating to unlock the boxes that have mines around
        for (int[] pos : neighborsList) {
            neighborsMinesAround(pos[0], pos[1]);
            exploredNeighbors.add(new int[]{pos[0], pos[1]});
        }
        
        this.neighborsList.clear();
        this.exploredNeighbors.clear();
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
        this.matrixBoxs = board.getMatrixBoxesButtons();
        
        this.panelMatrix.removeAll();
        this.panelMatrix.setLayout(new GridLayout(matrixSize, matrixSize));
        for (int row = 0; row < matrixSize; row++) {
            for (int column = 0; column < matrixSize; column++) {
                Box box = matrixBoxs[row][column];
                box.setPreferredSize(new Dimension(preferredSizeBox, preferredSizeBox));
                
                box.addMouseListener(new MouseListener() {
                    
                    public void mouseClicked(MouseEvent e){
                        if (!gameOver) {
                            if (SwingUtilities.isRightMouseButton(e) && e.getClickCount() == 1 && box.isEnabled()) {
                                if (!box.getFlag()) {
                                    box.setFlag(true);
                                    ImageIcon flagImage = new ImageIcon("src/images/red-flag.png");

                                    // resize image
                                    Image image = flagImage.getImage();
                                    Image newImage = image.getScaledInstance(14, 14, Image.SCALE_SMOOTH);
                                    flagImage = new ImageIcon(newImage);

                                    // set image on button
                                    box.setIcon(flagImage);
                                } else {
                                    box.setFlag(false);
                                    box.setIcon(null);
                                }
                            }

                            if (SwingUtilities.isLeftMouseButton(e) && e.getClickCount() == 1 && !box.getFlag()) {
                                
                                // conditional if there's mine
                                if (box.getMine()) {
                                    box.setBackground(new Color(224, 109, 98, 88));
                                    gameOver = true;
                                    ImageIcon bombImage = new ImageIcon("src/images/bomb.png");
                                    
                                    // resize image
                                    Image image = bombImage.getImage();
                                    Image newImage = image.getScaledInstance(14, 14, Image.SCALE_SMOOTH);
                                    bombImage = new ImageIcon(newImage);
                                    
                                    // set image on button
                                    box.setIcon(bombImage);
                                    JOptionPane.showMessageDialog(null, "Oh no! You've lost!");
                                } else {
                                    if (box.getMinesAround() == 0){
                                        box.setText("");
                                        int[] posBox = box.getPosition();
                                        neighborsList.add(box.getPosition());
                                        
                                        findNeighbors(posBox[0], posBox[1]);
                                        
                                        for (int i = 0; i < unlockableBoxs.size(); i++) {
                                            int[] posDisable = unlockableBoxs.get(i);
                                            matrixBoxs[posDisable[0]][posDisable[1]].setEnabled(false);
                                            int minesAround = matrixBoxs[posDisable[0]][posDisable[1]].getMinesAround();
                                            if (minesAround == 0) {
                                                matrixBoxs[posDisable[0]][posDisable[1]].setText("");
                                            } else {
                                                matrixBoxs[posDisable[0]][posDisable[1]].setText("" + minesAround);
                                            }
                                        }
                                        
                                        unlockableBoxs.clear();
                                    }
                                    else
                                        box.setText("" + box.getMinesAround());
                                        
                                    box.setEnabled(false);
                                }
                            }
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {
                        
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        
                    }
                });

                box.addActionListener(new ActionListener() {
                    
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        
                    }
                });
                
                this.panelMatrix.add(box);
            }
        }
    }
    
    private void resetMarix() {
        this.gameOver = false;
        this.board = new Board(matrixSize, amountMines);
        this.panelMatrix.removeAll();
        this.panelMatrix.revalidate();
        this.panelMatrix.repaint();
        setMatrix();
    }
    
    public AppMinesweeper() {
        this.gameOver = false;
        this.matrixSize = 16;
        this.amountMines = 50;
        this.board = new Board(matrixSize, amountMines);
        
        initComponents();

        this.setResizable(false);

        this.setTitle("Minesweeper - Jerson Prendas Quirós");

        this.panelGeneralGame.setBackground(Color.white);
        this.panelTitle.setBackground(Color.white);
        this.panelMatrix.setBackground(Color.white);
        this.resetPanel.setBackground(Color.white);

        this.resetButton.setFocusable(false);
        this.resetButton.setBackground(Color.white);
        
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
        resetPanel = new javax.swing.JPanel();
        resetButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setSize(new java.awt.Dimension(700, 700));

        panelTitle.setForeground(new java.awt.Color(0, 0, 0));

        labelTitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
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
            .addGap(0, 688, Short.MAX_VALUE)
        );
        panelMatrixLayout.setVerticalGroup(
            panelMatrixLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 626, Short.MAX_VALUE)
        );

        resetButton.setText("Reset");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout resetPanelLayout = new javax.swing.GroupLayout(resetPanel);
        resetPanel.setLayout(resetPanelLayout);
        resetPanelLayout.setHorizontalGroup(
            resetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resetPanelLayout.createSequentialGroup()
                .addGap(306, 306, 306)
                .addComponent(resetButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        resetPanelLayout.setVerticalGroup(
            resetPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resetPanelLayout.createSequentialGroup()
                .addGap(0, 12, Short.MAX_VALUE)
                .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout panelGeneralGameLayout = new javax.swing.GroupLayout(panelGeneralGame);
        panelGeneralGame.setLayout(panelGeneralGameLayout);
        panelGeneralGameLayout.setHorizontalGroup(
            panelGeneralGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralGameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelGeneralGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelMatrix, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(panelTitle, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(resetPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        panelGeneralGameLayout.setVerticalGroup(
            panelGeneralGameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelGeneralGameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panelMatrix, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resetPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(panelGeneralGame, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelGeneralGame, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
       resetMarix();
    }//GEN-LAST:event_resetButtonActionPerformed

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
    private javax.swing.JButton resetButton;
    private javax.swing.JPanel resetPanel;
    // End of variables declaration//GEN-END:variables
}
