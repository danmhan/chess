package model.pieces;

import model.Game.*;
import model.Game.board.Board;
import model.Game.board.BoardUtils;
import model.Game.board.Square;

import java.util.ArrayList;
import java.util.Collections;
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
        int candidateDestinationPosition;
        List<Move> legalMoves = new ArrayList<>();
        for (int candidateOffset : CANDIDATE_MOVE_COORDINATES) {
            candidateDestinationPosition = this.position + candidateOffset;
            // Check if destination is within the chess board
            if (BoardUtils.coordinateInBounds(candidateDestinationPosition)) {
                // Edge cases...literally
                if (isFirstColumnExclusion(this.position, candidateOffset) ||
                    isEighthColumnExclusion(this.position, candidateOffset)) {
                    continue;
                }
                Square destinationSquare = board.getSquare(candidateDestinationPosition);
                // Check if the square is occupied.
                if (!destinationSquare.isOccupied()) {
                    legalMoves.add(new Move.MajorMove(board, this, candidateDestinationPosition));
                } else {
                    // Check if ally or enemy piece
                    Piece pieceAtDestination = destinationSquare.getPiece();
                    Color pieceColor = pieceAtDestination.getColor();
                    if (this.pieceColor != pieceColor) {
                        // Capture the piece at the destination square.
                        legalMoves.add(new Move.AttackMove(board, this, candidateDestinationPosition, pieceAtDestination));
                    }
                }
            }
        }

        return Collections.unmodifiableList(legalMoves);
    }

    private boolean isFirstColumnExclusion(int candidateDestinationPosition, int candidateCoordinateOffset) {
        return BoardUtils.FIRST_COLUMN[candidateDestinationPosition] &&
            (candidateCoordinateOffset == -9 || candidateCoordinateOffset == -1 ||
                candidateCoordinateOffset == 7);
    }
    private boolean isEighthColumnExclusion(int candidateDestinationPosition, int candidateCoordinateOffset) {
        return BoardUtils.EIGHTH_COLUMN[candidateDestinationPosition] &&
            (candidateCoordinateOffset == 9 || candidateCoordinateOffset == 1 ||
                candidateCoordinateOffset == -7);
    }
}
