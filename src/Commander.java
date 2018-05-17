class Commander {

    static void welcomeMessage() {
        System.out.println("Hello! We are going to solve some sudoku puzzles. Well, actually I am going to solve them, I need you to serve as pseudo-random number generator.\n " +
                "Below you can see empty sudoku board. It's 9 x 9 grid that should be filled with digits from 1 to 9.\n " +
                "Each row, each column and each mini grid (size 3 x 3 each) must have it's own unique digit set - no recurring numbers.\n" +
                "You will be adding digits to the grid in three digit, X Y Z format where: \n" +
                " - X means column\n" +
                " - Y means row\n" +
                " - Z means value you wish to enter\n" +
                "If you give me command >sudoku< I will try to solve the puzzle and present board filled with numbers.\n" +
                "If there is no valid solution I will inform you too.");
    }

    static void menu() {
        System.out.println("Please choose one option: \n - enter three digits to fill a grid on the board, \n - type 'sudoku' to ask me to solve current puzzle,");
        System.out.println(" - press 'n' to start new game,\n - press 'x' to leave game.");
    }

    static void errorMessage() {
        System.out.println("Uppss, something went wrong, I didn't recognize your command");
    }

    static void erroneousSudokuMessage() {
        System.out.println("Provided sudoku is erroneous. End of current game.");
    }

    static void boardSolved() {
        System.out.println("Board is solved.");
        System.out.println(Board.getBoardInstance());
    }
}
