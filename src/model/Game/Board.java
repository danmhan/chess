package model.Game;

import model.pieces.*;
import java.awt.*;
import java.util.*;

public class Board {
    /**
     * Array of arrays that represents the board. Squares[file][rank]. ex. Squares['a'][4]
     */
    // Idea: Tile[] Squares where Tile = { Coordinate, Piece } so it's easier to know if a tile is empty or not.
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

    /**
     * Method to set up the board according to the FEN string
     * @param fen   A FEN string
     */
    public void setBoardToFen(String fen) {
        // Split FEN string by rank.
        String[] ranks = fen.split("/");
        // Parse the details separately from ranks.
        String details = ranks[7].split(" ", 2)[1];
        this.setBoardDetails(details);
        ranks[7] = ranks[7].split(" ", 2)[0];

        // Populate the board by rank.
        for (int rank = 0; rank < 8; rank++) {
            int file = 0;
            int strPos = 0;
            while (strPos < ranks[7 - rank].length()) {
                // Start at end of fen string to get the ranks in increasing order.
                int letter = ranks[7 - rank].charAt(strPos);

                // Place the piece on its tile. For numbers, skip over that many tiles.
                switch(letter) {
                    case 'K':
                        Squares[file][rank] = new King(new Player(Color.WHITE), new Coordinate(
                            (char) file, rank));
                    case 'Q':
                        Squares[file][rank] = new Queen(new Player(Color.WHITE), new Coordinate(
                            (char) file, rank));
                    case 'R':
                        Squares[file][rank] = new Rook(new Player(Color.WHITE), new Coordinate(
                            (char) file, rank));
                    case 'B':
                        Squares[file][rank] = new Bishop(new Player(Color.WHITE), new Coordinate(
                            (char) file, rank));
                    case 'N':
                        Squares[file][rank] = new Knight(new Player(Color.WHITE), new Coordinate(
                            (char) file, rank));
                    case 'P':
                        Squares[file][rank] = new Pawn(new Player(Color.WHITE), new Coordinate(
                            (char) file, rank));
                    case 'k':
                        Squares[file][rank] = new King(new Player(Color.BLACK), new Coordinate(
                            (char) file, rank));
                    case 'q':
                        Squares[file][rank] = new Queen(new Player(Color.BLACK), new Coordinate(
                            (char) file, rank));
                    case 'r':
                        Squares[file][rank] = new Rook(new Player(Color.BLACK), new Coordinate(
                            (char) file, rank));
                    case 'b':
                        Squares[file][rank] = new Bishop(new Player(Color.BLACK), new Coordinate(
                            (char) file, rank));
                    case 'n':
                        Squares[file][rank] = new Knight(new Player(Color.BLACK), new Coordinate(
                            (char) file, rank));
                    case 'p':
                        Squares[file][rank] = new Pawn(new Player(Color.BLACK), new Coordinate(
                            (char) file, rank));
                    default:
                        // Possible bug: null object on empty tiles.
                        file += letter;
                }
                strPos += 1;
                file += 1;
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
