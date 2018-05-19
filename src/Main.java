public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {

        Commander.welcomeMessage();
        Processor processor = new Processor();
        processor.parseInput();
        Commander.goodByeMessage();
    }
}
