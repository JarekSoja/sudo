public class Backtrack {

    private Board board;
    private Cell cell;
    private int value;

    public Backtrack(Board board, Cell cell, int value) {
        this.board = board;
        this.cell = cell;
        this.value = value;
    }

    public Board getBoard() {
        return board;
    }

    public Cell getCell() {
        return cell;
    }

    public int getValue() {
        return value;
    }
}
