package model.pieces;

import model.Game.*;
import model.Game.board.Board;
import model.Game.board.BoardUtils;
import model.Game.board.Square;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Queen extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = {-9, -8, -7, -1, 1, 7, 8, 9};

    private Color pieceColor;

    public Queen(Player player, int position) {
        super(player, position);
        this.pieceColor = player.getColor();
    }
    public PieceType getType() { return PieceType.QUEEN;}

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

    private boolean isFirstColumnExclusion(int candidateDestinationPosition, int candidateCoordinateOffset) {
        return BoardUtils.FIRST_COLUMN[candidateDestinationPosition] && (candidateCoordinateOffset == -1 ||
            candidateCoordinateOffset == 9 || candidateCoordinateOffset == 7 || candidateCoordinateOffset == -9);
    }

    private boolean isEighthColumnExclusion(int candidateDestinationPosition, int candidateCoordinateOffset) {
        return BoardUtils.EIGHTH_COLUMN[candidateDestinationPosition] && (candidateCoordinateOffset == 1 ||
            candidateCoordinateOffset == -9 || candidateCoordinateOffset == -7 || candidateCoordinateOffset == 9);
    }


}
