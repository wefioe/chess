package controller;

import model.ChessPiece;
import model.ChessboardPoint;
import model.PlayerColor;

import java.io.Serializable;

public class Step implements Serializable {
    private ChessboardPoint from;
    private ChessboardPoint to;
    private PlayerColor currentPlayer;
    private int turnCount;
    private String beeaten;

    public Step(ChessboardPoint from, ChessboardPoint to, PlayerColor currentPlayer, int turnCount,String beeaten) {
        this.from = from;
        this.to = to;
        /*this.fromChessPiece = fromChessPiece;
        this.toChessPiece = toChessPiece;*/
        this.currentPlayer = currentPlayer;
        this.turnCount = turnCount;
        this.beeaten=beeaten;
    }

    public ChessboardPoint getFrom() {
        return from;
    }

    public ChessboardPoint getTo() {
        return to;
    }

    public void setFrom(ChessboardPoint from) {
        this.from = from;
    }

    public void setTo(ChessboardPoint to) {
        this.to = to;
    }


    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }

    public int getTurnCount() {
        return turnCount;
    }

    public void setCurrentPlayer(PlayerColor currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setTurnCount(int turnCount) {
        this.turnCount = turnCount;
    }

    public String getBeeaten() {
        return beeaten;
    }

    public void setBeeaten(String beeaten) {
        this.beeaten = beeaten;
    }

    @Override
    public String toString() {
        return "Step{" +
                "from=" + from +
                ", to=" + to +
                ", beeaten" + beeaten +
                '}';
    }
}