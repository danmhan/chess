package model.pieces;

import model.Game.*;
import model.Game.board.Board;
import model.Game.board.BoardUtils;
import model.Game.board.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//import static model.Game.board.BoardUtils.coordinateInBounds;

public class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_COORDINATES = {-17, -15, -10, -6, 6, 10, 15, 17};

    Color pieceColor;
    /**
     * The constructor for an abstract Chess Piece
     *
     * @param player    The player who controls this piece.
     * @param position  The position on the Board
     */

    public Knight(Player player, int position) {
        super(player, position);
        this.pieceColor = player.getColor();
    }

    @Override
    public PieceType getType() {
        return PieceType.KNIGHT;
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
                    isSecondColumnExclusion(this.position, candidateOffset) ||
                    isSeventhColumnExclusion(this.position, candidateOffset) ||
                    isEighthColumnExclusion(this.position, candidateOffset)) {
                    // Do not add the invalid moves for when the piece is in the
                    // 1st, 2nd, 7th, and 8th columns.
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

    // Add edge cases for the knight on the edges/corners

    private static boolean isFirstColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.FIRST_COLUMN[currentPosition] && (candidateOffset == -17 || candidateOffset == -10 ||
            candidateOffset == 6 || candidateOffset == 15);
    }

    private static boolean isSecondColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SECOND_COLUMN[currentPosition] && (candidateOffset == -10 || candidateOffset == 6);
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.SEVENTH_COLUMN[currentPosition] && (candidateOffset == 10 || candidateOffset == -6);
    }

    private static boolean isEighthColumnExclusion(final int currentPosition, final int candidateOffset) {
        return BoardUtils.EIGHTH_COLUMN[currentPosition] && (candidateOffset == 17 || candidateOffset == 10 ||
            candidateOffset == -6 || candidateOffset == -15);
    }
}
