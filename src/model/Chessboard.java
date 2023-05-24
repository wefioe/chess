package model;

import controller.GameController;
import model.changshu;

/**
 * This class store the real chess information.
 * The Chessboard has 9*7 cells, and each cell has a position for chess
 */
public class Chessboard {
    private GameController gameController;

    public Chessboard() {
        changshu.grid =
                new Cell[Constant.CHESSBOARD_ROW_SIZE.getNum()][Constant.CHESSBOARD_COL_SIZE.getNum()];//19X19

        initGrid();
        initPieces();
    }

    private void initGrid() {
        for (int i = 0; i < Constant.CHESSBOARD_ROW_SIZE.getNum(); i++) {
            for (int j = 0; j < Constant.CHESSBOARD_COL_SIZE.getNum(); j++) {
                changshu.grid[i][j] = new Cell();
            }
        }
    }

    private void initPieces() {
        changshu.grid[2][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Elephant",8));
        changshu.grid[6][0].setPiece(new ChessPiece(PlayerColor.RED, "Elephant",8));

        changshu.grid[0][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Lion",7));
        changshu.grid[8][6].setPiece(new ChessPiece(PlayerColor.RED, "Lion",7));

        changshu.grid[0][6].setPiece(new ChessPiece(PlayerColor.BLUE, "Tiger",6));
        changshu.grid[8][0].setPiece(new ChessPiece(PlayerColor.RED, "Tiger",6));

        changshu.grid[2][2].setPiece(new ChessPiece(PlayerColor.BLUE, "Leopard",5));
        changshu.grid[6][4].setPiece(new ChessPiece(PlayerColor.RED, "Leopard",5));

        changshu.grid[2][4].setPiece(new ChessPiece(PlayerColor.BLUE, "Wolf",4));
        changshu.grid[6][2].setPiece(new ChessPiece(PlayerColor.RED, "Wolf",4));

        changshu.grid[1][1].setPiece(new ChessPiece(PlayerColor.BLUE, "Dog",3));
        changshu.grid[7][5].setPiece(new ChessPiece(PlayerColor.RED, "Dog",3));

        changshu.grid[1][5].setPiece(new ChessPiece(PlayerColor.BLUE, "Cat",2));
        changshu.grid[7][1].setPiece(new ChessPiece(PlayerColor.RED, "Cat",2));

        changshu.grid[2][0].setPiece(new ChessPiece(PlayerColor.BLUE, "Rat",1));
        changshu.grid[6][6].setPiece(new ChessPiece(PlayerColor.RED, "Rat",1));
    }

    private ChessPiece getChessPieceAt(ChessboardPoint point) {
        return getGridAt(point).getPiece();
    }

    private Cell getGridAt(ChessboardPoint point) {
        return changshu.grid[point.getRow()][point.getCol()];
    }

    private int calculateDistance(ChessboardPoint src, ChessboardPoint dest) {
        return Math.abs(src.getRow() - dest.getRow()) + Math.abs(src.getCol() - dest.getCol());
    }

    private ChessPiece removeChessPiece(ChessboardPoint point) {
        ChessPiece chessPiece = getChessPieceAt(point);
        getGridAt(point).removePiece();
        return chessPiece;
    }

    public void setChessPiece(ChessboardPoint point, ChessPiece chessPiece) {
        getGridAt(point).setPiece(chessPiece);
        System.out.printf("setpiece=%d %d %s\n",point.getRow(),point.getCol(),chessPiece.getName());
    }
    public void moveChessPiece(ChessboardPoint src, ChessboardPoint dest) {
        /*if(src==null){
            System.out.printf("to=null\n");
            while(true);
        }*/
        System.out.printf("from%d %d to=%d %d\n",src.getRow(),src.getCol(),dest.getRow(),dest.getCol());
        if (!isValidMove(src, dest)) {
            throw new IllegalArgumentException("Illegal chess move!");
        }
        setChessPiece(dest, removeChessPiece(src));
    }

    public Cell[][] getGrid() {
        return changshu.grid;
    }
    public PlayerColor getChessPieceOwner(ChessboardPoint point) {
        return getGridAt(point).getPiece().getOwner();
    }
    public boolean caneat(ChessboardPoint a,ChessboardPoint b){
        if(getChessPieceAt(a).getOwner()==getChessPieceAt(b).getOwner())return false;
        int ra=getChessPieceAt(a).getRank();
        int rb=getChessPieceAt(b).getRank();
        String s1=changshu.mapterrain[a.getRow()][a.getCol()];
        String s2=changshu.mapterrain[b.getRow()][b.getCol()];
        if(s1=="trap") {
            if(gameController.getCurrentPlayer()==PlayerColor.BLUE&&(a.getRow()==7||b.getRow()==8))return false;
            if(gameController.getCurrentPlayer()==PlayerColor.RED &&(a.getRow()==0||a.getRow()==1))return false;
        }
        if(s2=="river"){
            if(ra==1)return false;
            else return true;
        }
        if(s2=="trap"){
            if(gameController.getCurrentPlayer()==PlayerColor.BLUE){
                if(b.getRow()==0||b.getRow()==1) return true;
            }
            else {
                if(b.getRow()==7||b.getRow()==8) return true;
            }
        }
        if(ra==1&&rb==8&&s1!="river")return true;
        else if(ra>=rb&&(ra!=8||rb!=1))return true;
        else return false;
    }

    public boolean isValidMove(ChessboardPoint src, ChessboardPoint dest) {
        //System.out.printf("a=%d\n",getChessPieceAt(src).getRank());
        String s1=changshu.mapcell[src.getRow()][src.getCol()];
        if(s1==null)return false;
        String ss=changshu.mapcell[dest.getRow()][dest.getCol()];
        if(ss == null){
            String s2=changshu.mapterrain[dest.getRow()][dest.getCol()];
            System.out.printf("animal=%s  terrain in isvalidmove=%s\n",s1,s2);
            if(s2=="river"){
                if(s1=="Rat")return true;
                else return false;
            }
            else if(s2=="dens"){
                if(gameController.getCurrentPlayer()==PlayerColor.BLUE&&dest.getRow()==0)return false;
                else if(gameController.getCurrentPlayer()==PlayerColor.RED&&dest.getRow()==8)return false;
                else return true;
            }
            else if((s1=="Lion"||s1=="Tiger")){
                if((src.getCol()==dest.getCol())&&(src.getCol()!=0&&src.getCol()!=3&&src.getCol()!=6)&&(Math.abs(src.getRow()-dest.getRow())==4)){
                    if(changshu.mapcell[3][src.getCol()]==null&&changshu.mapcell[4][src.getCol()]==null&&changshu.mapcell[5][src.getCol()]==null) return true;
                    else return false;
                }
                if(src.getRow()==dest.getRow()&&(src.getCol()==0||src.getCol()==3||src.getCol()==6)&&(Math.abs(src.getCol()-dest.getCol())==3)){
                    if(src.getCol()==0&&dest.getCol()==3){
                        if(changshu.mapcell[src.getRow()][1]==null&&changshu.mapcell[src.getRow()][2]==null)return true;
                        else return false;
                    }
                    else if(src.getCol()==3&&dest.getCol()==0){
                        if(changshu.mapcell[src.getRow()][1]==null&&changshu.mapcell[src.getRow()][2]==null)return true;
                        else return false;
                    }
                    else if(src.getCol()==3&&dest.getCol()==6){
                        if(changshu.mapcell[src.getRow()][4]==null&&changshu.mapcell[src.getRow()][5]==null)return true;
                        else return false;
                    }
                    else {
                        if(changshu.mapcell[src.getRow()][4]==null&&changshu.mapcell[src.getRow()][5]==null)return true;
                        else return false;
                    }
                }
                return calculateDistance(src, dest) == 1;
            }
            else return calculateDistance(src, dest) == 1;
        }else{
            if(calculateDistance(src, dest) == 1){
                return caneat(src, dest);
            }
            else if((s1=="Lion"||s1=="Tiger")){
                if((src.getCol()==dest.getCol())&&(src.getCol()!=0&&src.getCol()!=3&&src.getCol()!=6)&&(Math.abs(src.getRow()-dest.getRow())==4)){
                    if(changshu.mapcell[3][src.getCol()]==null&&changshu.mapcell[4][src.getCol()]==null&&changshu.mapcell[5][src.getCol()]==null){
                        return caneat(src,dest);
                    }
                    else return false;
                }
                if(src.getRow()==dest.getRow()&&(src.getCol()==0||src.getCol()==3||src.getCol()==6)&&(Math.abs(src.getCol()-dest.getCol())==3)){
                    if(src.getCol()==0&&dest.getCol()==3){
                        if(changshu.mapcell[src.getRow()][1]==null&&changshu.mapcell[src.getRow()][2]==null)return caneat(src,dest);
                        else return false;
                    }
                    else if(src.getCol()==3&&dest.getCol()==0){
                        if(changshu.mapcell[src.getRow()][1]==null&&changshu.mapcell[src.getRow()][2]==null)return caneat(src,dest);
                        else return false;
                    }
                    else if(src.getCol()==3&&dest.getCol()==6){
                        if(changshu.mapcell[src.getRow()][4]==null&&changshu.mapcell[src.getRow()][5]==null)return caneat(src,dest);
                        else return false;
                    }
                    else {
                        if(changshu.mapcell[src.getRow()][4]==null&&changshu.mapcell[src.getRow()][5]==null)return caneat(src,dest);
                        else return false;
                    }
                }
                return false;
            }
            else return false;
        }
    }

    public void setGameController(GameController gameController) {
        this.gameController = gameController;
    }

    public GameController getGameController() {
        return gameController;
    }
}
