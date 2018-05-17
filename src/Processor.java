import java.util.Scanner;

public class Processor {

    private Scanner reader = new Scanner(System.in);
    private Solver solver = new Solver();

    public void parseInput() throws CloneNotSupportedException {
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
                    if (input.chars().allMatch(Character::isDigit)) {
                        if (input.length() == 3) {
                            char[] inputArr = input.toCharArray();
                            placeUserDigitOnBoard(Character.getNumericValue(inputArr[0]), Character.getNumericValue(inputArr[1]), Character.getNumericValue(inputArr[2]));
                        } else {
                            Commander.errorMessage();
                        }
                    } else
                        Commander.errorMessage();
            }
        }
    }

    private void placeUserDigitOnBoard(int column, int row, int value) {
        Cell parsedCell = Board.getBoardInstance().getCellWithGivenCoordinates(row - 1, column - 1);
        parsedCell.setValue(value);
        parsedCell.removeOtherPossibleValues(value);
    }

    private void newGame() {
        //TODO New game
    }

    private void exitGame() {
        System.exit(1);
    }

}
