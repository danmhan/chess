package model.pieces;

import model.Game.Coordinate;
import model.Game.PieceType;
import model.Game.Player;

public class Queen extends Piece {
    PieceType type;

    protected Queen(Player player, Coordinate position) {
        super(player, position);
        type = PieceType.QUEEN;
    }
    public PieceType getType() { return PieceType.QUEEN;}

    @Override
    public boolean isValidMove() {
        return false;
    }

    @Override
    public Coordinate[] drawPath() {
        return new Coordinate[0];
    }

    public boolean isValidMove(Coordinate position) {
        return true;
    }

    public Coordinate[] drawPath(Coordinate start) {
        return null;
    }
}