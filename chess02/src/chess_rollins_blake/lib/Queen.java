package chess_rollins_blake.lib;

public class Queen extends Piece {

    public Queen(PieceColor color, PieceStatus status) {
        super(color, status);
        super.type = PieceType.q;
    }

}
