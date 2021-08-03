package model.Game.board;

import model.Game.Color;
import model.Game.Coordinate;
import model.Game.PieceType;
import model.Game.Player;
import model.pieces.*;
import java.util.*;

public class Board {
    /**
     * Array of arrays that represents the board. Squares[file][rank]. ex. Squares['a'][4]
     */
    public Square[] Squares;
    public model.Game.Color turn = model.Game.Color.WHITE;
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
        Squares = new Square[64];
        
        for (int i = 0; i < 64; i++) {
            Squares[i] = new Square();
        }

        // White Castling
        canCastle.put('K', false);
        canCastle.put('Q', false);

        // Black Castling
        canCastle.put('k', false);
        canCastle.put('q', false);
        this.setBoardToFen(fen);
    }

    public Square getSquare(int position) {
        return Squares[position];
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
                        Squares[8 * rank + file].setPieceOnSquare(new King(new Player(model.Game.Color.WHITE), 8 * rank + file));
                    case 'Q':
                        Squares[8 * rank + file].setPieceOnSquare(new Queen(new Player(model.Game.Color.WHITE), 8 * rank + file));
                    case 'R':
                        Squares[8 * rank + file].setPieceOnSquare(new Rook(new Player(model.Game.Color.WHITE), 8 * rank + file));
                    case 'B':
                        Squares[8 * rank + file].setPieceOnSquare(new Bishop(new Player(model.Game.Color.WHITE), 8 * rank + file));
                    case 'N':
                        Squares[8 * rank + file].setPieceOnSquare(new Knight(new Player(model.Game.Color.WHITE), 8 * rank + file));
                    case 'P':
                        Squares[8 * rank + file].setPieceOnSquare(new Pawn(new Player(model.Game.Color.WHITE), 8 * rank + file));
                    case 'k':
                        Squares[8 * rank + file].setPieceOnSquare(new King(new Player(model.Game.Color.BLACK), 8 * rank + file));
                    case 'q':
                        Squares[8 * rank + file].setPieceOnSquare(new Queen(new Player(model.Game.Color.BLACK), 8 * rank + file));
                    case 'r':
                        Squares[8 * rank + file].setPieceOnSquare(new Rook(new Player(model.Game.Color.BLACK), 8 * rank + file));
                    case 'b':
                        Squares[8 * rank + file].setPieceOnSquare(new Bishop(new Player(model.Game.Color.BLACK), 8 * rank + file));
                    case 'n':
                        Squares[8 * rank + file].setPieceOnSquare(new Knight(new Player(model.Game.Color.BLACK), 8 * rank + file));
                    case 'p':
                        Squares[8 * rank + file].setPieceOnSquare(new Pawn(new Player(model.Game.Color.BLACK), 8 * rank + file));
                    default:
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
            this.turn = model.Game.Color.WHITE;
        } else if (details[0].equals("b")) {
            this.turn = Color.BLACK;
        } else {
            throw new IllegalArgumentException("Must specify player turn");
        }

        // Castling Availability
        if (!details[1].equals("-")) {
            for (int i = 0; i < details[1].length(); i++) {
                canCastle.replace(details[1].charAt(i), true);
            }
        }

        // En Passant Square
        if (!details[2].equals("-")) {
            enPassantSquare = new Coordinate(details[2].charAt(0), details[2].charAt(1));
        }

        // Halfmove clock
        halfMoveNum = Integer.parseInt(details[3]);

        // Fullmove number
        fullMoveNum = Integer.parseInt(details[4]);
    }
}
