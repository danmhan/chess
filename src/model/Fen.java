package model;

public class Fen {
    public final String startPos = "rnbqkbnr/pppppppp/8/8/8/8/PPPPPPPP/RNBQKBNR";
    private String fenString;

    public Fen(String fenString) {
        fenString = fenString;
    }

    public int[] parseFenString(String fenString) {
        String[] rows = fenString.split("/");
        int[] currBoard = new int[64];
        for (String str: rows) {
            for (int i = 0; i < str.length(); i++) {
                switch (str.charAt(i)) {
                    case 'k':

                }
            }
        }
        return null;
    }
}
