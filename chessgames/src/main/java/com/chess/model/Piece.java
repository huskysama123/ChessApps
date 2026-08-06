package com.chess.model;

public abstract class Piece {
    private final PieceColor color;
    private final PieceType type;
    private Position position;

    public Piece(PieceColor color, PieceType type, Position position) {
        this.color = color;
        this.type = type;
        this.position = position;
    }

    public PieceColor getColor() {
        return color;
    }

    public PieceType getType() {
        return type;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    
}
