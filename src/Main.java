public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        Solver solver = new Solver();
        boolean isSolved = false;
        do {
            isSolved = solver.solve();
        } while (!isSolved);


    }
}
