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
import java.util.*;

import static model.Constant.CHESSBOARD_COL_SIZE;
import static model.Constant.CHESSBOARD_ROW_SIZE;

public class save implements java.io.Serializable{
    public static Deque<Step>sav=new ArrayDeque<Step>();
    @Override
    public String toString(){
        return sav+"\n";
    }
}
