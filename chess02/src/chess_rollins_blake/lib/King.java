package chess_rollins_blake.lib;

public class King extends Piece {

    public King(PieceColor color, PieceStatus status) {
        super(color, status);
        super.type = PieceType.k;
    }

}
