package com.chess.model;

public enum PieceType {
    PAWN, KNIGHT, BISHOP, KING,
    QUEEN, ROOK;

    // ky tu cho notation: Nf3, e5,...
    public char getNotationLetter(){
        return 'n';
    }
}
