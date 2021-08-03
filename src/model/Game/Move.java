package model.Game;

import model.Game.board.Board;
import model.pieces.Piece;

public abstract class Move {
    Board board;
    Piece movedPiece;
    int destinationCoordinate;

    Move(Board board, Piece movedPiece, int destinationCoordinate) {
        this.board = board;
        this.movedPiece = movedPiece;
        this.destinationCoordinate = destinationCoordinate;
    }

    public static final class MajorMove extends Move {

        public MajorMove(Board board, Piece movedPiece, int destinationCoordinate) {
            super(board, movedPiece, destinationCoordinate);
        }
    }

    public static final class AttackMove extends Move {
        Piece attackedPiece;
        public AttackMove(Board board, Piece movedPiece, int destinationCoordinate,
            Piece attackedPiece) {
            super(board, movedPiece, destinationCoordinate);
            this.attackedPiece = attackedPiece;
        }
    }
}
