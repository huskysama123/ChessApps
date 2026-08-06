package com.chess.model;

public enum PieceColor {
    WHITE, BLACK;

    public PieceColor getPieceColor(){
        return this == WHITE ? BLACK : WHITE;
    }
}
