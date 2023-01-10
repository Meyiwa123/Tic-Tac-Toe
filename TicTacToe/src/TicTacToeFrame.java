import javax.swing.*;
import java.awt.*;

public class TicTacToeFrame extends JFrame implements TicTacToeView {
    private JButton[][] buttons;

    public TicTacToeFrame(){
        super("tic tac toe");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new GridLayout(TicTacToeModel.SIZE, TicTacToeModel.SIZE));

        TicTacToeModel model=new TicTacToeModel();

        buttons=new JButton[TicTacToeModel.SIZE][TicTacToeModel.SIZE];
        TicTacToeController ttc =new TicTacToeController(model);
        model.addTicTacToeView(this);//

        for(int i = 0; i< TicTacToeModel.SIZE; i++){
            for(int j = 0; j< TicTacToeModel.SIZE; j++){
                JButton b=new JButton(" ");
                b.setActionCommand(i + " "+ j);
                buttons[i][j]=b;
                b.addActionListener(ttc);
                this.add(b);
            }
        }
        this.setSize(300,300);
        this.setVisible(true);
    }


    @Override
    public void update(TicTacToeEvent event) {
        String label= event.isTurn()? "X":"O";
        buttons[event.getX()][event.getY()].setText(label);

        if(event.getStatus()!= TicTacToeModel.Status.UNDECIDED) {
            if(event.getStatus() == TicTacToeModel.Status.X_WON) {
                JOptionPane.showMessageDialog(null, "X won!");
            }
            if(event.getStatus() == TicTacToeModel.Status.O_WON) {
                JOptionPane.showMessageDialog(null, "O won!");
            }
            if(event.getStatus() == TicTacToeModel.Status.TIE) {
                JOptionPane.showMessageDialog(null, "The game ended in a tie.");
            }
        }
    }

    public static void main(String[] args) {
        new TicTacToeFrame();
    }
}
