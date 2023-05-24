import controller.GameController;
import model.Chessboard;
import view.ChessGameFrame;
import java.util.Scanner;
import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Scanner input=new Scanner(System.in);
        SwingUtilities.invokeLater(() -> {
            ChessGameFrame mainFrame = new ChessGameFrame(1100, 810);
            Chessboard board=new Chessboard();
            GameController gameController = new GameController(mainFrame.getChessboardComponent(), board);
            mainFrame.setGameController(gameController);
            board.setGameController(gameController);
            gameController.board=board;
            gameController.frame=mainFrame;
            mainFrame.setVisible(true);
        });
    }
}
