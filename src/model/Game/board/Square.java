package model.Game.board;

import model.pieces.Piece;

public class Square {

    public Piece pieceOnSquare;
    public boolean isOccupied;

    Square() {
        this.pieceOnSquare = null;
        this.isOccupied = false;
    }

    Square(Piece pieceOnSquare) {
        this.pieceOnSquare = pieceOnSquare;
        this.isOccupied = true;
    }

}
