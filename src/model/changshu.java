package model;
import chess.CatChessComponent;
import chess.DogChessComponent;
import chess.ElephantChessComponent;
import chess.LeopardChessComponent;
import chess.LionChessComponent;
import chess.RatChessComponent;
import chess.TigerChessComponent;
import chess.WolfChessComponent;
import controller.Step;
import view.CellComponent;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

public class changshu  {
    public static String[][] mapcell=new String[10][10];
    public static String[][] mapterrain=new String[10][10];
    public static int turn=1;
    public static int Bluechessnumber=8;
    public static int Redchessnumber=8;
    public static Stack<Step>sav=new Stack<>();
    public static Stack<ElephantChessComponent>stackElephant=new Stack<>();
    public static Stack<CatChessComponent>stackCat=new Stack<>();
    public static Stack<DogChessComponent>stackDog=new Stack<>();
    public static Stack<LeopardChessComponent>stackLeopard=new Stack<>();
    public static Stack<LionChessComponent>stackLion=new Stack<>();
    public static Stack<RatChessComponent>stackRat=new Stack<>();
    public static Stack<TigerChessComponent>stackTiger=new Stack<>();
    public static Stack<WolfChessComponent>stackWolf=new Stack<>();
    public static Cell[][] grid;
    public static CellComponent[][] gridComponents = new CellComponent[CHESSBOARD_ROW_SIZE.getNum()][CHESSBOARD_COL_SIZE.getNum()];
    //public static Deque<Step> savetxt=new ArrayDeque<Step>();
    @Override
    public String toString(){
        return sav+" "+
                turn+"  "+
                Bluechessnumber+"  "+
                Redchessnumber;
    }
}
