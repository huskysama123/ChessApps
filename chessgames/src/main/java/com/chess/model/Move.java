package com.chess.model;

public class Move {
    private final Position from;
    private final Position to;
    private final Piece moved; // quân đi
    private final Piece captured; // quân đã bị ăn

    private final boolean isEnPassant;
    private final PieceType promotion; // null = ko phong cap
    private final boolean kingSideCastle;
    private final boolean queenSideCastle;

    public Move(Position from, Position to, Piece moved, Piece captured, boolean isEnPassant, PieceType promotion,
            boolean kingSideCastle, boolean queenSideCastle) {
        this.from = from;
        this.to = to;
        this.moved = moved;
        this.captured = captured;
        this.isEnPassant = isEnPassant;
        this.promotion = promotion;
        this.kingSideCastle = kingSideCastle;
        this.queenSideCastle = queenSideCastle;
    }

    public Position getFrom() {
        return from;
    }

    public Position getTo() {
        return to;
    }

    public Piece getMoved() {
        return moved;
    }

    public Piece getCaptured() {
        return captured;
    }

    public boolean isEnPassant() {
        return isEnPassant;
    }

    public PieceType getPromotion() {
        return promotion;
    }

    public boolean isKingSideCastle() {
        return kingSideCastle;
    }

    public boolean isQueenSideCastle() {
        return queenSideCastle;
    }
}
