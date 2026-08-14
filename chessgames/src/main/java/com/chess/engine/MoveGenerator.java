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
                        storeAllMove.addAll(generatePawnMoves(board, pos, p));
                        break;
                    case KNIGHT:
                        // goi ham xu li cua ngua
                        storeAllMove.addAll(generateKnightMoves(board, pos, p));
                        break;
                    case BISHOP:
                        // goi ham xu li cua tuong
                        storeAllMove.addAll(generateBishopMoves(board, pos, p));
                        break;
                    case KING:
                        // goi ham xu li cua vua
                        storeAllMove.addAll(generateKingMoves(board, pos, p));
                        break;
                    case QUEEN:
                        // goi ham xu li cua hau
                        storeAllMove.addAll(generateQueenMoves(board, pos, p));
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

    private List<Move> generatePawnMoves(Board board, Position pos, Piece p) {
        List<Move> store = new ArrayList<>();

        int row = pos.getRow();
        int col = pos.getCol();

        // (A) Tinh huong di va hang xuat phat tu mau - CHI 1 LAN
        int direction;
        int startRow;
        if (p.getColor() == PieceColor.WHITE) {
            direction = -1;
            startRow = 6;
        } else {
            direction = 1;
            startRow = 1;
        }

        // (B) DI THANG 1 O
        int oneRow = row + direction;
        if (oneRow >= 0 && oneRow <= 7) { // check bien TRUOC getPiece
            Position to = new Position(oneRow, col);
            Piece target = board.getPiece(to);
            if (target == null) { // di thang chi khi TRONG
                store.add(new Move(pos, to, p, target, false, null, false, false));
            }
        }

        // (C) DI THANG 2 O - DOC LAP, chi o hang xuat phat
        if (row == startRow) {
            int betweenRow = row + direction;
            int twoRow = row + 2 * direction;
            if (twoRow >= 0 && twoRow <= 7) { // check bien
                Position between = new Position(betweenRow, col);
                Position to2 = new Position(twoRow, col);
                Piece betweenTarget = board.getPiece(between);
                Piece target2 = board.getPiece(to2);
                if (betweenTarget == null && target2 == null) { // ca 2 o deu TRONG
                    store.add(new Move(pos, to2, p, target2, false, null, false, false));
                }
            }
        }

        // (D) AN CHEO
        int diagRow = row + direction;
        if (diagRow >= 0 && diagRow <= 7) { // check bien hang
            int[] diagCols = { col - 1, col + 1 };
            for (int c : diagCols) {
                if (c >= 0 && c <= 7) { // check bien cot
                    Position toDiag = new Position(diagRow, c);
                    Piece targetDiag = board.getPiece(toDiag);
                    if (targetDiag != null && targetDiag.getColor() != p.getColor()) {
                        store.add(new Move(pos, toDiag, p, targetDiag, false, null, false, false));
                    }
                }
            }
        }

        return store;
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

    private List<Move> generateKingMoves(Board board, Position pos, Piece p) {
        List<Move> store = new ArrayList<>();
        int[][] direction = {
                { -1, -1 },
                { -1, 1 },
                { -1, 0 },
                { 0, -1 },
                { 1, -1 },
                { 0, 1 },
                { 1, -1 },
                { 1, 1 }
        };

        int row = pos.getRow();
        int col = pos.getCol();

        for (int[] direct : direction) {
            int newRow = row + direct[0];
            int newCol = col + direct[1];

            // Check if validate move in board
            if (newRow < 0 || newRow > 7 || newCol < 0 || newCol > 7) {
                continue;
            }

            Position to = new Position(newRow, newCol);
            Piece target = board.getPiece(to);

            // If that position that have already piece and same color
            if (target != null && target.getColor() == p.getColor()) {
                continue;
            }

            store.add(new Move(pos, to, p, target, false, null, false, false));
        }
        return store;
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
