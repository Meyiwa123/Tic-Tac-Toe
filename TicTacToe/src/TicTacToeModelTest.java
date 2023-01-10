import org.junit.Before;

import static org.junit.Assert.*;

public class TicTacToeModelTest {
    TicTacToeModel ttt;

    @Before
    public void setUp() throws Exception {
        ttt = new TicTacToeModel();
    }

    @org.junit.Test
    public void getStatus() {
        assertEquals(TicTacToeModel.Status.UNDECIDED, ttt.getStatus());

        ttt.play(0,0);
        ttt.play(1,0);
        ttt.play(0,1);
        ttt.play(1,1);
        ttt.play(0,2);
        assertEquals(TicTacToeModel.Status.X_WON, ttt.getStatus());
    }

    @org.junit.Test
    public void getTurn() {
        assertEquals( TicTacToeModel.X, ttt.getTurn());
    }
}