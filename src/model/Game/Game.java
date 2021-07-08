package model.Game;

public class Game {
    private Player whiteP;
    private Player blackP;
    private Board board;

    public Game() {
        board.setBoardToFen("rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR w KQkq - 0 1");
    }
    public Game(String fen) {
        board.setBoardToFen(fen);
    }

    public static void main(String[] args) {
        Board myBoard = new Board("rnbqkbnr/pp1ppppp/8/2p5/4P3/8/PPPP1PPP/RNBQKBNR w KQkq c6 0 2");
        System.out.println(myBoard.turn);
        System.out.println(myBoard.canCastle);
        System.out.println(myBoard.enPassantSquare.getPosition());
        System.out.println(myBoard.halfMoveNum);
        System.out.println(myBoard.fullMoveNum);
    }

}
