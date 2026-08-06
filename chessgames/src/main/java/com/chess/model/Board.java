package com.chess.model;

public class Board {
    private final Piece[][] squares = new Piece[8][8];
    public Piece getPiece(Position p){
        return squares[p.getRow()][p.getCol()];
    }

    public void setPiece(Position p, Piece piece){
        this.squares[p.getRow()][p.getCol()] = piece;
    }

    // Game engin se kiem tra check
    public Position findKingPiece(PieceColor color){
        return null;
    }

    // cho undo Manger chup snap shot
    public Board clone(){
        return null;
    }

    // set up vi tri ban dau 
    public static Board createStandardBoard(){
        return null;
    }

}