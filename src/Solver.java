import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Solver {

    private Board board = Board.getBoardInstance();
    private List<Backtrack> backtrack = new ArrayList<>();

    boolean solve() throws CloneNotSupportedException {
            solveObviousCells();
            if (board.isBoardSolved()) {
                System.out.println(board);
                return true;
            }
            guessValue(board.returnFirstEmptyCell());
        return (board.isBoardSolved());
    }

    private void solveObviousCells() {
        boolean isAnythingSolved = false;
        for (int rowNumber = 0; rowNumber < 9; rowNumber++) {
            for (int columnNumber = 0; columnNumber < 9; columnNumber++) {
                if (solveCell(board.getCellWithGivenCoordinates(rowNumber, columnNumber))) {
                    isAnythingSolved = true;
                }
            }
        }
    }

    boolean guessValue(Cell testedCell) throws CloneNotSupportedException {
        Board clonedBoard = board.clone();
        Cell clonedCell = testedCell.clone();
        for (Integer value : testedCell.getPossible()) {
            backtrack.add(new Backtrack(clonedBoard, clonedCell, value));
            testedCell.setValue(value);
            testedCell.removeOtherPossibleValues(value);
            System.out.println(board);
            if(!solve()) {
                recreateStateFromBacktrack();
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean solveCell(Cell testedCell) {
        List<Integer> foundValues = new ArrayList<>(board.getValuesFromConnectedCells(testedCell));
        List<Integer> valuesToRemove = new ArrayList<>();
        if (testedCell.getValue() == 0) {
            for (Integer value : testedCell.getPossible()) {
                if (foundValues.contains(value)) {
                    valuesToRemove.add(value);
                } else if (validateValueVersusPossibleValues(value, testedCell)) {
                    testedCell.setValue(value);
                    testedCell.removeOtherPossibleValues(value);
                    return true;
                }
            }
            testedCell.getPossible().removeAll(valuesToRemove);
        } else {
            if (!validateValueVersusActualValues(testedCell.getValue(), testedCell) && backtrack.size() == 0) {
                System.out.println("Sudoku error!");
                System.exit(-666);
            }
        }
        return false;
    }

    private boolean validateValueVersusPossibleValues(Integer value, Cell cell) {
        Set<Integer> foundValues = board.getPossibleValuesFromConnectedCells(cell);
        return !foundValues.contains(value);
    }

    private boolean validateValueVersusActualValues(Integer value, Cell cell) {
        Set<Integer> foundValues = board.getValuesFromConnectedCells(cell);
        return !foundValues.contains(value);
    }

    void recreateStateFromBacktrack() {
        if (backtrack.size() == 0 || backtrack == null) {
            System.out.println("Sudoku error");
            System.exit(-111);
        } else {
            int row = backtrack.get(backtrack.size()-1).getCell().getRow();
            int column = backtrack.get(backtrack.size()-1).getCell().getColumn();
            int value = backtrack.get(backtrack.size()-1).getCell().getValue();
            board = backtrack.get(backtrack.size()-1).getBoard();
            board.getCellWithGivenCoordinates(row, column).setPossible(backtrack.get(backtrack.size()-1).getCell().getPossible());
            board.getCellWithGivenCoordinates(row, column).removeValue(value);
            backtrack.remove(backtrack.size()-1);
        }
    }
}
