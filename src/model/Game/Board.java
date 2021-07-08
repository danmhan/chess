package model.Game;

import model.pieces.*;
import java.awt.*;
import java.util.*;

public class Board {
    public static Piece[][] Squares;
    public Color turn = Color.WHITE;
    public Map<Character, Boolean> canCastle = new HashMap<>();
    public Coordinate enPassantSquare = null;
    public int halfMoveNum = 0;
    public int fullMoveNum = 1;

    /**
     * A mapping of Chess piece String notation to its corresponding PieceType.
     */
    public static final Map<String, PieceType> pieceNotationDict;
    static {
        Map<String, PieceType> tMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        tMap.put("K", PieceType.KING);
        tMap.put("Q", PieceType.QUEEN);
        tMap.put("R", PieceType.ROOK);
        tMap.put("B", PieceType.BISHOP);
        tMap.put("N", PieceType.KNIGHT);
        tMap.put("P", PieceType.PAWN);
        pieceNotationDict = Collections.unmodifiableMap(tMap);
    }

    public Board() {
        this("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }

    public Board(String fen) {
        Squares = new Piece[8][8];

        // White Castling
        canCastle.put('K', false);
        canCastle.put('Q', false);

        // Black Castling
        canCastle.put('k', false);
        canCastle.put('q', false);
        this.setBoardToFen(fen);
    }

    public void setBoardToFen(String fen) {
        String[] lst = fen.split("/");
        String details = lst[7].split(" ", 2)[1];
        this.setBoardDetails(details);
        lst[7] = lst[7].split(" ", 2)[0];
        for (int rank = 0; rank < 8; rank++) {
            int file = 0;
            int strPos = 0;
            while (strPos < 8) {
                int letter = lst[7 - rank].charAt(strPos);
                switch(letter) {
                    case 'K':
                        Squares[][] = new King()
                }
            }

        }
        // Error handling

    }

    private void setBoardDetails(String detailString) {
        String[] details = detailString.split(" ");
        // Player Turn
        if (details[0].equals("w")) {
            this.turn = Color.WHITE;
        } else if (details[0].equals("b")) {
            this.turn = Color.BLACK;
        } else {
            throw new IllegalArgumentException("Must specify player turn");
        }

        // Castling Availability
        if (details[1].equals("-")) {
        } else {
            for (int i = 0; i < details[1].length(); i++) {
                canCastle.replace(details[1].charAt(i), true);
            }
        }

        // En Passant Square
        if (details[2].equals("-")) {
        } else {
            enPassantSquare = new Coordinate(details[2].charAt(0), details[2].charAt(1));
        }

        // Halfmove clock
        halfMoveNum = Integer.valueOf(details[3]);

        // Fullmove number
        fullMoveNum = Integer.valueOf(details[4]);
    }
}
