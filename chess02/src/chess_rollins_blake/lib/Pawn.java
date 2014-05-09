package chess_rollins_blake.lib;

public class Pawn extends Piece {

    public Pawn(PieceColor color, PieceStatus status) {
        super(color, status);
        super.type = PieceType.p;
    }

}
