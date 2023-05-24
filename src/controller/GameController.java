package controller;


import listener.GameListener;
import model.*;
import view.CellComponent;
import chess.CatChessComponent;
import chess.DogChessComponent;
import chess.ElephantChessComponent;
import chess.LeopardChessComponent;
import chess.LionChessComponent;
import chess.RatChessComponent;
import chess.TigerChessComponent;
import chess.WolfChessComponent;
import view.ChessGameFrame;
import view.ChessboardComponent;

import java.io.*;

/**
 * Controller is the connection between model and view,
 * when a Controller receive a request from a view, the Controller
 * analyzes and then hands over to the model for processing
 * [in this demo the request methods are onPlayerClickCell() and onPlayerClickChessPiece()]
 *
*/
public class GameController implements GameListener {

    public ChessGameFrame frame;
    private Chessboard model;
    private ChessboardComponent view;
    private PlayerColor currentPlayer;
    public  Chessboard board;

    // Record whether there is a selected piece before
    private ChessboardPoint selectedPoint;

    public GameController(ChessboardComponent view, Chessboard model) {
        this.view = view;
        this.model = model;
        this.currentPlayer = PlayerColor.BLUE;

        view.registerController(this);
        view.initiateChessComponent(model);
        view.repaint();
    }

    // after a valid move swap the player
    private void swapColor() {
        currentPlayer = currentPlayer == PlayerColor.BLUE ? PlayerColor.RED : PlayerColor.BLUE;
    }


