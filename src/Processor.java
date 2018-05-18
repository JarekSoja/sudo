import java.util.Scanner;

public class Processor {

    private static Scanner reader = new Scanner(System.in);
    private static Solver solver = new Solver();

    static void parseInput() throws CloneNotSupportedException {
        while(true) {
            Commander.menu();
            String input = reader.next();
            switch (input.toLowerCase()) {
                case "sudoku":
                    solver.solve();
                    break;
                case "n":
                    newGame();
                    break;
                case "x":
                    exitGame();
                default:
                    if (input.chars().allMatch(Character::isDigit) && input.length() == 3) {
                            char[] inputArr = input.toCharArray();
                            placeUserDigitOnBoard(Character.getNumericValue(inputArr[0]), Character.getNumericValue(inputArr[1]), Character.getNumericValue(inputArr[2]));
                    } else
                        Commander.errorMessage();
            }
        }
    }

    private static void placeUserDigitOnBoard(int column, int row, int value) {
        Cell parsedCell = Board.getBoardInstance().getCellWithGivenCoordinates(row - 1, column - 1);
        parsedCell.setValue(value);
        parsedCell.removeOtherPossibleValues(value);
        System.out.println(Board.getBoardInstance());
    }

    private static void newGame() {
        Board.getBoardInstance().clearBoard();
        System.out.println(Board.getBoardInstance());
        System.out.println("We are starting new game.");
    }

    private static void exitGame() {
        System.exit(1);
    }

}
