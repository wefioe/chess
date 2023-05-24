package view;


import chess.CatChessComponent;
import chess.DogChessComponent;
import chess.ElephantChessComponent;
import chess.LeopardChessComponent;
import chess.LionChessComponent;
import chess.RatChessComponent;
import chess.TigerChessComponent;
import chess.WolfChessComponent;
import controller.GameController;
import model.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.sql.Struct;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

/**
 * This class represents the checkerboard component object on the panel
 */
public class ChessboardComponent extends JComponent {
    private final int CHESS_SIZE;
    private final Set<ChessboardPoint> riverCell = new HashSet<>();
    private final Set<ChessboardPoint> trapCell = new HashSet<>();
    private final Set<ChessboardPoint> densCell = new HashSet<>();
    //public HashMap<coordinate,String>mapcell;
    //public HashMap<coordinate,String>mapterrain;

    private GameController gameController;

    public ChessboardComponent(int chessSize) {
        CHESS_SIZE = chessSize;
        int width = CHESS_SIZE * 7;
        int height = CHESS_SIZE * 9;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK);// Allow mouse events to occur
        setLayout(null); // Use absolute layout.
        setSize(width, height);
        System.out.printf("chessboard width, height = [%d : %d], chess size = %d\n", width, height, CHESS_SIZE);

