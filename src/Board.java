import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

class Board implements Cloneable {

    private List<List<Cell>> rows = new ArrayList<>();

    Board() {
        for (int i = 0; i < 9; i++) {
            rows.add(new ArrayList<>());
            for (int j = 0; j < 9; j++) {
                rows.get(i).add(new Cell(i, j));
            }
        }
    }

    Set<Integer> getValuesFromConnectedCells(Cell cell) {
        Set<Integer> result = rows.stream()
                .flatMap(v -> v.stream())
                .filter(v -> v.getRow() == cell.getRow() || v.getColumn() == cell.getColumn())
                .filter(v -> !(v.equals(cell)))
                .map(v -> v.getValue())
                .filter(v -> v != 0)
                .collect(Collectors.toSet());
        int rowSection = cell.getRow() / 3;
        int columnSection = cell.getColumn() / 3;
        for (int i = rowSection * 3; i < rowSection * 3 + 3; i++) {
            for (int j = columnSection * 3; j < columnSection * 3 + 3; j++) {
                if (!getCellWithGivenCoordinates(i, j).equals(cell)) {
                    result.add(getCellWithGivenCoordinates(i, j).getValue());
                }
            }
        }
        return result;
    }

    Set<Integer> getPossibleValuesFromConnectedCells(Cell cell) {
        Set<Integer> result = rows.stream()
                .flatMap(v -> v.stream())
                .filter(v -> !(v.equals(cell)))
                .filter(v -> v.getRow() == cell.getRow() || v.getColumn() == cell.getColumn())
                .flatMap(v -> v.getPossible().stream())
                .collect(Collectors.toSet());
        int rowSection = cell.getRow() / 3;
        int columnSection = cell.getColumn() / 3;
        for (int i = rowSection * 3; i < rowSection * 3 + 3; i++) {
            for (int j = columnSection * 3; j < columnSection * 3 + 3; j++) {
                if (!getCellWithGivenCoordinates(i, j).equals(cell)) {
                    result.addAll(getCellWithGivenCoordinates(i, j).getPossible());
                }
            }
        }
        return result;
    }

    Cell getCellWithGivenCoordinates(int row, int column) {
        return getRows().get(row).get(column);
    }

    Cell returnFirstEmptyCell() {
        for (int i = 0; i <= 8; i++) {
            for (int j = 0; j <= 8; j++) {
                Cell testedCell = getCellWithGivenCoordinates(i, j);
                if (testedCell.getValue() == 0) {
                    return testedCell;
                }
            }
        }
        return null;
    }

    boolean isBoardSolved() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (getCellWithGivenCoordinates(i, j).getValue() == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private List<List<Cell>> getRows() {
        return rows;
    }

    @Override
    public Board clone() throws CloneNotSupportedException {
        Board clonedBoard = (Board) super.clone();
        clonedBoard.rows = new ArrayList<>();
        int counter = 0;
        for (List<Cell> row : rows) {
            clonedBoard.rows.add(new ArrayList<>());
            for (Cell cell : row) {
                clonedBoard.getRows().get(counter).add(cell.clone());
            }
            counter++;
        }
        return clonedBoard;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                sb.append(getCellWithGivenCoordinates(i, j).getValue())
                        .append(" | ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}

