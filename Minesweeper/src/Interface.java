import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Interface{
    private JFrame window;

    private JPanel boardPanel;
    // private JPanel panelsJPanel;

    private Board board;

    private JButton clickedButton;

    public Interface(){
        window = new JFrame("Minesweeper");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);

        addComponents();

        window.pack();
        window.setVisible(true);
    }

    private void addComponents(){
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(16, 16));

        board = new Board();

        for(Box[] array: board.matrixBoxesButtons){
            for(Box element: array){
                // dimensions
                (element.boxButton).setPreferredSize(new Dimension(40, 40));

                // add each box to the board panel
                boardPanel.add(element.boxButton);

                // ActionListeners
                (element.boxButton).addActionListener(new ActionListener() {
                    
                    public void actionPerformed(ActionEvent e){
                        clickedButton = (JButton) e.getSource();
                        clickedButton.setForeground(Color.RED);
                        clickedButton.setBackground(Color.LIGHT_GRAY);
                        clickedButton.setEnabled(false);
                        if (element.mine){
                            clickedButton.setText("BOMB");
                        }
                    }
                    
                });
            }
        }

        window.add(boardPanel, BorderLayout.CENTER);
    }
}
