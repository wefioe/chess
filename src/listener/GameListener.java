package listener;

import model.ChessboardPoint;
import view.CellComponent;
import chess.CatChessComponent;
import chess.DogChessComponent;
import chess.ElephantChessComponent;
import chess.LeopardChessComponent;
import chess.LionChessComponent;
import chess.RatChessComponent;
import chess.TigerChessComponent;
import chess.WolfChessComponent;

public interface GameListener {

    void onPlayerClickCell(ChessboardPoint point, CellComponent component);
    void drawback();
    void initChessboard();
    void LOADMOVE(ChessboardPoint from,ChessboardPoint to);
    void LOADMOVEElephant(ChessboardPoint from,ChessboardPoint point);
    void LOADMOVECat(ChessboardPoint from,ChessboardPoint point);
    void LOADMOVEDog(ChessboardPoint from,ChessboardPoint point);
    void LOADMOVELeopard(ChessboardPoint from,ChessboardPoint point);
    void LOADMOVELion(ChessboardPoint from,ChessboardPoint point);
    void LOADMOVERat(ChessboardPoint from,ChessboardPoint point);
    void LOADMOVETiger(ChessboardPoint from,ChessboardPoint point);
    void LOADMOVEWolf(ChessboardPoint from,ChessboardPoint point);

    void onPlayerClickChessPieceElephant(ChessboardPoint point, ElephantChessComponent component);
    void onPlayerClickChessPieceCat(ChessboardPoint point, CatChessComponent component);
    void onPlayerClickChessPieceDog(ChessboardPoint point, DogChessComponent component);
    void onPlayerClickChessPieceLeopard(ChessboardPoint point, LeopardChessComponent component);
    void onPlayerClickChessPieceLion(ChessboardPoint point, LionChessComponent component);
    void onPlayerClickChessPieceRat(ChessboardPoint point, RatChessComponent component);
    void onPlayerClickChessPieceTiger(ChessboardPoint point, TigerChessComponent component);
    void onPlayerClickChessPieceWolf(ChessboardPoint point, WolfChessComponent component);
}
