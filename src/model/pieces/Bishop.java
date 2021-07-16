package model.pieces;

import model.Game.Coordinate;
import model.Game.PieceType;
import model.Game.Player;

public class Bishop extends Piece {
    /**
     * The constructor for an abstract Chess Piece
     *
     * @param player   The player who controls this piece.
     * @param position
     */
    public Bishop(Player player, Coordinate position) {
        super(player, position);
    }

    @Override
    public PieceType getType() {
        return null;
    }

    @Override
    public boolean isValidMove() {
        return false;
    }

    @Override
    public Coordinate[] drawPath() {
        return new Coordinate[0];
    }
}
