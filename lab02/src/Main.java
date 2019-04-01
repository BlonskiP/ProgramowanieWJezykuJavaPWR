import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Random generator = new Random();

        InstanceProblemGenerator gen=new InstanceProblemGenerator((long)313);
        gen.generateProblem().printItems();
        Thread t=new ProblemSolverThread(generator.nextLong(),"brute");
        t.start();
    }
}
