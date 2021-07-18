package model.pieces;

import model.Game.Coordinate;
import model.Game.Move;
import model.Game.PieceType;
import model.Game.Player;
import model.Game.board.Board;

import java.util.List;

public class Queen extends Piece {
    PieceType type;

    public Queen(Player player, Coordinate position) {
        super(player, position);
        type = PieceType.QUEEN;
    }
    public PieceType getType() { return PieceType.QUEEN;}

    @Override
    public List<Move> calculateLegalMoves(Board board) {
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

    public boolean isValidMove(Coordinate position) {
        return true;
    }

    public Coordinate[] drawPath(Coordinate start) {
        return null;
    }
}
