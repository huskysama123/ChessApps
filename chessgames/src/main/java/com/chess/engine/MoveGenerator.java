package com.chess.engine;

import java.util.ArrayList;
import java.util.List;

import com.chess.model.Board;
import com.chess.model.Move;
import com.chess.model.Piece;
import com.chess.model.PieceColor;
import com.chess.model.PieceType;
import com.chess.model.Position;

public class MoveGenerator {
    public List<Move> generateMoves(Board board, PieceColor color) {
        List<Move> storeAllMove = new ArrayList<>();
        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Position pos = new Position(row, col);
                Piece p = board.getPiece(pos);

                if (p == null)
                    continue;
                if (p.getColor() != color)
                    continue;

                switch (p.getType()) {
                    case PAWN:
                        // goi lam xu li cua tot
                        generatePawnMoves();

                        break;
                    case KNIGHT:
                        // goi ham xu li cua ngua
                        generateKnightMoves();
                    break;
                    case BISHOP:
                        // goi ham xu li cua tuong
                        generateBishopMoves();
                        break;
                    case KING:
                        // goi ham xu li cua vua
                        generateKingMoves();

                        break;
                    case QUEEN:
                        // goi ham xu li cua hau
                        generateQueenMoves();
                        break;
                    case ROOK:
                        // goi ham xu li cua xe
                        storeAllMove.addAll(generateRookMoves(board, pos, p));
                        break;
                }

            }
        }
        return storeAllMove;
    }

    private List<Move> generatePawnMoves() {
        return null;
    }

    private List<Move> generateRookMoves(Board board, Position pos, Piece p) {
        List<Move> store = new ArrayList<>();

        store.addAll(sliding(board, pos, p, -1, 0)); //Len
        store.addAll(sliding(board, pos, p, 1, 0)); //xuong
        store.addAll(sliding(board, pos, p, 0, -1)); // trai
        store.addAll(sliding(board, pos, p, 0, 1)); // phai

        return store;
    }

    private List<Move> generateBishopMoves() {
        return null;
    }

    private List<Move> generateKingMoves() {
        return null;
    }

    private List<Move> generateKnightMoves() {
        return null;
    }

    private List<Move> generateQueenMoves() {
        return null;
    }

    private List<Move> sliding(Board board, Position from,
            Piece p, int rowDelta, int colDelta) {
        // Lấy ví trí hiệnt tại để + vào rowDelta để biết vị trí
        // di chuyển tiếp theo
        List<Move> store = new ArrayList<>();

        int row = from.getRow() + rowDelta;
        int col = from.getCol() + colDelta;
        // Kiểm tra còn trong bàn cờ
        while (row >= 0 && row < 8 && col >= 0 && col < 8) {
            Position to = new Position(row, col);
            Piece target = board.getPiece(to);
            
            if (target == null) {
                store.add(new Move(from, to, p,
                        target, false, null,
                        false, false));
            } else {
                if(target.getColor() != p.getColor()){
                    store.add(new Move(from, to, p, target,
                         false, null,
                          false, false));
                }
                break;
            }
            row+=rowDelta;
            col+=colDelta;

        }
        return store;
    }
}
