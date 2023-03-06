public class Board {
    
    public Box[][] matrixBoxes;
    
    private Box boxClass;

    public Board(){
        matrixBoxes = new Box[16][16];

        for(int i = 0; i < 16; i++){
            for(int j = 0; j < 16; j++){
                boxClass = new Box();
                matrixBoxes[i][j] = boxClass;
            }
        }
    }

}
