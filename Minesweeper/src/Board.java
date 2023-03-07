import java.util.Random;

public class Board {
    
    public Box[][] matrixBoxesButtons;
        
    private Box boxClass;

    // private String tag;

    private Random rnd = new Random();

    private int counter, row, column;

    private boolean existMine;

    private int[][] arrMines;

    public Board(){
        matrixBoxesButtons = new Box[16][16];
        arrMines = new int[25][2];
        counter = 0;

        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                // tag = Integer.toString(i) + ", " + Integer.toString(j);
                // adds boxClasses in the matrix
                boxClass = new Box();
                matrixBoxesButtons[i][j] = boxClass;
            }
        }

        while (counter < 25){
            row = rnd.nextInt(16);
            column = rnd.nextInt(16);
            existMine = false;
            for (int[] coords: arrMines){
                if (coords[0] == row && coords[1] == column){
                    existMine = true;
                }
            }

            if (!existMine){
                matrixBoxesButtons[row][column].mine = true;
                arrMines[counter][0] = row;
                arrMines[counter][1] = column;
                counter++;
            }
        }
    }

}
