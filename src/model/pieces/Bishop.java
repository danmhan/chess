package model.pieces;

import model.Game.*;
import model.Game.board.Board;
import model.Game.board.BoardUtils;
import model.Game.board.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Bishop extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -7, 7, 9};
    public Color pieceColor;
    /**
     * The constructor for an abstract Chess Piece
     *
     * @param player    The player who controls this piece.
     * @param position  The position on the Board
     */
    public Bishop(Player player, int position) {
        super(player, position);
        this.pieceColor = player.getColor();
    }

    @Override
    public PieceType getType() {
        return PieceType.BISHOP;
    }

    @Override
    public List<Move> calculateLegalMoves(Board board) {
        final List<Move> legalMoves = new ArrayList<>();
        for (int candidateCoordinateOffset: CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationPosition = this.position;
            // Check for boundary cases of initial position
            if (isFirstColumnExclusion(candidateDestinationPosition, candidateCoordinateOffset) ||
                isEighthColumnExclusion(candidateDestinationPosition, candidateCoordinateOffset)) {
                continue;
            }
            while (BoardUtils.coordinateInBounds(candidateDestinationPosition)) {
                candidateDestinationPosition += candidateCoordinateOffset;
                if (BoardUtils.coordinateInBounds(candidateDestinationPosition)) {
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
                        // Break if there's a piece in the bishop's path.
                        break;
                    }
                }
            }
        }
        return Collections.unmodifiableList(legalMoves);
    }

    private boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == 7 || candidateOffset == -9);
    }

    private boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == -7 || candidateOffset == 9);
    }
}
