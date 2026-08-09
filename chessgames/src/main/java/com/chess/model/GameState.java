package com.chess.model;

import java.util.ArrayList;
import java.util.List;

public class GameState {
    private final Board board;
    private PieceColor currentTurnColor;

    // quyen nhap thanh: 4 ô(trắng-xe hậu, trắng-xe vua, đen-xe hậu, đen-xe vua)
    private final boolean[] castlingRights = new boolean[4];
    private Position enPassantTarget; //null neu ko co
    private int halfMoveClock; // cho luat 50 nuoc di
    private final List<Move> moveHistory = new ArrayList<>();

    public GameState(Board board){
        this.board = board;
    }
    public GameState clone(){
        return null;
    }

    public Board getBoard() {
        return board;
    }

    public PieceColor getCurrentTurnColor() {
        return currentTurnColor;
    }

    public void setCurrentTurnColor(PieceColor currentTurnColor) {
        this.currentTurnColor = currentTurnColor;
    }

    public boolean[] getCastlingRights() {
        return castlingRights;
    }

    public Position getEnPassantTarget() {
        return enPassantTarget;
    }

    public void setEnPassantTarget(Position enPassantTarget) {
        this.enPassantTarget = enPassantTarget;
    }

    public int getHalfMoveClock() {
        return halfMoveClock;
    }

    public void setHalfMoveClock(int halfMoveClock) {
        this.halfMoveClock = halfMoveClock;
    }

    public List<Move> getMoveHistory() {
        return moveHistory;
    }
    

}