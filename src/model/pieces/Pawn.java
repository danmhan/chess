package model.pieces;

import model.Game.*;
import model.Game.board.Board;
import model.Game.board.BoardUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Pawn extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {7, 8, 9, 16};
    private boolean isFirstMove;

    /**
     * The constructor for an abstract Chess Piece
     *
     * @param player    The player who controls this piece.
     * @param position  The position on the Board
     */
    public Pawn(Player player, int position) {
        super(player, position);
        this.isFirstMove = true;
    }

    @Override
    public PieceType getType() {
        return PieceType.PAWN;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_COORDINATES) {
            int candidateDestinationPosition = this.position + (this.getColor().getDirection() * candidateCoordinateOffset);

            if (candidateCoordinateOffset == 8 && !board.getSquare(candidateDestinationPosition).isOccupied()) {
                legalMoves.add(new Move.MajorMove(board, this, candidateDestinationPosition));
            } else if (candidateCoordinateOffset == 16 && this.isFirstMove &&
                ((BoardUtils.SEVENTH_ROW[this.position] && this.getColor() == Color.WHITE) ||
                (BoardUtils.SECOND_ROW[this.position] && this.getColor() == Color.BLACK))) {
                final int betweenJumpCoordinate = this.position + (this.getColor().getDirection() * 8);
                if (!board.getSquare(betweenJumpCoordinate).isOccupied() && !board.getSquare(candidateDestinationPosition).isOccupied())
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationPosition));
            } else if (!isFirstColumnExclusion(this.position, candidateCoordinateOffset, this.getColor()) &&
                !isEighthColumnExclusion(this.position, candidateCoordinateOffset, this.getColor())) {
                final Piece pieceOnCandidateSquare = board.getSquare(candidateDestinationPosition).getPiece();
                if (pieceOnCandidateSquare != null && this.getColor() != pieceOnCandidateSquare.getColor()) {
                    legalMoves.add(new Move.AttackMove(board, this, candidateDestinationPosition, pieceOnCandidateSquare));
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    private boolean isFirstColumnExclusion(int currentPosition, int coordinateOffset, Color color) {
        return (coordinateOffset == 7 && BoardUtils.FIRST_COLUMN[currentPosition] && color.isBlack()) ||
            (coordinateOffset == 9 && BoardUtils.FIRST_COLUMN[currentPosition] && color.isWhite());
    }

    private boolean isEighthColumnExclusion(int currentPosition, int coordinateOffset, Color color) {
        return (coordinateOffset == 9 && BoardUtils.EIGHTH_COLUMN[currentPosition] && color.isBlack()) ||
            (coordinateOffset == 7 && BoardUtils.EIGHTH_COLUMN[currentPosition] && color.isWhite());
    }
}
