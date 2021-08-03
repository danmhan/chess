package model.Game.board;

public class BoardUtils {

//    public static final boolean[] FIRST_COLUMN = {0, 8, 16, 24, 32, 40, 48, 56};
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    private BoardUtils() {
        throw new RuntimeException("Cannot instantiate BoardUtils");
    }
    public static boolean coordinateInBounds(int coordinate) {
        return coordinate >= 0 && coordinate < 64;
    }

    private static boolean[] initColumn(int columnNum) {
        boolean[] columnSquares = new boolean[64];
        while (columnNum < 64) {
            columnSquares[columnNum] = true;
            columnNum += 8;
        }
        return columnSquares;
    }

}
