public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Solver solver = new Solver();
        boolean isSolved = false;
        do {
            isSolved = solver.solve();
            System.out.println(Board.getBoardInstance());
        } while (!isSolved);


    }
}
