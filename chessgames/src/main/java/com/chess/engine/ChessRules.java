package com.chess.engine;

import java.util.ArrayList;
import java.util.List;

import com.chess.model.Board;
import com.chess.model.Move;
import com.chess.model.Piece;
import com.chess.model.PieceColor;
import com.chess.model.Position;

public class ChessRules {

    // Kieserm tra vua có bị chiếu ko
    public boolean isCheckIn(Board board, PieceColor color) {
        // Lấy ví trí quân VUA
        Position kingPosition = board.findKingPiece(color);

        // lấy vị trí màu quân dịch
        PieceColor opponentColor = color.getPieceColor();

        MoveGenerator generator = new MoveGenerator();
        // tạo nước đi quan địch
        List<Move> opponentMoves = generator.generateMoves(board, opponentColor);

        // vòng lặp kiểm tra trong nước đi của địch
        for (Move moves : opponentMoves) {
            // nước đi = vị trí quân vvua
            if (moves.getTo().equals(kingPosition)) {
                // => true
                return true;
            }
        }

        return false;
    }

    // keim tra nuoc di hop le
    public List<Move> getLegalMoves(Board board, PieceColor color) {
        List<Move> legalMoves = new ArrayList<>();

        // lấy generateMove
        MoveGenerator generator = new MoveGenerator();
        List<Move> allMoves = generator.generateMoves(board, color);

        // duyệt toàn bộ allMoves
        for (Move move : allMoves) {
            // clone board để check
            Board cloneBoard = board.clone();
            // thuc hien nuoc di tren clone board
            Piece movingPiece = cloneBoard.getPiece(move.getFrom());
            cloneBoard.setPiece(move.getFrom(), null);
            cloneBoard.setPiece(move.getTo(), movingPiece);
            // kiếm tra
            boolean isChecked = isCheckIn(cloneBoard, color);
            if (!isChecked) {
                legalMoves.add(move);
            }
            // còn bị check thì bỏ
        }
        // ko chiếu thì giữ lại thành legalMoves
        return legalMoves;
    }
}
