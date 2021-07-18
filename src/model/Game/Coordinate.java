package model.Game;

public class Coordinate {
    private char file;
    private int rank;
//    public Coordinate(int x, int y) {
//        this.file = x;
//        this.rank = y;
//    }

    public Coordinate(char file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public int getFile() { return file; }
    public int getRank() { return rank; }
//    public String getPosition() {
//        StringBuilder position = new StringBuilder(Character.toString(file));
//        position.append(rank);
//        return position.toString();
//    }

}
