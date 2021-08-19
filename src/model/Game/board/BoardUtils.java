package model.Game.board;

public class BoardUtils {

    // Columns go in order of 1-a, 2-b
    // Rows go from black to white.
    public static final boolean[] FIRST_COLUMN = initColumn(0);
    public static final boolean[] SECOND_COLUMN = initColumn(1);
    public static final boolean[] SEVENTH_COLUMN = initColumn(6);
    public static final boolean[] EIGHTH_COLUMN = initColumn(7);

    public static final boolean[] SECOND_ROW = initRow(2);
    public static final boolean[] SEVENTH_ROW = initRow(7);

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

    private static boolean[] initRow(int rowNum) {
        boolean[] rowSquares = new boolean[64];
        for (int i = 0; i < 8; i++) {
            rowSquares[8 * (rowNum - 1) + i] = true;
        }
        return rowSquares;
    }

}
