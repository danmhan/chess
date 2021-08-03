package model.pieces;

import model.Game.Coordinate;
import model.Game.Move;
import model.Game.PieceType;
import model.Game.Player;
import model.Game.board.Board;

import java.util.List;

public class King extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

    /**
     * The constructor for an abstract Chess Piece
     *  @param player   The player who controls this piece.
     * @param position  The position on the Board
     */
    public King(Player player, int position) {
        super(player, position);
    }

    @Override
    public PieceType getType() {
        return PieceType.KING;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        return null;
    }

}
