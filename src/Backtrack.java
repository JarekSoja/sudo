public class Backtrack {

    private Board board;
    private Cell cell;
    private int value;

    Backtrack(Board board, Cell cell, int value) {
        this.board = board;
        this.cell = cell;
        this.value = value;
    }

    Board getBoard() {
        return board;
    }

    Cell getCell() {
        return cell;
    }

    int getValue() {
        return value;
    }
}
