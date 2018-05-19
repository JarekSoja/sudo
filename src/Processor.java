import java.util.Scanner;

public class Processor {

    private Scanner reader = new Scanner(System.in);
    private Solver solver = new Solver();

    void parseInput() throws CloneNotSupportedException {
        boolean isDone = false;
        while(!isDone) {
            Commander.menu();
            String input = reader.next();
            switch (input.toLowerCase()) {
                case "sudoku":
                    if (solver.solve()) {
                        System.out.println(solver.getBoard());
                    } else {
                        Commander.erroneousSudokuMessage();
                    }
                    break;
                case "n":
                    System.out.println("We are starting new game.");
                    solver = new Solver();
                    System.out.println(solver.getBoard());
                    break;
                case "b":
                    System.out.println(solver.getBoard());
                    break;
                case "x":
                    isDone = true;
                    break;
                default:
                    if (input.chars().allMatch(Character::isDigit) && input.length() == 3) {
                            char[] inputArr = input.toCharArray();
                            placeUserDigitOnBoard(Character.getNumericValue(inputArr[0]), Character.getNumericValue(inputArr[1]), Character.getNumericValue(inputArr[2]));
                    } else
                        Commander.errorMessage();
            }
        }
    }

    private void placeUserDigitOnBoard(int column, int row, int value) {
        Cell parsedCell = solver.getBoard().getCellWithGivenCoordinates(row - 1, column - 1);
        parsedCell.setValue(value);
        parsedCell.removeOtherPossibleValues(value);
        System.out.println(solver.getBoard());
    }
}
