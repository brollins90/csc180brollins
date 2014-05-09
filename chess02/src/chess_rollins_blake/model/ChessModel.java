package chess_rollins_blake.model;

import java.util.Stack;

import chess_rollins_blake.lib.BoardLocation;
import chess_rollins_blake.lib.ChessMove;
import chess_rollins_blake.lib.MoveType;
import chess_rollins_blake.lib.Piece;
import chess_rollins_blake.lib.PieceStatus;

public class ChessModel extends java.util.Observable {

    public PieceList pieces;
    private Stack<ChessMove> moves;
    private Stack<ChessMove> movesRedo;

    public ChessModel() {
        System.out.println("ChessModel()");

        this.pieces = new PieceList();
        this.moves = new Stack<>();
        this.movesRedo = new Stack<>();

    }

    public boolean addPiece(BoardLocation loc, Piece p) {
        pieces.add(loc, p);
        return true;
    }

    public Piece getPiece(BoardLocation loc) {
        return pieces.get(loc);
    }

    public boolean addMove(ChessMove m) {
        // todo
        if (validateMove(m)) {
            // System.out.println("Move valid");
            moves.push(m);
            if (m.type == MoveType.ADD) {
                // System.out.println("Adding piece");
                pieces.add(m.destLoc, m.piece);
            } else if (m.type == MoveType.CAPTURE) {
                // System.out.println("captured piece");
                this.pieces.capturePiece(m.destLoc);
                this.pieces.move(m.srcLoc, m.destLoc);
            } else if (m.type == MoveType.MOVE) {
                // System.out.println("moving piece");
                this.pieces.move(m.srcLoc, m.destLoc);
            }
            if (m.subMove != null) {
                addMove(m.subMove);
            }

        } else {

            // System.out.println("Move NOT valid");
        }
        return true;
    }

    public boolean undoMove() {
        ChessMove m = moves.pop();
        movesRedo.push(m);

        return true;
    }

    public boolean redoMove() {
        ChessMove m = movesRedo.pop();
        moves.push(m);

        return true;
    }

    private boolean validateMove(ChessMove m) {
        boolean isValid = true;

        if (isValid && m.type != MoveType.ADD && this.pieces.get(m.srcLoc) == null) {
            isValid = false;
        }
        if (isValid && m.type == MoveType.MOVE && this.pieces.get(m.destLoc) != null) {
            isValid = false;
        }
        if (isValid && m.type == MoveType.CAPTURE && this.pieces.get(m.destLoc) == null) {
            isValid = false;
        }

        if (m.subMove != null && isValid) {
            return validateMove(m.subMove);
        }
        return isValid;
    }
}
