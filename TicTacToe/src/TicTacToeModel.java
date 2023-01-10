import java.util.ArrayList;
import java.util.List;

public class TicTacToeModel {


    public static final int SIZE = 3;
    public static final boolean X = true;
    public static final boolean O = false;
    private List<TicTacToeView> views;

    public enum Status {X_WON, O_WON, TIE, UNDECIDED};

    private char[][] grid;
    private boolean turn;
    private Status status;


    public TicTacToeModel() {
        grid = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = ' ';
            }
        }
        turn = X;
        status = Status.UNDECIDED;
        views=new ArrayList<>();

    }
    public void addTicTacToeView(TicTacToeView v){
        views.add(v);
    }

    private void changeTurn() {
        turn = !turn;
    }

    public Status getStatus() {return status;}

    private void updateStatus(int x, int y) {
        int numPlayedSquares=0, countRow = 0, countCol = 0, countLDiag = 0, countRDiag = 0;
        char currentPlayer = turn? 'X' : 'O';
        
        for(int i=0; i<SIZE;i++) {
            if(grid[x][i] == currentPlayer)
                countRow++;
            if(grid[i][y] == currentPlayer)
                countCol++;
            if(grid[i][i] == currentPlayer)
                countRDiag++;
            if(grid[grid.length-1-i][i] == currentPlayer)
                countLDiag++;
        }

        if(countCol==SIZE || countRow==SIZE  || countLDiag == SIZE || countRDiag == SIZE)
            if(currentPlayer=='X')
                status=status.X_WON;
            else
                status=status.O_WON;

        for(int i=0; i<SIZE; i++) {
            for(int j=0; j<SIZE; j++) {
                if(grid[i][j] != ' ') numPlayedSquares++;
            }
        }
        if(numPlayedSquares == (3*SIZE)) {
            status=status.TIE;
        }

    }

    public boolean getTurn() {return turn;}

    public void play(int x, int y) {
        if (grid[x][y] != ' ') return;
        grid[x][y] = turn? 'X' : 'O'; //
        updateStatus(x, y);

        for(TicTacToeView v : views){
            v.update(new TicTacToeEvent(this , x, y,grid,turn,status));
        }
        changeTurn();
    }
}

