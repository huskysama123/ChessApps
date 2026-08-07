package com.chess.model;

import static com.chess.model.PieceType.*; // Khai báo enum toàn cục
import static com.chess.model.PieceColor.*;

public class Board {
    private final Piece[][] squares = new Piece[8][8];

    public Piece getPiece(Position p) {
        return squares[p.getRow()][p.getCol()];
    }

    public void setPiece(Position p, Piece piece) {
        this.squares[p.getRow()][p.getCol()] = piece;
    }

    /**
     * Finds the position of the king with the specified color on the board.
     *
     * @param color the color of the king to search for
     * @return the king's position if found; otherwise {@code null}
     */
    public Position findKingPiece(PieceColor color) {
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece p = squares[row][col];
                if (p != null && p.getColor() == color && p.getType() == KING) {
                    return new Position(row, col);
                }
            }
        }
        return null;
    }

    // cho undo Manger chup snap shot
    public Board clone() {
        Board copy = new Board();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Piece p = squares[row][col];
                if (p != null) {
                    Position pos = new Position(row, col);
                    copy.setPiece(pos, createStandardPiece(p.getColor(), p.getType(), pos));
                }
            }
        }
        return copy;
    }

    // set up vi tri ban dau
    public static Board createStandardBoard() {
        Board board = new Board();
        PieceType[] backRank = { ROOK, KNIGHT, BISHOP, QUEEN, KING, BISHOP, KNIGHT, ROOK };
        for (int col = 0; col < 8; col++) {
            board.setPiece(new Position(0, col), createStandardPiece(BLACK, backRank[col], new Position(0, col)));
            board.setPiece(new Position(1, col), new Pawn(BLACK, new Position(1, col)));
            board.setPiece(new Position(6, col), new Pawn(WHITE, new Position(6, col)));
            board.setPiece(new Position(7, col), createStandardPiece(WHITE, backRank[col], new Position(7, col)));
        }
        return board;
    }

    public static Piece createStandardPiece(PieceColor color, PieceType type, Position pos) {
        switch (type) {
            case PAWN:
                return new Pawn(color, pos);
            case KNIGHT:
                return new Knight(color, pos);
            case BISHOP:
                return new Bishop(color, pos);
            case QUEEN:
                return new Queen(color, pos);
            case ROOK: 
                return new Rook(color, pos);
            default:
                return new King(color, pos);
        }
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        for(int row = 0;row<8;row++){
            for(int col=0;col<8;col++){
                Piece p  = squares[row][col];
                char c = '.';
                if(p!=null){
                    switch (p.getType()) {
                        case PAWN: c = 'P'; break;
                        case ROOK: c = 'R'; break;
                        case KNIGHT: c = 'N'; break;
                        case BISHOP: c = 'B'; break;
                        case QUEEN: c = 'Q'; break; 
                        default: c = 'K'; break;
                    }
                    if(p.getColor()==WHITE) c = Character.toUpperCase(c);
                }
                sb.append(c).append(' ');
            }
            sb.append('\n');
        }
        return sb.toString();
    }

}