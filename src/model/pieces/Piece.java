package model.pieces;

import model.Game.*;
import model.Game.board.Board;

import java.util.List;

/**
 * @author danmhan
 * A class representing a chess piece using an integer value.
 */
public abstract class Piece {

    protected Player player;
    protected int position;
    protected Color pieceColor;

    /**
     * The constructor for an abstract Chess Piece
     * @param player    The player who controls this piece.
     */
    public Piece(Player player, int position) {
        this.player = player;
        this.position = position;
        this.pieceColor = player.getColor();
    }

    /**
     * @return Color of this piece
     */
    public Color getColor() { return player.getColor();}

    /**
     * @return The piece type
     */
    public abstract PieceType getType();

    /**
     * @return The position of this piece.
     */
    public int getPosition() { return position; }

    public abstract List<Move> calculateLegalMoves(final Board board);
//    protected boolean coordinateInBounds(int coordinate) {
//        return coordinate >= 0 && coordinate < 64;
//    }

}
