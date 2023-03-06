import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;

public class Interface {
    private JFrame window;

    private JPanel boardPanel;

    private Board board;

    public Interface(){
        window = new JFrame("Minesweeper");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponents();

        window.pack();
        window.setVisible(true);
    }

    private void addComponents(){
        boardPanel = new JPanel();

        board = new Board();
        boardPanel.setLayout(new GridLayout(16, 16));

        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                (board.matrixBoxes[i][j].box).setPreferredSize(new Dimension(40, 40));
                boardPanel.add(board.matrixBoxes[i][j].box);
            }
        }

        window.add(boardPanel, BorderLayout.CENTER);
    }

}