    // click an empty cell
    @Override
    public void onPlayerClickCell(ChessboardPoint point, CellComponent component) {
        if (selectedPoint != null && model.isValidMove(selectedPoint, point)) {
            ChessboardPoint from=selectedPoint,to=point;
            model.moveChessPiece(selectedPoint, point);
            int x= selectedPoint.getRow(), y=selectedPoint.getCol();
            System.out.printf("x=%d y=%d\n",x,y);
            if(changshu.mapcell[x][y]=="Elephant") view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(selectedPoint,0));
            else if(changshu.mapcell[x][y]=="Cat") view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(selectedPoint,0));
            else if(changshu.mapcell[x][y]=="Dog") view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(selectedPoint,0));
            else if(changshu.mapcell[x][y]=="Leopard") view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(selectedPoint,0));
            else if(changshu.mapcell[x][y]=="Lion") view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(selectedPoint,0));
            else if(changshu.mapcell[x][y]=="Rat") view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(selectedPoint,0));
            else if(changshu.mapcell[x][y]=="Tiger") view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(selectedPoint,0));
            else if(changshu.mapcell[x][y]=="Wolf") view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(selectedPoint,0));
            else System.out.printf("no find mapcell in gamecontroller\n");
            selectedPoint = null;
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,to,currentPlayer,changshu.turn,"null"));
            iswin(to);
            //  if the chess enter Dens or Traps and so on
        }
    }
    @Override
    public void LOADMOVE(ChessboardPoint from,ChessboardPoint to){
        if (from != null && model.isValidMove(from, to)) {
            model.moveChessPiece(from, to);
            int x= from.getRow(), y=from.getCol();
            //System.out.printf("x=%d y=%d\n",x,y);
            if(changshu.mapcell[x][y]=="Elephant") view.setChessComponentAtGridElephant(to, view.removeChessComponentAtGridElephant(from,0));
            else if(changshu.mapcell[x][y]=="Cat") view.setChessComponentAtGridCat(to, view.removeChessComponentAtGridCat(from,0));
            else if(changshu.mapcell[x][y]=="Dog") view.setChessComponentAtGridDog(to, view.removeChessComponentAtGridDog(from,0));
            else if(changshu.mapcell[x][y]=="Leopard") view.setChessComponentAtGridLeopard(to, view.removeChessComponentAtGridLeopard(from,0));
            else if(changshu.mapcell[x][y]=="Lion") view.setChessComponentAtGridLion(to, view.removeChessComponentAtGridLion(from,0));
            else if(changshu.mapcell[x][y]=="Rat") view.setChessComponentAtGridRat(to, view.removeChessComponentAtGridRat(from,0));
            else if(changshu.mapcell[x][y]=="Tiger") view.setChessComponentAtGridTiger(to, view.removeChessComponentAtGridTiger(from,0));
            else if(changshu.mapcell[x][y]=="Wolf") view.setChessComponentAtGridWolf(to, view.removeChessComponentAtGridWolf(from,0));
            else System.out.printf("no find mapcell in gamecontroller\n");
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,to,currentPlayer,changshu.turn,"null"));
            iswin(to);
        }
    }
    @Override
    public void drawback(){
        if(changshu.turn==1)return;
        Step now=changshu.sav.pop();
        System.out.println(now);
        /*if(now.getTo()==null){
            System.out.printf("to=null\n");
            while(true);
        }*/
        model.moveChessPiece(now.getTo(),now.getFrom());
        int x=now.getTo().getRow(), y=now.getTo().getCol();
        //System.out.printf("12 x=%d y=%d\n",x,y);
        if(changshu.mapcell[x][y]=="Elephant") view.setChessComponentAtGridElephant(now.getFrom(), view.removeChessComponentAtGridElephant(now.getTo(),0));
        else if(changshu.mapcell[x][y]=="Cat") view.setChessComponentAtGridCat(now.getFrom(), view.removeChessComponentAtGridCat(now.getTo(),0));
        else if(changshu.mapcell[x][y]=="Dog") view.setChessComponentAtGridDog(now.getFrom(), view.removeChessComponentAtGridDog(now.getTo(),0));
        else if(changshu.mapcell[x][y]=="Leopard") view.setChessComponentAtGridLeopard(now.getFrom(), view.removeChessComponentAtGridLeopard(now.getTo(),0));
        else if(changshu.mapcell[x][y]=="Lion") view.setChessComponentAtGridLion(now.getFrom(), view.removeChessComponentAtGridLion(now.getTo(),0));
        else if(changshu.mapcell[x][y]=="Rat") view.setChessComponentAtGridRat(now.getFrom(), view.removeChessComponentAtGridRat(now.getTo(),0));
        else if(changshu.mapcell[x][y]=="Tiger") view.setChessComponentAtGridTiger(now.getFrom(), view.removeChessComponentAtGridTiger(now.getTo(),0));
        else if(changshu.mapcell[x][y]=="Wolf") view.setChessComponentAtGridWolf(now.getFrom(), view.removeChessComponentAtGridWolf(now.getTo(),0));
        else System.out.printf("no find mapcell in gamecontroller\n");
        selectedPoint = null;
        if(now.getBeeaten()=="Elephant"){
            ElephantChessComponent a=changshu.stackElephant.pop();
            view.setChessComponentAtGridElephant(now.getTo(),a);
            board.setChessPiece(now.getTo(),new  ChessPiece(a.getOwner(),"Elephant",8));
            if(a.getOwner()==PlayerColor.BLUE)changshu.Bluechessnumber++;
            else changshu.Redchessnumber++;

        }
        else if(now.getBeeaten()=="Cat"){
            CatChessComponent a=changshu.stackCat.pop();
            view.setChessComponentAtGridCat(now.getTo(),a);
            board.setChessPiece(now.getTo(),new  ChessPiece(a.getOwner(),"Cat",2));
            if(a.getOwner()==PlayerColor.BLUE)changshu.Bluechessnumber++;
            else changshu.Redchessnumber++;
        }
        else if(now.getBeeaten()=="Dog"){
            DogChessComponent a=changshu.stackDog.pop();
            view.setChessComponentAtGridDog(now.getTo(),a);
            board.setChessPiece(now.getTo(),new  ChessPiece(a.getOwner(),"Dog",3));
            if(a.getOwner()==PlayerColor.BLUE)changshu.Bluechessnumber++;
            else changshu.Redchessnumber++;
        }
        else if(now.getBeeaten()=="Leopard"){
            LeopardChessComponent a=changshu.stackLeopard.pop();
            view.setChessComponentAtGridLeopard(now.getTo(),a);
            board.setChessPiece(now.getTo(),new  ChessPiece(a.getOwner(),"Leopard",5));
            if(a.getOwner()==PlayerColor.BLUE)changshu.Bluechessnumber++;
            else changshu.Redchessnumber++;
        }
        else if(now.getBeeaten()=="Lion"){
            LionChessComponent a=changshu.stackLion.pop();
            view.setChessComponentAtGridLion(now.getTo(),a);
            board.setChessPiece(now.getTo(),new  ChessPiece(a.getOwner(),"Lion",7));
            if(a.getOwner()==PlayerColor.BLUE)changshu.Bluechessnumber++;
            else changshu.Redchessnumber++;
        }
        else if(now.getBeeaten()=="Rat"){
            RatChessComponent a=changshu.stackRat.pop();
            view.setChessComponentAtGridRat(now.getTo(),a);
            board.setChessPiece(now.getTo(),new  ChessPiece(a.getOwner(),"Rat",1));
            if(a.getOwner()==PlayerColor.BLUE)changshu.Bluechessnumber++;
            else changshu.Redchessnumber++;
        }
        else if(now.getBeeaten()=="Tiger"){
            TigerChessComponent a=changshu.stackTiger.pop();
            view.setChessComponentAtGridTiger(now.getTo(),a);
            board.setChessPiece(now.getTo(),new  ChessPiece(a.getOwner(),"Tiger",6));
            if(a.getOwner()==PlayerColor.BLUE)changshu.Bluechessnumber++;
            else changshu.Redchessnumber++;
        }
        else if(now.getBeeaten()=="Wolf"){
            WolfChessComponent a=changshu.stackWolf.pop();
            view.setChessComponentAtGridWolf(now.getTo(),a);
            board.setChessPiece(now.getTo(),new  ChessPiece(a.getOwner(),"Wolf",4));
            if(a.getOwner()==PlayerColor.BLUE)changshu.Bluechessnumber++;
            else changshu.Redchessnumber++;
        }
        else System.out.printf("beeaten=null\n");
        System.out.printf("x=%d y=%d s=%s\n",x,y,changshu.mapcell[x][y]);
        swapColor();
        changshu.turn--;
        view.repaint();
    }
    @Override
    public void initChessboard(){
        while(changshu.turn>1){
            drawback();
        }
        System.out.printf("nowturn=%d bluenumber=%d rednumber%d\n",changshu.turn,changshu.Bluechessnumber,changshu.Redchessnumber);
    }
    public void iswin(ChessboardPoint to){
        if(changshu.mapterrain[to.getRow()][to.getCol()]=="dens"||changshu.Bluechessnumber==0||changshu.Redchessnumber==0){
            int win=0;
            if(changshu.Bluechessnumber==0)win=2;
            else if(changshu.Redchessnumber==0)win=1;
            else if(currentPlayer==PlayerColor.BLUE&&to.getRow()==0)win=2;
            else if(currentPlayer==PlayerColor.RED&&to.getRow()==8)win=1;
            else return;
            if(win==1){
                System.out.printf("Blue win!\n");
            }
            else{
                System.out.printf("Red win!\n");
            }
            // 赢了的面板
        }
    }
    // click a cell with a chess
    @Override
    public void onPlayerClickChessPieceElephant(ChessboardPoint point, ElephantChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }//下面 selectedpoint是要动的棋子，point是被吃的棋子
        else if(model.isValidMove(selectedPoint, point)){
            String s=changshu.mapcell[selectedPoint.getRow()][selectedPoint.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridElephant(point,1);
            ChessboardPoint from=selectedPoint,to=point;
            model.moveChessPiece(selectedPoint, point);
            int x= selectedPoint.getRow(), y=selectedPoint.getCol();
            //System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(selectedPoint,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(selectedPoint,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(selectedPoint,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(selectedPoint,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(selectedPoint,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(selectedPoint,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(selectedPoint,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(selectedPoint,0));
            selectedPoint = null;
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,to,currentPlayer,changshu.turn,"Elephant"));
            iswin(to);
        }
    }
    @Override
    public void LOADMOVEElephant(ChessboardPoint from,ChessboardPoint point) {
        if(model.isValidMove(from, point)){
            String s=changshu.mapcell[from.getRow()][from.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridElephant(point,1);
            model.moveChessPiece(from, point);
            int x= from.getRow(), y=from.getCol();
            //System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(from,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(from,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(from,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(from,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(from,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(from,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(from,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(from,0));
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,point,currentPlayer,changshu.turn,"Elephant"));
            iswin(point);
        }
    }
    @Override
    public void LOADMOVECat(ChessboardPoint from,ChessboardPoint point) {
        if(model.isValidMove(from, point)){
            String s=changshu.mapcell[from.getRow()][from.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridCat(point,1);
            model.moveChessPiece(from, point);
            int x= from.getRow(), y=from.getCol();
            //System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(from,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(from,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(from,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(from,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(from,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(from,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(from,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(from,0));
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,point,currentPlayer,changshu.turn,"Cat"));
            iswin(point);
        }
    }
    @Override
    public void LOADMOVEDog(ChessboardPoint from,ChessboardPoint point) {
        if(model.isValidMove(from, point)){
            String s=changshu.mapcell[from.getRow()][from.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridDog(point,1);
            model.moveChessPiece(from, point);
            int x= from.getRow(), y=from.getCol();
            //System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(from,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(from,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(from,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(from,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(from,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(from,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(from,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(from,0));
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,point,currentPlayer,changshu.turn,"Dog"));
            iswin(point);
        }
    }
    @Override
    public void LOADMOVELeopard(ChessboardPoint from,ChessboardPoint point) {
        if(model.isValidMove(from, point)){
            String s=changshu.mapcell[from.getRow()][from.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridLeopard(point,1);
            model.moveChessPiece(from, point);
            int x= from.getRow(), y=from.getCol();
            //System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(from,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(from,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(from,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(from,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(from,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(from,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(from,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(from,0));
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,point,currentPlayer,changshu.turn,"Leopard"));
            iswin(point);
        }
    }
    @Override
    public void LOADMOVELion(ChessboardPoint from,ChessboardPoint point) {
        if(model.isValidMove(from, point)){
            String s=changshu.mapcell[from.getRow()][from.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridLion(point,1);
            model.moveChessPiece(from, point);
            int x= from.getRow(), y=from.getCol();
            //System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(from,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(from,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(from,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(from,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(from,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(from,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(from,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(from,0));
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,point,currentPlayer,changshu.turn,"Lion"));
            iswin(point);
        }
    }
    @Override
    public void LOADMOVERat(ChessboardPoint from,ChessboardPoint point) {
        if(model.isValidMove(from, point)){
            String s=changshu.mapcell[from.getRow()][from.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridRat(point,1);
            model.moveChessPiece(from, point);
            int x= from.getRow(), y=from.getCol();
            //System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(from,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(from,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(from,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(from,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(from,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(from,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(from,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(from,0));
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,point,currentPlayer,changshu.turn,"Rat"));
            iswin(point);
        }
    }
    @Override
    public void LOADMOVETiger(ChessboardPoint from,ChessboardPoint point) {
        if(model.isValidMove(from, point)){
            String s=changshu.mapcell[from.getRow()][from.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridTiger(point,1);
            model.moveChessPiece(from, point);
            int x= from.getRow(), y=from.getCol();
            //System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(from,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(from,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(from,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(from,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(from,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(from,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(from,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(from,0));
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,point,currentPlayer,changshu.turn,"Tiger"));
            iswin(point);
        }
    }
    @Override
    public void LOADMOVEWolf(ChessboardPoint from,ChessboardPoint point) {
        if(model.isValidMove(from, point)){
            String s=changshu.mapcell[from.getRow()][from.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridWolf(point,1);
            model.moveChessPiece(from, point);
            int x= from.getRow(), y=from.getCol();
            //System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(from,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(from,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(from,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(from,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(from,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(from,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(from,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(from,0));
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,point,currentPlayer,changshu.turn,"Wolf"));
            iswin(point);
        }
    }
    @Override
    public void onPlayerClickChessPieceCat(ChessboardPoint point, CatChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }//下面 selectedpoint是要动的棋子，point是被吃的棋子
        else if(model.isValidMove(selectedPoint, point)){
            String s=changshu.mapcell[selectedPoint.getRow()][selectedPoint.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridCat(point,1);
            ChessboardPoint from=selectedPoint,to=point;
            model.moveChessPiece(selectedPoint, point);
            int x= selectedPoint.getRow(), y=selectedPoint.getCol();
            System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(selectedPoint,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(selectedPoint,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(selectedPoint,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(selectedPoint,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(selectedPoint,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(selectedPoint,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(selectedPoint,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(selectedPoint,0));
            selectedPoint = null;
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,to,currentPlayer,changshu.turn,"Cat"));
            iswin(to);
        }
    }
    @Override
    public void onPlayerClickChessPieceDog(ChessboardPoint point, DogChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }//下面 selectedpoint是要动的棋子，point是被吃的棋子
        else if(model.isValidMove(selectedPoint, point)){
            String s=changshu.mapcell[selectedPoint.getRow()][selectedPoint.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridDog(point,1);
            ChessboardPoint from=selectedPoint,to=point;
            model.moveChessPiece(selectedPoint, point);
            int x= selectedPoint.getRow(), y=selectedPoint.getCol();
            System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(selectedPoint,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(selectedPoint,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(selectedPoint,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(selectedPoint,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(selectedPoint,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(selectedPoint,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(selectedPoint,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(selectedPoint,0));
            selectedPoint = null;
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,to,currentPlayer,changshu.turn,"Dog"));
            iswin(to);
        }
    }
    @Override
    public void onPlayerClickChessPieceLeopard(ChessboardPoint point, LeopardChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }//下面 selectedpoint是要动的棋子，point是被吃的棋子
        else if(model.isValidMove(selectedPoint, point)){
            String s=changshu.mapcell[selectedPoint.getRow()][selectedPoint.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridLeopard(point,1);
            ChessboardPoint from=selectedPoint,to=point;
            model.moveChessPiece(selectedPoint, point);
            int x= selectedPoint.getRow(), y=selectedPoint.getCol();
            System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(selectedPoint,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(selectedPoint,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(selectedPoint,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(selectedPoint,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(selectedPoint,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(selectedPoint,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(selectedPoint,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(selectedPoint,0));
            selectedPoint = null;
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,to,currentPlayer,changshu.turn,"Leopard"));
            iswin(to);
        }
    }
    @Override
    public void onPlayerClickChessPieceLion(ChessboardPoint point, LionChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }//下面 selectedpoint是要动的棋子，point是被吃的棋子
        else if(model.isValidMove(selectedPoint, point)){
            String s=changshu.mapcell[selectedPoint.getRow()][selectedPoint.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridLion(point,1);
            ChessboardPoint from=selectedPoint,to=point;
            model.moveChessPiece(selectedPoint, point);
            int x= selectedPoint.getRow(), y=selectedPoint.getCol();
            System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(selectedPoint,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(selectedPoint,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(selectedPoint,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(selectedPoint,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(selectedPoint,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(selectedPoint,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(selectedPoint,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(selectedPoint,0));
            selectedPoint = null;
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,to,currentPlayer,changshu.turn,"Lion"));
            iswin(to);
        }
    }
    @Override
    public void onPlayerClickChessPieceRat(ChessboardPoint point, RatChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }//下面 selectedpoint是要动的棋子，point是被吃的棋子
        else if(model.isValidMove(selectedPoint, point)){
            String s=changshu.mapcell[selectedPoint.getRow()][selectedPoint.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridRat(point,1);
            ChessboardPoint from=selectedPoint,to=point;
            model.moveChessPiece(selectedPoint, point);
            int x= selectedPoint.getRow(), y=selectedPoint.getCol();
            System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(selectedPoint,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(selectedPoint,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(selectedPoint,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(selectedPoint,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(selectedPoint,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(selectedPoint,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(selectedPoint,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(selectedPoint,0));
            selectedPoint = null;
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,to,currentPlayer,changshu.turn,"Rat"));
            iswin(to);
        }
    }
    @Override
    public void onPlayerClickChessPieceTiger(ChessboardPoint point, TigerChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }//下面 selectedpoint是要动的棋子，point是被吃的棋子
        else if(model.isValidMove(selectedPoint, point)){
            String s=changshu.mapcell[selectedPoint.getRow()][selectedPoint.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridTiger(point,1);
            ChessboardPoint from=selectedPoint,to=point;
            model.moveChessPiece(selectedPoint, point);
            int x= selectedPoint.getRow(), y=selectedPoint.getCol();
            System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(selectedPoint,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(selectedPoint,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(selectedPoint,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(selectedPoint,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(selectedPoint,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(selectedPoint,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(selectedPoint,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(selectedPoint,0));
            selectedPoint = null;
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,to,currentPlayer,changshu.turn,"Tiger"));
            iswin(to);
        }
    }
    @Override
    public void onPlayerClickChessPieceWolf(ChessboardPoint point, WolfChessComponent component) {
        if (selectedPoint == null) {
            if (model.getChessPieceOwner(point).equals(currentPlayer)) {
                selectedPoint = point;
                component.setSelected(true);
                component.repaint();
            }
        } else if (selectedPoint.equals(point)) {
            selectedPoint = null;
            component.setSelected(false);
            component.repaint();
        }//下面 selectedpoint是要动的棋子，point是被吃的棋子
        else if(model.isValidMove(selectedPoint, point)){
            String s=changshu.mapcell[selectedPoint.getRow()][selectedPoint.getCol()];
            System.out.printf("now selected %s\n",s);
            view.removeChessComponentAtGridWolf(point,1);
            ChessboardPoint from=selectedPoint,to=point;
            model.moveChessPiece(selectedPoint, point);
            int x= selectedPoint.getRow(), y=selectedPoint.getCol();
            System.out.printf("eat x=%d y=%d\n",x,y);
            if(s=="Elephant")view.setChessComponentAtGridElephant(point, view.removeChessComponentAtGridElephant(selectedPoint,0));
            else if(s=="Lion")view.setChessComponentAtGridLion(point, view.removeChessComponentAtGridLion(selectedPoint,0));
            else if(s=="Tiger")view.setChessComponentAtGridTiger(point, view.removeChessComponentAtGridTiger(selectedPoint,0));
            else if(s=="Leopard")view.setChessComponentAtGridLeopard(point, view.removeChessComponentAtGridLeopard(selectedPoint,0));
            else if(s=="Wolf")view.setChessComponentAtGridWolf(point, view.removeChessComponentAtGridWolf(selectedPoint,0));
            else if(s=="Dog")view.setChessComponentAtGridDog(point, view.removeChessComponentAtGridDog(selectedPoint,0));
            else if(s=="Cat")view.setChessComponentAtGridCat(point, view.removeChessComponentAtGridCat(selectedPoint,0));
            else view.setChessComponentAtGridRat(point, view.removeChessComponentAtGridRat(selectedPoint,0));
            selectedPoint = null;
            swapColor();
            changshu.turn++;
            view.repaint();
            changshu.sav.push(new Step(from,to,currentPlayer,changshu.turn,"Wolf"));
            iswin(to);
        }
    }

    public PlayerColor getCurrentPlayer() {
        return currentPlayer;
    }
    public void save()
    {
        Savetxt o=new Savetxt();
        for(Step oo:changshu.sav){
            o.sav.addLast(new Step(oo.getFrom(),oo.getTo(),oo.getCurrentPlayer(),oo.getTurnCount(),oo.getBeeaten()));
        }
        try
        {
            FileOutputStream fileOut =
                    new FileOutputStream("/java/sav.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(o);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in /sav.txt");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
    }
    Savetxt e;
    public void load()
    {
        e =new Savetxt();
        try
        {
            FileInputStream fileIn = new FileInputStream("/java/sav.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Savetxt ee = (Savetxt) in.readObject();
            for(Step oo:ee.sav){
                Step kk=new Step(oo.getFrom(),oo.getTo(),oo.getCurrentPlayer(),oo.getTurnCount(),oo.getBeeaten());
                e.sav.addLast(kk);
            }
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("load finished");
        System.out.println(e);
        initChessboard();
        /*for(Step oo:ee.sav){
            String s=changshu.mapcell[oo.getTo().getRow()][oo.getTo().getCol()];
            if(s=="Elephant")LOADMOVEElephant(oo.getFrom(),oo.getTo());
            else if(s=="Cat")LOADMOVECat(oo.getFrom(),oo.getTo());
            else if(s=="Dog")LOADMOVEDog(oo.getFrom(),oo.getTo());
            else if(s=="Leopard")LOADMOVELeopard(oo.getFrom(),oo.getTo());
            else if(s=="Lion")LOADMOVELion(oo.getFrom(),oo.getTo());
            else if(s=="Rat")LOADMOVERat(oo.getFrom(),oo.getTo());
            else if(s=="Tiger")LOADMOVETiger(oo.getFrom(),oo.getTo());
            else if(s=="Wolf")LOADMOVEWolf(oo.getFrom(),oo.getTo());
            else LOADMOVE(oo.getFrom(),oo.getTo());
        }*/
    }
    public void slowload()
    {
        /*save e = null;
        try
        {
            FileInputStream fileIn = new FileInputStream("/sav.txt");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (save) in.readObject();
            in.close();
            fileIn.close();
        }catch(IOException i)
        {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c)
        {
            System.out.println("Employee class not found");
            c.printStackTrace();
            return;
        }
        System.out.println("load finished");
        System.out.println(e);
        initChessboard();*/
        //for(Step oo:e.sav){
        if(e.sav.size()==0)return;
        Step oo=e.sav.getFirst();
        e.sav.removeFirst();
            String s=changshu.mapcell[oo.getTo().getRow()][oo.getTo().getCol()];
            if(s=="Elephant")LOADMOVEElephant(oo.getFrom(),oo.getTo());
            else if(s=="Cat")LOADMOVECat(oo.getFrom(),oo.getTo());
            else if(s=="Dog")LOADMOVEDog(oo.getFrom(),oo.getTo());
            else if(s=="Leopard")LOADMOVELeopard(oo.getFrom(),oo.getTo());
            else if(s=="Lion")LOADMOVELion(oo.getFrom(),oo.getTo());
            else if(s=="Rat")LOADMOVERat(oo.getFrom(),oo.getTo());
            else if(s=="Tiger")LOADMOVETiger(oo.getFrom(),oo.getTo());
            else if(s=="Wolf")LOADMOVEWolf(oo.getFrom(),oo.getTo());
            else LOADMOVE(oo.getFrom(),oo.getTo());
            //view.repaint();
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        //}
    }
    public void quickload()
    {
        for(Step oo:e.sav){
            String s=changshu.mapcell[oo.getTo().getRow()][oo.getTo().getCol()];
            if(s=="Elephant")LOADMOVEElephant(oo.getFrom(),oo.getTo());
            else if(s=="Cat")LOADMOVECat(oo.getFrom(),oo.getTo());
            else if(s=="Dog")LOADMOVEDog(oo.getFrom(),oo.getTo());
            else if(s=="Leopard")LOADMOVELeopard(oo.getFrom(),oo.getTo());
            else if(s=="Lion")LOADMOVELion(oo.getFrom(),oo.getTo());
            else if(s=="Rat")LOADMOVERat(oo.getFrom(),oo.getTo());
            else if(s=="Tiger")LOADMOVETiger(oo.getFrom(),oo.getTo());
            else if(s=="Wolf")LOADMOVEWolf(oo.getFrom(),oo.getTo());
            else LOADMOVE(oo.getFrom(),oo.getTo());
        }
    }
}
