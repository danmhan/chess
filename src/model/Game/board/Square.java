package model.Game.board;

import model.pieces.Piece;

public class Square {

    private Piece pieceOnSquare;
    private boolean isOccupied;

    Square() {
        this.pieceOnSquare = null;
        this.isOccupied = false;
    }

    Square(Piece pieceOnSquare) {
        this.pieceOnSquare = pieceOnSquare;
        this.isOccupied = true;
    }

    public void setPieceOnSquare(Piece pieceOnSquare) {
        this.pieceOnSquare = pieceOnSquare;
        this.isOccupied = true;
    }

    public void removePieceOnSquare() {
        this.pieceOnSquare = null;
        this.isOccupied = false;
    }

    public Piece getPiece() {
        return this.pieceOnSquare;
    }

    public boolean isOccupied() {
        return this.isOccupied;
    }

}
