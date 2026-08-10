package com.chess.engine;

import java.util.ArrayList;
import java.util.List;

import com.chess.model.Board;
import com.chess.model.Move;
import com.chess.model.Piece;
import com.chess.model.PieceColor;
import com.chess.model.PieceType;
import com.chess.model.Position;

import javafx.geometry.Pos;

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

                        break;
                    case KNIGHT:
                        // goi ham xu li cua ngua

                        break;
                    case BISHOP:
                        // goi ham xu li cua tuong
                        storeAllMove.addAll(generateBishopMoves(board, pos, p));
                        break;
                    case KING:
                        // goi ham xu li cua vua

                        break;
                    case QUEEN:
                        // goi ham xu li cua hau

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

        store.addAll(sliding(board, pos, p, -1, 0)); // Len
        store.addAll(sliding(board, pos, p, 1, 0)); // xuong
        store.addAll(sliding(board, pos, p, 0, -1)); // trai
        store.addAll(sliding(board, pos, p, 0, 1)); // phai

        return store;
    }

    private List<Move> generateBishopMoves(Board board, Position pos, Piece p) {
        List<Move> store = new ArrayList<>();

        store.addAll(sliding(board, pos, p, -1, -1)); // Len
        store.addAll(sliding(board, pos, p, -1, 1)); // xuong
        store.addAll(sliding(board, pos, p, 1, -1)); // trai
        store.addAll(sliding(board, pos, p, 1, 1)); // phai

        return store;
    }

    private List<Move> generateKingMoves() {
        return null;
    }

    private List<Move> generateKnightMoves(Board board, Position pos, Piece p) {
        List<Move> store = new ArrayList<>();

        int[][] direction = {
                { -2, -1 },
                { -2, 1 },
                { -1, -2 },
                { -1, 2 },
                { 1, -2 },
                { 1, 2 },
                { 2, -1 },
                { 2, 1 }
        };

        // Lay vi tri quan ngua
        int row = pos.getRow();
        int col = pos.getCol();

        for (int[] direct : direction) {
            int newRow = row + direct[0];
            int newCol = col + direct[1];

            // Kiem tra neu ngoai ban co
            if (newRow < 0 || newRow > 7 ||
                    newCol < 0 || newCol > 7) {
                continue;
            }

            Position to = new Position(newRow, newCol);
            Piece target = board.getPiece(to);
            // Kiem tra neu quan co cung mau
            if (target != null && target.getColor() == p.getColor()) {
                continue;
            }

            store.add(new Move(pos, to, p, target, false,
                    null, false, false));

        }
        return store;
    }

    private List<Move> generateQueenMoves(Board board, Position pos, Piece p) {
        List<Move> store = new ArrayList<>();
        store.addAll(sliding(board, pos, p, -1, 0)); // Len
        store.addAll(sliding(board, pos, p, 1, 0)); // xuong
        store.addAll(sliding(board, pos, p, 0, -1)); // trai
        store.addAll(sliding(board, pos, p, 0, 1)); // phai

        store.addAll(sliding(board, pos, p, -1, -1)); // Len
        store.addAll(sliding(board, pos, p, -1, 1)); // xuong
        store.addAll(sliding(board, pos, p, 1, -1)); // trai
        store.addAll(sliding(board, pos, p, 1, 1)); // phai
        return store;
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
                if (target.getColor() != p.getColor()) {
                    store.add(new Move(from, to, p, target,
                            false, null,
                            false, false));
                }
                break;
            }
            row += rowDelta;
            col += colDelta;

        }
        return store;
    }
}