        initiateGridComponents();
    }


    /**
     * This method represents how to initiate ChessComponent
     * according to Chessboard information
     */
    public void initiateChessComponent(Chessboard chessboard) {
        Cell[][] grid = chessboard.getGrid();
        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                // TODO: Implement the initialization checkerboard

                if (grid[i][j].getPiece() != null) {
                    ChessPiece chessPiece = grid[i][j].getPiece();
                    //System.out.println(chessPiece.getOwner());
                    if(grid[i][j].getPiece().getName()=="Elephant"){
                        changshu.gridComponents[i][j].add(new ElephantChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                        changshu.mapcell[i][j]="Elephant";
                    }
                    else if(grid[i][j].getPiece().getName()=="Lion"){
                        changshu.gridComponents[i][j].add(new LionChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                        changshu.mapcell[i][j]="Lion";
                    }
                    else if(grid[i][j].getPiece().getName()=="Cat"){
                        changshu.gridComponents[i][j].add(new CatChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                        changshu.mapcell[i][j]="Cat";
                    }
                    else if(grid[i][j].getPiece().getName()=="Dog"){
                        changshu.gridComponents[i][j].add(new DogChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                        changshu.mapcell[i][j]="Dog";
                    }
                    else if(grid[i][j].getPiece().getName()=="Leopard"){
                        changshu.gridComponents[i][j].add(new LeopardChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                        changshu.mapcell[i][j]="Leopard";
                    }
                    else if(grid[i][j].getPiece().getName()=="Rat"){
                        changshu.gridComponents[i][j].add(new RatChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                        changshu.mapcell[i][j]="Rat";
                    }
                    else if(grid[i][j].getPiece().getName()=="Tiger"){
                        changshu.gridComponents[i][j].add(new TigerChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                        changshu.mapcell[i][j]="Tiger";
                    }
                    else if(grid[i][j].getPiece().getName()=="Wolf"){
                        changshu.gridComponents[i][j].add(new WolfChessComponent(chessPiece.getOwner(), CHESS_SIZE));
                        changshu.mapcell[i][j]="Wolf";
                    }
                    else System.out.printf("piece's name error\n");
                }
            }
        }

    }

    public void initiateGridComponents() {

        riverCell.add(new ChessboardPoint(3,1));
        riverCell.add(new ChessboardPoint(3,2));
        riverCell.add(new ChessboardPoint(4,1));
        riverCell.add(new ChessboardPoint(4,2));
        riverCell.add(new ChessboardPoint(5,1));
        riverCell.add(new ChessboardPoint(5,2));

        riverCell.add(new ChessboardPoint(3,4));
        riverCell.add(new ChessboardPoint(3,5));
        riverCell.add(new ChessboardPoint(4,4));
        riverCell.add(new ChessboardPoint(4,5));
        riverCell.add(new ChessboardPoint(5,4));
        riverCell.add(new ChessboardPoint(5,5));

        trapCell.add(new ChessboardPoint(0,2));
        trapCell.add(new ChessboardPoint(0,4));
        trapCell.add(new ChessboardPoint(1,3));

        trapCell.add(new ChessboardPoint(7,3));
        trapCell.add(new ChessboardPoint(8,2));
        trapCell.add(new ChessboardPoint(8,4));

        densCell.add(new ChessboardPoint(0,3));
        densCell.add(new ChessboardPoint(8,3));

        for (int i = 0; i < CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < CHESSBOARD_COL_SIZE.getNum(); j++) {
                ChessboardPoint temp = new ChessboardPoint(i, j);
                CellComponent cell;
                if (riverCell.contains(temp)) {
                    cell = new CellComponent(Color.CYAN, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                    changshu.mapterrain[i][j]="river";
                }
                else if(trapCell.contains(temp)){
                    cell = new CellComponent(Color.PINK, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                    changshu.mapterrain[i][j]="trap";
                }
                else if(densCell.contains(temp)){
                    cell = new CellComponent(Color.MAGENTA, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                    changshu.mapterrain[i][j]="dens";
                }
                else{
                    cell = new CellComponent(Color.GREEN, calculatePoint(i, j), CHESS_SIZE);
                    this.add(cell);
                    changshu.mapterrain[i][j]="grass";
                }
                changshu.gridComponents[i][j] = cell;
            }
        }
    }

    public void registerController(GameController gameController) {
        this.gameController = gameController;
    }

    public void setChessComponentAtGridElephant(ChessboardPoint point, ElephantChessComponent chess) {
        getGridComponentAt(point).add(chess);
        changshu.mapcell[point.getRow()][point.getCol()]="Elephant";
    }
    public ElephantChessComponent removeChessComponentAtGridElephant(ChessboardPoint point,int issave) {
        // Note re-validation is required after remove / removeAll.
        ElephantChessComponent chess = (ElephantChessComponent) getGridComponentAt(point).getComponents()[0];
        if(issave==1){
            changshu.stackElephant.push(chess);
            if(gameController.getCurrentPlayer()==PlayerColor.BLUE)changshu.Redchessnumber--;
            else changshu.Bluechessnumber--;
        }
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        changshu.mapcell[point.getRow()][point.getCol()]=null;
        return chess;
    }
    public void setChessComponentAtGridCat(ChessboardPoint point, CatChessComponent chess) {
        getGridComponentAt(point).add(chess);
        changshu.mapcell[point.getRow()][point.getCol()]="Cat";
    }
    public CatChessComponent removeChessComponentAtGridCat(ChessboardPoint point,int issave) {
        // Note re-validation is required after remove / removeAll.
        CatChessComponent chess = (CatChessComponent) getGridComponentAt(point).getComponents()[0];
        if(issave==1){
            changshu.stackCat.push(chess);
            if(gameController.getCurrentPlayer()==PlayerColor.BLUE)changshu.Redchessnumber--;
            else changshu.Bluechessnumber--;
        }
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        changshu.mapcell[point.getRow()][point.getCol()]=null;
        return chess;
    }

    public void setChessComponentAtGridDog(ChessboardPoint point, DogChessComponent chess) {
        getGridComponentAt(point).add(chess);
        changshu.mapcell[point.getRow()][point.getCol()]="Dog";
    }
    public DogChessComponent removeChessComponentAtGridDog(ChessboardPoint point,int issave) {
        // Note re-validation is required after remove / removeAll.
        DogChessComponent chess = (DogChessComponent) getGridComponentAt(point).getComponents()[0];
        if(issave==1){
            changshu.stackDog.push(chess);
            if(gameController.getCurrentPlayer()==PlayerColor.BLUE)changshu.Redchessnumber--;
            else changshu.Bluechessnumber--;
        }
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        changshu.mapcell[point.getRow()][point.getCol()]=null;
        return chess;
    }
    public void setChessComponentAtGridLeopard(ChessboardPoint point, LeopardChessComponent chess) {
        getGridComponentAt(point).add(chess);
        changshu.mapcell[point.getRow()][point.getCol()]="Leopard";
    }
    public LeopardChessComponent removeChessComponentAtGridLeopard(ChessboardPoint point,int issave) {
        // Note re-validation is required after remove / removeAll.
       LeopardChessComponent chess = (LeopardChessComponent) getGridComponentAt(point).getComponents()[0];
        if(issave==1){
            changshu.stackLeopard.push(chess);
            if(gameController.getCurrentPlayer()==PlayerColor.BLUE)changshu.Redchessnumber--;
            else changshu.Bluechessnumber--;
        }
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        changshu.mapcell[point.getRow()][point.getCol()]=null;
        return chess;
    }
    public void setChessComponentAtGridLion(ChessboardPoint point, LionChessComponent chess) {
        getGridComponentAt(point).add(chess);
        changshu.mapcell[point.getRow()][point.getCol()]="Lion";
    }
    public LionChessComponent removeChessComponentAtGridLion(ChessboardPoint point,int issave) {
        // Note re-validation is required after remove / removeAll.
        LionChessComponent chess = (LionChessComponent) getGridComponentAt(point).getComponents()[0];
        if(issave==1){
            changshu.stackLion.push(chess);
            if(gameController.getCurrentPlayer()==PlayerColor.BLUE)changshu.Redchessnumber--;
            else changshu.Bluechessnumber--;
        }
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        changshu.mapcell[point.getRow()][point.getCol()]=null;
        return chess;
    }
    public void setChessComponentAtGridRat(ChessboardPoint point, RatChessComponent chess) {
        getGridComponentAt(point).add(chess);
        changshu.mapcell[point.getRow()][point.getCol()]="Rat";
    }
    public RatChessComponent removeChessComponentAtGridRat(ChessboardPoint point,int issave) {
        // Note re-validation is required after remove / removeAll.
        RatChessComponent chess = (RatChessComponent) getGridComponentAt(point).getComponents()[0];
        if(issave==1) {
            changshu.stackRat.push(chess);
            if(gameController.getCurrentPlayer()==PlayerColor.BLUE)changshu.Redchessnumber--;
            else changshu.Bluechessnumber--;
        }
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        changshu.mapcell[point.getRow()][point.getCol()]=null;
        return chess;
    }
    public void setChessComponentAtGridTiger(ChessboardPoint point, TigerChessComponent chess) {
        getGridComponentAt(point).add(chess);
        changshu.mapcell[point.getRow()][point.getCol()]="Tiger";
    }
    public TigerChessComponent removeChessComponentAtGridTiger(ChessboardPoint point,int issave) {
        // Note re-validation is required after remove / removeAll.
       TigerChessComponent chess = (TigerChessComponent) getGridComponentAt(point).getComponents()[0];
        if(issave==1){
            changshu.stackTiger.push(chess);
            if(gameController.getCurrentPlayer()==PlayerColor.BLUE)changshu.Redchessnumber--;
            else changshu.Bluechessnumber--;
        }
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        changshu.mapcell[point.getRow()][point.getCol()]=null;
        return chess;
    }
    public void setChessComponentAtGridWolf(ChessboardPoint point, WolfChessComponent chess) {
        getGridComponentAt(point).add(chess);
        changshu.mapcell[point.getRow()][point.getCol()]="Wolf";
    }
    public WolfChessComponent removeChessComponentAtGridWolf(ChessboardPoint point,int issave) {
        // Note re-validation is required after remove / removeAll.
       WolfChessComponent chess = (WolfChessComponent) getGridComponentAt(point).getComponents()[0];
        if(issave==1){
            changshu.stackWolf.push(chess);
            if(gameController.getCurrentPlayer()==PlayerColor.BLUE)changshu.Redchessnumber--;
            else changshu.Bluechessnumber--;
        }
        getGridComponentAt(point).removeAll();
        getGridComponentAt(point).revalidate();
        chess.setSelected(false);
        changshu.mapcell[point.getRow()][point.getCol()]=null;
        return chess;
    }
    private CellComponent getGridComponentAt(ChessboardPoint point) {
        return changshu.gridComponents[point.getRow()][point.getCol()];
    }

    private ChessboardPoint getChessboardPoint(Point point) {
        System.out.println("[" + point.y/CHESS_SIZE +  ", " +point.x/CHESS_SIZE + "] Clicked");
        return new ChessboardPoint(point.y/CHESS_SIZE, point.x/CHESS_SIZE);
    }
    private Point calculatePoint(int row, int col) {
        return new Point(col * CHESS_SIZE, row * CHESS_SIZE);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    }

    @Override
    protected void processMouseEvent(MouseEvent e) {
        if (e.getID() == MouseEvent.MOUSE_PRESSED) {
            JComponent clickedComponent = (JComponent) getComponentAt(e.getX(), e.getY());
            if (clickedComponent.getComponentCount() == 0) {
                System.out.print("None chess here and ");
                gameController.onPlayerClickCell(getChessboardPoint(e.getPoint()), (CellComponent) clickedComponent);
            } else {
                System.out.print("One chess here and ");
                ChessboardPoint oo=getChessboardPoint(e.getPoint());
                int x=oo.getRow(),y=oo.getCol();
                System.out.printf("x=%d y=%d s=%s\n",x,y,changshu.mapcell[x][y]);
                if(changshu.mapcell[x][y]=="Elephant"){
                    gameController.onPlayerClickChessPieceElephant(oo, (ElephantChessComponent) clickedComponent.getComponents()[0]);
                }
                else if(changshu.mapcell[x][y]=="Cat"){
                    gameController.onPlayerClickChessPieceCat(oo, (CatChessComponent) clickedComponent.getComponents()[0]);
                }
                else if(changshu.mapcell[x][y]=="Dog"){
                    gameController.onPlayerClickChessPieceDog(oo, (DogChessComponent) clickedComponent.getComponents()[0]);
                }
                else if(changshu.mapcell[x][y]=="Leopard"){
                    gameController.onPlayerClickChessPieceLeopard(oo, (LeopardChessComponent) clickedComponent.getComponents()[0]);
                }
                else if(changshu.mapcell[x][y]=="Lion"){
                    gameController.onPlayerClickChessPieceLion(oo, (LionChessComponent) clickedComponent.getComponents()[0]);
                }
                else if(changshu.mapcell[x][y]=="Rat"){
                    gameController.onPlayerClickChessPieceRat(oo, (RatChessComponent) clickedComponent.getComponents()[0]);
                }
                else if(changshu.mapcell[x][y]=="Tiger"){
                    gameController.onPlayerClickChessPieceTiger(oo, (TigerChessComponent) clickedComponent.getComponents()[0]);
                }
                else if(changshu.mapcell[x][y]=="Wolf"){
                    gameController.onPlayerClickChessPieceWolf(oo, (WolfChessComponent) clickedComponent.getComponents()[0]);
                }
                else System.out.printf("no find mapcell in ChessboardComponent\n");
            }
        }
    }
}
